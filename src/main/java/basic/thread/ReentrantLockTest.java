package basic.thread;

/**
 * ReentrantLock 可重入的独占锁 测试 比起synchronized功能更加丰富，支持公平锁实现，支持中断响应以及限时等待等等。可以配合一个或多个Condition条件方便的实现等待通知机制。
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/12/2 7:57 AM,
 */
public class ReentrantLockTest{

//	/**
//	 * 公平锁
//	 */
//	static Lock lock = new ReentrantLock(true);
//	public static void main(String[] args) throws InterruptedException{
//		for(int i=0; i<5; i++){
//			new Thread(new ThreadDemo(i)).start();
//		}
//	}
//
//	static class ThreadDemo implements Runnable{
//		Integer id;
//		public ThreadDemo(Integer id){
//			this.id = id;
//		}
//
//		@Override
//		public void run(){
//			try{
//				TimeUnit.MILLISECONDS.sleep(10);
//			}catch(InterruptedException e){
//				e.printStackTrace();
//			}
//
//			for(int i=0; i<2; i++){
//				lock.lock();
//				System.out.println("获得锁的线程：" + id);
//				lock.unlock();
//			}
//		}
//	}

//	/**
//	 * 相应中断的例子
//	 */
//	static Lock lock1 = new ReentrantLock();
//	static Lock lock2 = new ReentrantLock();
//	public static void main(String[] args) throws InterruptedException{
//		Thread thread = new Thread(new ThreadDemo(lock1, lock2)); //该线程先获取锁1，再获取锁2
//		Thread thread1 = new Thread(new ThreadDemo(lock2, lock1)); //该线程先获取锁2，再获取锁1
//		thread.start();
//		thread1.start();
//		thread.interrupt();//是第一个线程中断
//	}
//
//	static class ThreadDemo implements Runnable{
//		Lock firstLock;
//		Lock secondLock;
//		public ThreadDemo(Lock firstLock, Lock secondLock){
//			this.firstLock = firstLock;
//			this.secondLock = secondLock;
//		}
//
//		@Override
//		public void run(){
//			try{
//				firstLock.lockInterruptibly();
//				TimeUnit.MILLISECONDS.sleep(10);//更好的出发死锁
//				secondLock.lockInterruptibly();
//			}catch(InterruptedException e){
//				e.printStackTrace();
//			}finally{
//				firstLock.unlock();
//				secondLock.unlock();
//				System.out.println(Thread.currentThread().getName() + "正常结束！");
//			}
//		}
//	}

}


