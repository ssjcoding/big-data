package basic.pattern;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 生产者消费者模式
 * 	采用blockingQueue实现
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/12/2 7:18 AM,
 */
public class ProducerConsumerPattern{

	public static void main(String[] args){
		//Create shared object
		BlockingQueue sharedQueue = new LinkedBlockingDeque();
		Thread prodThread = new Thread(new Producer(sharedQueue));
		Thread consThread = new Thread(new Consumer(sharedQueue));
		Thread prodThread2 = new Thread(new Producer(sharedQueue));
		Thread consThrea2 = new Thread(new Consumer(sharedQueue));
		Thread consThrea3 = new Thread(new Consumer(sharedQueue));

		prodThread.start();
		consThread.start();
		prodThread2.start();
		consThrea2.start();
		consThrea3.start();
	}

}


class Producer implements Runnable{
	private final BlockingQueue sharedQueue;

	public Producer(BlockingQueue sharedQueue){
		this.sharedQueue = sharedQueue;
	}

	@Override
	public void run(){
		for(int i=0; i<10; i++){
			try{
				System.out.println(Thread.currentThread().getName() + "Produced:" + i);
				sharedQueue.put(i);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
}

class Consumer implements Runnable{
	private final BlockingQueue sharedQueue;

	public Consumer(BlockingQueue sharedQueue){
		this.sharedQueue = sharedQueue;
	}

	@Override
	public void run(){
		for(int i=0; i<10; i++){
			try{
				System.out.println(Thread.currentThread().getName() + "Consumed:" + sharedQueue.take());
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
}

