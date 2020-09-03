package basic.thread.create;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池
 * 跟数据库连接池类似
 * 避免了线程的创建和销毁造成的额外开销
 *
 * java.util.concurrent
 * Executor    负责现成的使用和调度的根接口
 * 		|--ExecutorService    线程池的主要接口
 * 			|--ThreadPoolExecutor    线程池的实现类
 * 			|--ScheduledExecutorService    接口，负责线程的调度
 * 				|--ScheduledThreadPoolExecutor    (extends ThreadPoolExecutor implements ScheduledExecutorService)
 *
 * Executors工具类
 * 提供了创建线程池的方法
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/11/30 11:28 PM,
 */
public class ThreadPool{
	public static void main(String[] args){
		//使用Executors工具类中的方法创建线程池
		ExecutorService pool = Executors.newFixedThreadPool(5);
		ThreadPoolDemo demo = new ThreadPoolDemo();

		//为线程池中的线程分配任务，使用submit方法，传入的参数可以是Runnable的实现，也可以是Callable的实现类
		for(int i=1; i<=5; i++){
			pool.submit(demo);
		}

		//关闭线程池
		//shutdown：以一宗平和的方式关闭线程池，在关闭线程池之前，会等待线程池中的所有任务都结束，不再接受新任务
		//shutdownNow: 立即关闭线程池
		pool.shutdown();
	}
}

class ThreadPoolDemo implements Runnable{
	/**
	 * 多线程的共享数据
	 */
	private int i = 0;

	@Override
	public void run(){
		while(i<50){
			System.out.println(Thread.currentThread().getName() + "---" + i++);
		}
	}
}
