package interview;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Phone implements Runnable{
	public synchronized void sendSMS() throws Exception{
		System.out.println(Thread.currentThread().getId() + "\t invoked sendSMS()");
		sendEmail();
	}
	public synchronized void sendEmail() throws Exception{
		System.out.println(Thread.currentThread().getId() + "\t #### invoked sendEmail()");
	}

	@Override
	public void run(){
		get();
	}

	Lock lock = new ReentrantLock();

	public void get(){
		lock.lock();
		lock.lock();
		try{
			System.out.println(Thread.currentThread().getId() + "\t get");
			try{
			    TimeUnit.SECONDS.sleep(1);
			}catch(InterruptedException e){
			    e.printStackTrace();
			}
			set();
		}catch(Exception e){
		    e.printStackTrace();
		}finally{
			lock.unlock();
			lock.unlock();
		}
	}

	public void set(){
		lock.lock();
		try{
			System.out.println(Thread.currentThread().getId() + "\t set");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
}

/**
 * 可重入锁（也叫做递归锁）
 * 指的是同一线程外层函数获得锁之后，内层递归函数仍然能获取该锁的代码，
 * 在同一个线程外层方法获取锁的时候，在进入内层方法会自动获取锁
 *
 * 也即是说，线程可以进入任何一个已经拥有的锁的所同步着的代码块。
 *
 * case one Synchronized 就是一个典型的可重入锁
 * t1	 invoked sendSMS()				t1线程在外层方法获取锁的时候
 * t1	 #### invoked sendEmail()		t1在进入内层方法会自动获取锁
 * t2	 invoked sendSMS()
 * t2	 #### invoked sendEmail()
 *
 * case two
 * ReentrantLock就是一个典型的可重入锁
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/12/19 12:42 AM,
 */
public class ReenterLockDemo{
	public static void main(String[] args){
		Phone phone = new Phone();
		new Thread(()->{
			try{
				phone.sendSMS();
			}catch(Exception e){
				e.printStackTrace();
			}
		}, "t1").start();

		new Thread(()->{
			try{
				phone.sendSMS();
			}catch(Exception e){
				e.printStackTrace();
			}
		}, "t2").start();

		try{
		    TimeUnit.SECONDS.sleep(1);
		}catch(InterruptedException e){
		    e.printStackTrace();
		}
		System.out.println();
		
		Thread t3 = new Thread(phone);
		Thread t4 = new Thread(phone);
		t3.start();
		t4.start();
	}
}
