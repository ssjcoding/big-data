package util;

import java.util.HashSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 线程池简单实现
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2018/6/12 下午4:41,
 */
public class ThreadPoolExcutor{

	/**
	 * 创建
	 */
	private volatile boolean RUNNING = true;

	/**
	 * 所有任务都放队列中，让工作线程来消费
	 */
	private static BlockingQueue<Runnable> queue = null;

	/**
	 * worker 存储位置
	 */
	private final HashSet<Worker> workers = new HashSet<>();

	/**
	 * 工作线程数
	 */
	int poolSize = 0;

	/**
	 * 核心线程数（创建了多少个工作线程）
	 */
	int coreSize = 0;

	/**
	 * shutdown状态标识
	 */
	boolean shutdown = false;

	public ThreadPoolExcutor(int poolSize){
		this.poolSize = poolSize;
		queue = new LinkedBlockingQueue<>(poolSize);
	}

	/**
	 * 执行
	 * @param runnable
	 */
	public void exec(Runnable runnable){
		if(runnable == null){
			throw new NullPointerException();
		}

		if(coreSize < poolSize){
			addThread(runnable);
		}else{
			try{
				queue.put(runnable);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}

	/**
	 * 添加线程
	 * @param runnable
	 */
	public void addThread(Runnable runnable){
		coreSize ++;
		Worker worker = new Worker(runnable);
		Thread t = new Thread(worker);
		worker.setCurrentThread(t);
		workers.add(worker);
		try{
			t.start();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * 关闭
	 */
	public void shutdown(){
		RUNNING = false;
		if(!workers.isEmpty()){
			for(Worker worker : workers){
				worker.interruptIfIdle();
			}
		}
		shutdown = true;
		Thread.currentThread().interrupt();
	}

	/**
	 * 工作线程[内部类]
	 */
	class Worker implements Runnable{

		/**
		 * worker自身thread
		 */
		private Thread currentThread = null;

		public Worker(Runnable runnable){
			queue.offer(runnable);
		}

		@Override
		public void run(){
			while(true && RUNNING){
				if(shutdown == true){
					Thread.interrupted();
				}
				Runnable task = null;
				try{
					task = getTask();
					task.run();
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		}

		/**
		 * 获取需要执行的任务
		 * @return
		 * @throws InterruptedException
		 */
		public Runnable getTask() throws InterruptedException{
			return queue.take();
		}

		/**
		 * 中断正在处理的worker任务
		 */
		public synchronized void interruptIfIdle(){
			currentThread.isInterrupted();
			System.out.println(currentThread.getName() + " interrupt");
		}

		public Thread getCurrentThread(){
			return currentThread;
		}

		public void setCurrentThread(Thread currentThread){
			this.currentThread = currentThread;
		}
	}

	public static void main(String[] args){
		ThreadPoolExcutor excutor = new ThreadPoolExcutor(3);
		for(int i=0; i<10; i++){
			excutor.exec(new Runnable(){
				@Override
				public void run(){
					System.out.println("线程 " + Thread.currentThread().getName() + " 在帮我干活");
				}
			});
		}

		try{
			Thread.sleep(50);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		excutor.shutdown();
	}

}

