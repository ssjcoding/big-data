package basic.pattern;

import java.util.concurrent.TimeUnit;

/**
 * 等待与唤醒测试
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/12/1 10:10 PM,
 */
public class ProducerConsumerWithWaitAndNotify{
	public static void main(String[] args){
		Object object = new Object();
		System.out.println(object);

		for(int i=0; i<5; i++){
			Mythread mythread = new Mythread("Thread" + i, object);
			mythread.start();
		}

		try{
			TimeUnit.SECONDS.sleep(2);
			System.out.println("---------------Main Thread notify----------------");
			synchronized(object){
				object.notifyAll();
			}
			TimeUnit.SECONDS.sleep(2);
			System.out.println("Main Thread is end.");
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}

class Mythread extends Thread {
	private String name;
	private Object object;

	public Mythread(String name, Object o){
		this.name = name;
		this.object = o;
	}

	@Override
	public void run(){
		System.out.println(name + " is waiting.");
		try{
			synchronized(object){
				object.wait();
			}
			System.out.println(name + " has been notified");
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}

}
