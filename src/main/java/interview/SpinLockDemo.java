package interview;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁
 * 是指尝试获取锁的线程不会立即阻塞，而是采用循环的方式去尝试获取锁，这样的好处是减少线程上下文切换的消耗，
 * 缺点是循环会消耗CPU
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/12/19 1:19 AM,
 */
public class SpinLockDemo{
	AtomicReference<Thread> atomicReference = new AtomicReference<>();
	public void myLock(){
		System.out.println(Thread.currentThread().getName() + " come in");
		while(!atomicReference.compareAndSet(null, Thread.currentThread())){
		}
	}
	public void unMyLock(){
		System.out.println(Thread.currentThread().getName() + " release lock");
		atomicReference.compareAndSet(Thread.currentThread(), null);
	}

	public static void main(String[] args){
		SpinLockDemo spinLockDemo = new SpinLockDemo();
		new Thread(()->{
			spinLockDemo.myLock();
			try{
			    TimeUnit.SECONDS.sleep(5);
			}catch(InterruptedException e){
			    e.printStackTrace();
			}
			spinLockDemo.unMyLock();
		}, "AA").start();
		
		try{
		    TimeUnit.SECONDS.sleep(1);
		}catch(InterruptedException e){
		    e.printStackTrace();
		}

		spinLockDemo.myLock();
		spinLockDemo.unMyLock();

	}

}
