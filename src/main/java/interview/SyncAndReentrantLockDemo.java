package interview;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Printer{

	private Lock lock = new ReentrantLock();
	private Condition condition1 = lock.newCondition();
	private Condition condition2 = lock.newCondition();
	private Condition condition3 = lock.newCondition();
	private int currentNumber = 5;

	public void print(int count){
		lock.lock();
		try{

			while(currentNumber!=count){//判断
				if(count==5){
					condition1.await();
				}else if(count==10){
					condition2.await();
				}else if(count==15){
					condition3.await();
				}
			}

			for(int i=0; i<count; i++){//工作
				System.out.println(Thread.currentThread().getName() + "\t" + i);
			}

			if(count==5){//通知
				currentNumber=10;
				System.out.println("通知B线程");
				condition2.signalAll();
			}else if(count==10){
				currentNumber=15;
				System.out.println("通知C线程");
				condition3.signalAll();
			}else if(count==15){
				currentNumber=5;
				System.out.println("通知A线程");
				condition1.signalAll();
			}

		}catch(Exception e){
		    e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
}

/**
 * 题目：synchronized和Lock有什么区别？用新的lock有什么好处？
 * 1 原始构成
 * 	synchronized是关键字属于JVM层面，
 * 		monitorenter（底层是通过monitor对象来完成，其实wait/notify等方法也依赖于monitor对象，
 * 			只有在同步块或者方法中才能调wait/notify等方法）
 *		monitorexit
 *	Lock是具体类（java.util.concurrent.locks.Lock）是api层面的锁
 *
 * 2 使用方法
 * 	synchronized不需要用户去手动释放锁，当synchronized代码执行完后系统会自动让线程释放对锁的占用
 * 	ReentrantLock则需要用户去手动释放锁若没有主动释放锁，就有可能导致出现死锁现象
 * 	需要lock()和unlock()方法配合try/finally()语句块来完成
 *
 * 3 等待是否可中断
 * 	synchronized不可中断，除非抛出异常或者正常运行完成
 * 	ReentrantLock可中断，1、设置超时方法tryLock(long timeout, TimeUnit unit)
 * 						2、lockInterruptibly()放代码块中，调用interrupt()方法可中断
 *
 * 4 加锁是否公平
 * 	synchronized非公平锁
 * 	ReentrantLock两者都可以，默认非公平锁，构造方法可以传入boolean值，true为公平锁，false为非公平锁
 *
 * 5 锁绑定多个条件Condition
 * 	synchronized没有
 * 	ReentrantLock用来实现分组唤醒需要唤醒的线程们，可以精确唤醒，而不是像synchronized要么随机唤醒一个线程，要么全部唤醒
 *
 * 	=====================================================================
 * 	=====================================================================
 * 	=====================================================================
 *
 * 	题目：多线程之间按顺序调用，实现A-->B-->C三个线程启动，需要如下：
 * 	AA打印5次，BB打印10次，CC打印15次
 * 	打印十次
 * 	.......
 * 	来10轮
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/12/21 2:55 PM,
 */
public class SyncAndReentrantLockDemo{

	public static void main(String[] args){
		Printer printer = new Printer();
		for(int i = 0; i<3; i++){
		    new Thread(()->{
		    	printer.print(5);
			}, "A").start();
		}

		for(int i = 0; i<3; i++){
			new Thread(()->{
				printer.print(10);
			}, "B").start();
		}

		for(int i = 0; i<3; i++){
			new Thread(()->{
				printer.print(15);
			}, "C").start();
		}
	}
}
