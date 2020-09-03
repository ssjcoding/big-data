package jvm;

import java.util.concurrent.TimeUnit;

class MyNumber{
	volatile int num=10;
	public void addNum1205(){
		num=1205;
	}
}

/**
 * JMM
 * 	volatile 可见性
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/12/15 10:02 AM,
 */
public class T2{
	public static void main(String[] args){
		MyNumber myNumber = new MyNumber();
		new Thread(()->{
			try{
			    TimeUnit.SECONDS.sleep(3);
			}catch(InterruptedException e){
			    e.printStackTrace();
			}
			myNumber.addNum1205();

			System.out.println(Thread.currentThread().getName() + "\t 设置成功");
		}, "AAA").start();

		while(myNumber.num == 10){

		}
		System.out.println("break ok");
	}
}
