package basic.thread;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * wait 与 notify 实现生产者消费者模式
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/12/1 10:31 PM,
 */
public class ProducerConsumerInJava{
	public static void main(String[] args){
		System.out.println("How to use wait and notify method in Java");
		System.out.println("Solving Producer Consumer Problem");
		Queue buffer = new LinkedList();
		int maxSize = 10;
		Thread producer = new Producer(buffer, maxSize, "Producer");
		Thread consumer = new Consumer(buffer, maxSize, "Consumer");
		producer.start();
		consumer.start();
	}
}

/**
 * Producer Thread will keep producing values for Consumer
 * to consumer. It will use wait() method when Queue is full
 * and use notify() method to send notification to Consumer
 * Thread.
 */
class Producer extends Thread {

	private Queue queue;
	private int maxSize;

	public Producer(Queue queue, int maxSize, String name){
		super(name);
		this.queue = queue;
		this.maxSize = maxSize;
	}

	@Override
	public void run(){
		while(true){
			synchronized(queue){
				while(queue.size() == maxSize){
					try{
						System.out.println("Queue is full, " + "Producer thread waiting for " + "consumer to take something from queue");
						queue.wait();
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				Random random = new Random();
				int i = random.nextInt();
				System.out.println("Producing value : " + i);
				queue.add(i);
				queue.notifyAll();
			}
		}
	}
}

class Consumer extends Thread{
	private Queue queue;
	private int maxSize;

	public Consumer(Queue queue, int maxSize, String name){
		super(name);
		this.queue = queue;
		this.maxSize = maxSize;
	}

	@Override
	public void run(){
		while(true){
			synchronized(queue){
				while(queue.isEmpty()){
					try{
						System.out.println("Queue is empty, " + "Consumer thread is waiting" + " for producer thread to put something in queue");
						queue.wait();
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				System.out.println("Consuming value : " + queue.remove());
				queue.notifyAll();
			}
		}
	}
}