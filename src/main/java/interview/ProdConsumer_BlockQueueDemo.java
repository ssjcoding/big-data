package interview;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

class MyResource<E>{
	private volatile boolean FLAG = true;
	private BlockingQueue<E> blockingQueue = null;
	AtomicInteger atomicInteger = new AtomicInteger();
	public MyResource(BlockingQueue<E> blockingQueue){
		this.blockingQueue = blockingQueue;
	}

	public void increment(){
		E object = null;
		while(FLAG){
			object = (E)(atomicInteger.getAndIncrement() + "");
			try{
				blockingQueue.put(object);
				System.out.println(Thread.currentThread().getName() + "\t" + "生产 " + object);
				try{TimeUnit.SECONDS.sleep(1);}catch(InterruptedException e){e.printStackTrace();}
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		System.out.println(Thread.currentThread().getName() + " 生产者退出");
	}

	public void decrement(){
		E result = null;
		while(FLAG){
			try{
				result = blockingQueue.poll(2, TimeUnit.SECONDS);
				System.out.println(Thread.currentThread().getName() + "\t" + "消费 " + result);
				try{TimeUnit.SECONDS.sleep(1);}catch(InterruptedException e){e.printStackTrace();}
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		System.out.println(Thread.currentThread().getName() + " 消费者退出");
	}

	public void stop(){
		this.FLAG = false;
	}


}

/**
 * volatile/CAS/atomicInteger/BlockQueue/线程交互/原子引用
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/12/21 4:47 PM,
 */
public class ProdConsumer_BlockQueueDemo{

	public static void main(String[] args){
		MyResource myResource = new MyResource(new ArrayBlockingQueue<String>(10));
		new Thread(()->{
			myResource.increment();
		}, "AA").start();

		new Thread(()->{
			myResource.decrement();
		}, "BB").start();

		try{TimeUnit.SECONDS.sleep(10);}catch(InterruptedException e){e.printStackTrace();}
		myResource.stop();
		System.out.println("老板叫停");


	}

}
