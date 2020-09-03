package juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class AirCondition{
	private int num = 0;

	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();

	public void increment() throws InterruptedException{

		lock.lock();
		try{
			//判断
			while(num != 0){
				condition.await();
			}
			//干活
			num ++;
			System.out.println(Thread.currentThread().getName() + " " + num);
			//通知

			condition.signalAll();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			lock.unlock();
		}

	}

	public void decrement() throws InterruptedException{
		lock.lock();
		try{
			//判断
			while(num == 0){
				condition.await();
			}
			//干活
			num --;
			System.out.println(Thread.currentThread().getName() + " " + num);
			//通知

			condition.signalAll();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}

//	public synchronized void increment() throws InterruptedException{
//		//判断
//		while(num != 0){
//			this.wait();
//		}
//		num ++;
//		System.out.println(Thread.currentThread().getName() + " " + num);
//		this.notifyAll();
//		//通知
//	}
//
//	public synchronized void decrement() throws InterruptedException{
//		while(num == 0){
//			this.wait();
//		}
//		num --;
//		System.out.println(Thread.currentThread().getName() + " " + num);
//		this.notifyAll();
//	}
}

/**
 * 题目：现在两个线程，可以操作初始值为零的一个变量，
 * 实现一个线程对该变量加1，一个线程对该变量减1，
 * 实现交替，来10轮，变量初始值为零。
 *
 * 1、高内聚低耦合前提下，线程操作资源类
 * 2、判断/干活/通知
 * 3、防止虚假唤醒（采用while唤醒后重新验证不要采用if）
 *
 * synchronized 与 wait() 		notify() 	notifyAll()匹配
 * Lock			与 await()		signal()	signalAll()匹配
 *
 * 知识小总结 = 多线程编程套路 + while判断 + 新版写法
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/12/12 11:18 PM,
 */
public class ProdConsumerDemo04{
	public static void main(String[] args){
		AirCondition airCondition = new AirCondition();
		new Thread(()->{
			for(int i = 0; i<10; i++){
				try{
					airCondition.increment();
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		}, "A1").start();

		new Thread(()->{
			for(int i = 0; i<10; i++){
				try{
					airCondition.decrement();
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		}, "B1").start();

		new Thread(()->{
			for(int i = 0; i<10; i++){
				try{
					airCondition.increment();
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		}, "A2").start();

		new Thread(()->{
			for(int i = 0; i<10; i++){
				try{
					airCondition.decrement();
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		}, "B2").start();

	}
}
