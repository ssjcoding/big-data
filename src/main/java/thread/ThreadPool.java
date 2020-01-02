package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * java提供四种线程池
 *
 * SynchronousQueue	没有容量，是无缓冲等待队列，是一个不存储元素的阻塞队列，会直接将任务交给消费者，必须等队列中的添加元素被消费后才能继续添加新的元素。
 * LinkedBlockingQueue 是一个无界缓存等待队列。当前执行的线程数量达到corePoolSize的数量时，剩余的元素会在阻塞队列里等待。（所以在使用此阻塞队列时maximumPoolSizes就相当于无效了），每个线程完全独立于其他线程。生产者和消费者使用独立的锁来控制数据的同步，即在高并发的情况下可以并行操作队列中的数据。
 * ArrayBlockingQueue 是一个有界缓存等待队列，可以指定缓存队列的大小，当正在执行的线程数等于corePoolSize时，多余的元素缓存在ArrayBlockingQueue队列中等待有空闲的线程时继续执行，当ArrayBlockingQueue已满时，加入ArrayBlockingQueue失败，会开启新的线程去执行，当线程数已经达到最大的maximumPoolSizes时，再有新的元素尝试加入ArrayBlockingQueue时会报错。
 *
 * Java并发包中的阻塞队列一共7个，当然他们都是线程安全的。
 * ArrayBlockingQueue：一个由数组结构组成的有界阻塞队列。
 * LinkedBlockingQueue：一个由链表结构组成的有界阻塞队列。
 * PriorityBlockingQueue：一个支持优先级排序的无界阻塞队列。
 * DealyQueue：一个使用优先级队列实现的无界阻塞队列。
 * SynchronousQueue：一个不存储元素的阻塞队列。
 * LinkedTransferQueue：一个由链表结构组成的无界阻塞队列。
 * LinkedBlockingDeque：一个由链表结构组成的双向阻塞队列。
 *
 * handler有四个选择：
 * ThreadPoolExecutor.AbortPolicy()
 * 抛出java.util.concurrent.RejectedExecutionException异常
 * ThreadPoolExecutor.CallerRunsPolicy()
 * 重试添加当前的任务，他会自动重复调用execute()方法
 * ThreadPoolExecutor.DiscardOldestPolicy()
 * 抛弃旧的任务
 * ThreadPoolExecutor.DiscardPolicy()
 * 抛弃当前的任务
 *
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/12/12 3:10 PM,
 */
public class ThreadPool{
	public static void main(String[] args){
		singleThreadExecutorTest();
	}

	/**
	 * newSingleThreadExecutor创建一个单线程化线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序执行。
	 * 					1个核心线程，最大线程数1，线程等待时间0L，采用LinkedBlockingQueue进行线程阻塞
	 *
	 * @throws InterruptedException
	 */
	public static void singleThreadExecutorTest(){
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		for(int i=1; i<100; i++){
			executorService.submit(() -> {
				System.out.println(Thread.currentThread().getName());
			});
		}
	}

	/**
	 * newScheduledThreadPool创建一个定长线程池，支持定时及周期性任务执行。
	 * 					nThreads个核心线程，最大线程数Integer.MAX_VALUE，线程等待时间0L，采用DelayedWorkQueue进行线程阻塞
	 *
	 * @throws InterruptedException
	 */
	public static void scheduledThreadPoolTest(){
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
		for(int i=1; i<100; i++){
			scheduledExecutorService.schedule(() -> {
				System.out.println(Thread.currentThread().getName());
			}, 3, TimeUnit.SECONDS);
		}
	}

	/**
	 * newFixedThreadPool创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
	 * 					nThreads个核心线程，最大线程数nThreads，线程等待时间0L秒，采用LinkedBlockingQueue进行线程阻塞
	 *
	 * @throws InterruptedException
	 */
	public static void fixedThreadPoolTest(){
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		for(int i=1; i<100; i++){
			executorService.submit(() -> {
				System.out.println(Thread.currentThread().getName());
			});
		}
	}

	/**
	 * newCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
	 * 					0个核心线程，最大线程数Integer.MAX_VALUE，线程等待时间60秒，采用SynchronousQueue进行线程阻塞
	 *
	 * @throws InterruptedException
	 */
	public static void cachedThreadPoolTest(){
		ExecutorService executorService = Executors.newCachedThreadPool();
		for(int i=1; i<100; i++){
			executorService.submit(() -> {
				System.out.println(Thread.currentThread().getName());
			});
		}
	}
}
