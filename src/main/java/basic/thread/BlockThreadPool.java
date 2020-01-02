package basic.thread;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义阻塞线程池，当池满时会阻塞任务提交
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/12/2 10:29 PM,
 */
public class BlockThreadPool{
	private ThreadPoolExecutor pool = null;
	public BlockThreadPool(int poolSize){
		pool = new ThreadPoolExecutor(poolSize, poolSize, 0, TimeUnit.MILLISECONDS,
				new ArrayBlockingQueue<>(5),
				new CustomThreadFactory(),
				new CustomRejectedExecutionHandler());
	}

	public void destory(){
		if(pool != null){
			pool.shutdownNow();
		}
	}

	private class CustomThreadFactory implements ThreadFactory{
		private AtomicInteger count = new AtomicInteger(0);

		@Override
		public Thread newThread(Runnable r){
			Thread t = new Thread(r);
			String threadName = BlockThreadPool.class.getName() + count.addAndGet(1);
			t.setName(threadName);
			return t;
		}
	}

	private class CustomRejectedExecutionHandler implements RejectedExecutionHandler{

		@Override
		public void rejectedExecution(Runnable r, ThreadPoolExecutor executor){
			try{
				executor.getQueue().put(r);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}

	public void execute(Runnable runnable){
		this.pool.execute(runnable);
	}

	//测试构造的线程池
	public static void main(String[] args){
		BlockThreadPool pool = new BlockThreadPool(3);
		for(int i=1; i<100; i++){
			System.out.println("提交第" + i + "个任务！");
			pool.execute(() -> {
				System.out.println(Thread.currentThread().getId() + "==============开始");
				try{
					TimeUnit.SECONDS.sleep(10);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getId() + "==============结束");

			});
			System.out.println("提交第" + i + "个任务成功！");
		}
		System.out.println("整体结束");
	}
}
