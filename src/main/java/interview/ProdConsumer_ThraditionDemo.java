package interview;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData{
	private int number=0;

//	public synchronized void increment(){
//		while(number!=0){//判断
//			try{
//				this.wait();
//			}catch(InterruptedException e){
//				e.printStackTrace();
//			}
//		}
//		number++;//干活
//		System.out.println(Thread.currentThread().getName() + "\t" + number);
//		this.notifyAll();
//	}
//
//	public synchronized void decrement(){
//		while(number==0){
//			try{
//				this.wait();
//			}catch(InterruptedException e){
//				e.printStackTrace();
//			}
//		}
//		number--;
//		System.out.println(Thread.currentThread().getName() + "\t" + number);
//		this.notifyAll();
//	}

	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();

	public void increment(){
		lock.lock();
		try{
			while(number!=0){//判断
				condition.await();
			}
			number++;//干活
			System.out.println(Thread.currentThread().getName() + "\t" + number);
			condition.signalAll();
		}catch(Exception e){
		    e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}

	public void decrement(){
		lock.lock();
		try{
			while(number==0){//判断
				condition.await();
			}
			number--;//干活
			System.out.println(Thread.currentThread().getName() + "\t" + number);
			condition.signalAll();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}

}

/**
 * 题目：一个初始值为零的变量，两个线程对其交替操作，一个加1一个减1，来5轮
 *
 * 1	线程		操作（方法）	资源类
 * 2	判断		干活			通知
 * 3	防止虚假唤醒机制
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/12/21 3:18 PM,
 */
public class ProdConsumer_ThraditionDemo{

	public static void main(String[] args){
		ShareData shareData = new ShareData();
		new Thread(()->{
			for(int i = 0; i<5; i++){
				shareData.increment();
			}
		}, "AAA").start();

		new Thread(()->{
			for(int i = 0; i<5; i++){
				shareData.decrement();
			}
		}, "BBB").start();

		new Thread(()->{
			for(int i = 0; i<5; i++){
				shareData.increment();
			}
		}, "aaa").start();

		new Thread(()->{
			for(int i = 0; i<5; i++){
				shareData.decrement();
			}
		}, "bbb").start();
	}

}
