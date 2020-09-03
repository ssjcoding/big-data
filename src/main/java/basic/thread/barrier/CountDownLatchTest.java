package basic.thread.barrier;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDownLatch测试学习类
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/12/1 1:18 PM,
 */
public class CountDownLatchTest{
	public static void main(String[] args){
		final CountDownLatch latch = new CountDownLatch(2);
		System.out.println("主线程开始执行…… ……");
		//第一个子线程执行
		ExecutorService es1 = Executors.newSingleThreadExecutor();
		es1.execute(() -> {
			try {
				Thread.sleep(3000);
				System.out.println("子线程："+Thread.currentThread().getName()+"执行");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			latch.countDown();
		});
		es1.shutdown();

		//第二个子线程执行
		ExecutorService es2 = Executors.newSingleThreadExecutor();
		es2.execute(() -> {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("子线程："+Thread.currentThread().getName()+"执行");
			latch.countDown();
		});

		es2.shutdown();

		System.out.println("等待两个线程执行完毕…… ……");
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("两个子线程都执行完毕，继续执行主线程");

	}
}
