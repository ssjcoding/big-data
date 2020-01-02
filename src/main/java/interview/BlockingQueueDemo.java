package interview;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * ${DESCRIPTION}
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/12/21 1:19 PM,
 */
public class BlockingQueueDemo{

	public static void main(String[] args) throws InterruptedException{
		BlockingQueue<String> blockingQueue = new SynchronousQueue<>();
		new Thread(()->{
			try{
				System.out.println(Thread.currentThread().getName() + "\t" + "a");
				blockingQueue.put("a");
				System.out.println(Thread.currentThread().getName() + "\t" + "b");
				blockingQueue.put("b");
				System.out.println(Thread.currentThread().getName() + "\t" + "c");
				blockingQueue.put("c");
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}, "AAA").start();
		
		new Thread(()->{
			try{TimeUnit.SECONDS.sleep(5);}catch(InterruptedException e){e.printStackTrace();}
			try{
				System.out.println(Thread.currentThread().getName() + "\t" + blockingQueue.take());
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			try{TimeUnit.SECONDS.sleep(5);}catch(InterruptedException e){e.printStackTrace();}
			try{
				System.out.println(Thread.currentThread().getName() + "\t" + blockingQueue.take());
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			try{TimeUnit.SECONDS.sleep(5);}catch(InterruptedException e){e.printStackTrace();}
			try{
				System.out.println(Thread.currentThread().getName() + "\t" + blockingQueue.take());
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}, "BBB").start();
	}
}
