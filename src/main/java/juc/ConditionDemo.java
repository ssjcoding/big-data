package juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareDate{
	private int num=1; //1 A工作，2 B工作，3 C工作
	private Lock lock = new ReentrantLock();
	private Condition condition1 = lock.newCondition();
	private Condition condition2 = lock.newCondition();
	private Condition condition3 = lock.newCondition();

	public void print(int count){
		lock.lock();
		try{
			if(count==5){
				while(num!=1){
					condition1.await();
				}
				//工作
				for(int i = 0; i<5; i++){
					System.out.println(Thread.currentThread().getName() + " " + i);
				}
				num = 2;
				//通知
				condition2.signalAll();
			}else if(count == 10){
				while(num!=2){
					condition2.await();
				}
				//工作
				for(int i = 0; i<10; i++){
					System.out.println(Thread.currentThread().getName() + " " + i);
				}
				num = 3;
				//通知
				condition3.signalAll();
			}else if(count == 15){
				while(num!=3){
					condition3.await();
				}
				//工作
				for(int i = 0; i<15; i++){
					System.out.println(Thread.currentThread().getName() + " " + i);
				}
				num = 1;
				//通知
				condition1.signalAll();
			}else{
				System.out.println("非指定打印次数");
			}
		}catch(Exception e){
		    e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}


	public void print5(){
		//判断
		lock.lock();
		try{
			while(num!=1){
				condition1.await();
			}
			//工作
			for(int i = 0; i<5; i++){
				System.out.println(Thread.currentThread().getName() + " " + i);
			}
			num = 2;
			//通知
			condition2.signalAll();
		}catch(Exception e){
		    e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}

	public void print10(){
		//判断
		lock.lock();
		try{
			while(num!=2){
				condition2.await();
			}
			//工作
			for(int i = 0; i<10; i++){
				System.out.println(Thread.currentThread().getName() + " " + i);
			}
			num = 3;
			//通知
			condition3.signalAll();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}


	public void print15(){
		//判断
		lock.lock();
		try{
			while(num!=3){
				condition3.await();
			}
			//工作
			for(int i = 0; i<15; i++){
				System.out.println(Thread.currentThread().getName() + " " + i);
			}
			num = 1;
			//通知
			condition1.signalAll();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}

}

/**
 * 备注：多线程之间按照顺序调用，实现A->B->C
 * 三个线程启动，要求如下：
 *
 * AA打印5次，BB打印10次，CC打印15次
 * 接着
 * AA打印5次，BB打印10次，CC打印15次
 * 来10轮
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/12/13 1:21 AM,
 */
public class ConditionDemo{
	public static void main(String[] args){
		ShareDate shareDate = new ShareDate();
		new Thread(()->{
			for(int i = 0; i<10; i++){
//				shareDate.print5();
				shareDate.print(5);
			}
		}, "A").start();

		new Thread(()->{
			for(int i = 0; i<10; i++){
//				shareDate.print10();
				shareDate.print(10);
			}
		}, "B").start();

		new Thread(()->{
			for(int i = 0; i<10; i++){
//				shareDate.print15();
				shareDate.print(15);
			}
		}, "C").start();
	}
}
