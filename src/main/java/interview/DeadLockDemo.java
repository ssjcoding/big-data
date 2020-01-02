package interview;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class MyRsource{
	private Lock lock1 = new ReentrantLock();
	private Lock lock2 = new ReentrantLock();
	public void get1(){
		lock1.lock();
		try{
			System.out.println(Thread.currentThread().getName() + "\t" + "获的锁lock1,并尝试获得lock2");
			try{TimeUnit.SECONDS.sleep(3);}catch(InterruptedException e){e.printStackTrace();}
			get2();
		}catch(Exception e){
		    e.printStackTrace();
		}finally{
			lock1.unlock();
		}
	}

	public void get2(){
		lock2.lock();
		try{
			try{TimeUnit.SECONDS.sleep(3);}catch(InterruptedException e){e.printStackTrace();}
			System.out.println(Thread.currentThread().getName() + "\t" + "获的锁lock2,并尝试获得lock1");
			get1();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			lock2.unlock();
		}
	}
}

/**
 * 死锁是指两个或者两个以上的线程在执行过程中，
 * 因争夺资源而造成的一种互相等待的现象，
 * 若无外力干涉那他们都将无法推进下去
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/12/22 12:05 AM,
 */
public class DeadLockDemo{
	public static void main(String[] args){
		MyRsource myRsource = new MyRsource();
		ExecutorService threadPool = new ThreadPoolExecutor(
				2,
				2,
				0,
				TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(2),
				Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
		try{
			threadPool.execute(()->{
				myRsource.get1();
				try{TimeUnit.SECONDS.sleep(2);}catch(InterruptedException e){e.printStackTrace();}
//				myRsource.get2();
			});

			threadPool.execute(()->{
				myRsource.get2();
				try{TimeUnit.SECONDS.sleep(2);}catch(InterruptedException e){e.printStackTrace();}
//				myRsource.get1();
			});
		}catch(Exception e){

		}finally{
			threadPool.shutdown();
		}

		/**
		 * linux ps -ef | grep ****
		 * windows 下java运行程序 也有类似ps的查看进程的命令，但是目前我们
		 */
	}
}
