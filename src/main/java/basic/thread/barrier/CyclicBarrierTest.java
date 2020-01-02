package basic.thread.barrier;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CyclicBarrier测试学习类
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/12/1 11:16 AM,
 */
public class CyclicBarrierTest{
	/**
	 * 了解CyclicBarrier(parties)/getParties()/await()/getNumberWaiting()的基本用法。
	 * 理解循环的意义。
	 */
//	public static void main(String[] args) throws InterruptedException{
//		//构造函数1：初始化-开启屏障的方数
//		CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
//		System.out.println("cyclicBarrier.getParties()获取开启屏障的方数：" + cyclicBarrier.getParties());
//		//通过barrier.getNumberWaiting()获取正在等待的线程数
//		System.out.println("通过cyclicBarrier.getNumberWaiting()获取正在等待的线程数：初始----" + cyclicBarrier.getNumberWaiting());
//		new Thread(() -> {
//			System.out.println("添加第1个等待线程----" + Thread.currentThread().getName());
//			//添加一个等待线程
//			try{
//				cyclicBarrier.await();
//				System.out.println(Thread.currentThread().getName() + " is running...");
//			}catch(InterruptedException e){
//				e.printStackTrace();
//			}catch(BrokenBarrierException e){
//				e.printStackTrace();
//			}
//			System.out.println(Thread.currentThread().getName() + " is terminated.");
//		}).start();
//
//		Thread.sleep(10);
//
//		//通过通过cyclicBarrier.getNumberWaiting()获取正在等待的线程数
//		System.out.println("通过cyclicBarrier.getNumberWaiting()获取正在等待的线程数：添加第1个等待线程---" + cyclicBarrier.getNumberWaiting());
//
//		Thread.sleep(10);
//
//		new Thread(() -> {
//			System.out.println("添加第2个等待线程----" + Thread.currentThread().getName());
//			//添加一个等待线程
//			try{
//				cyclicBarrier.await();
//				System.out.println(Thread.currentThread().getName() + " is running...");
//			}catch(InterruptedException e){
//				e.printStackTrace();
//			}catch(BrokenBarrierException e){
//				e.printStackTrace();
//			}
//			System.out.println(Thread.currentThread().getName() + " is terminated.");
//		}).start();
//
//		Thread.sleep(100);
//
//		//通过barrier.getNumberWaiting()获取正在等待的线程数
//		System.out.println("通过cyclicBarrier.getNumberWaiting()获取正在等待的线程数：打开屏障之后---" + cyclicBarrier.getNumberWaiting());
//
//		//已经打开的屏障，再次有线程等待的话，还会重新生效--视为循环
//		new Thread(() -> {
//			System.out.println("屏障打开之后，再有线程加入等待：" + Thread.currentThread().getName());
//			//添加一个等待线程
//			try{
//				cyclicBarrier.await();
//				System.out.println(Thread.currentThread().getName() + " is running...");
//			}catch(InterruptedException e){
//				e.printStackTrace();
//			}catch(BrokenBarrierException e){
//				e.printStackTrace();
//			}
//			System.out.println(Thread.currentThread().getName() + " is terminated.");
//		}).start();
//
//		Thread.sleep(10);
//		System.out.println("通过cyclicBarrier.getNumberWaiting()获取正在等待的线程数：打开屏障之后---" + cyclicBarrier.getNumberWaiting());
//		Thread.sleep(10);
//
//		new Thread(() -> {
//			System.out.println("屏障打开之后，再有线程加入等待：" + Thread.currentThread().getName());
//			//添加一个等待线程
//			try{
//				cyclicBarrier.await();
//				System.out.println(Thread.currentThread().getName() + " is running...");
//			}catch(InterruptedException e){
//				e.printStackTrace();
//			}catch(BrokenBarrierException e){
//				e.printStackTrace();
//			}
//			System.out.println(Thread.currentThread().getName() + " is terminated.");
//		}).start();
//		Thread.sleep(10);
//		System.out.println("通过barrier.getNumberWaiting()获取正在等待的线程数：打开屏障之后---" + cyclicBarrier.getNumberWaiting());
//	}

	/**
	 * 熟悉reset()的用法
	 * 理解回归初始状态的意义
	 */
//	public static void main(String[] args) throws InterruptedException{
//		CyclicBarrier barrier2 = new CyclicBarrier(2);
//		//如果是一个初始的CyclicBarrier，则reset()之后，什么也不会发生
//		System.out.println("如果是一个初始的CyclicBarrier，则reset()之后，什么也不会发生");
//		barrier2.reset();
//
//		Thread.sleep(100);
//		//如果是一个已经打开一次的CyclicBarrier，则reset()之后，什么也不会发生
//		ExecutorService executorService2 = Executors.newCachedThreadPool();
//
//		//等待两次
//		for (int i = 0; i < 2; i++) {
//			executorService2.submit(() -> {
//				try {
//					barrier2.await();
//					System.out.println("222屏障已经打开.");
//				} catch (InterruptedException e) {
//					//e.printStackTrace();
//					System.out.println("222被中断");
//				} catch (BrokenBarrierException e) {
//					//e.printStackTrace();
//					System.out.println("222被重置");
//				}
//			});
//		}
//
//		barrier2.reset();
//		Thread.sleep(100);
//
//		//如果是一个 有线程正在等待的线程，则reset()方法会使正在等待的线程抛出异常
//		executorService2.submit(() -> {
//			executorService2.submit(() -> {
//				try {
//					barrier2.await();
//					System.out.println("333屏障已经打开.");
//				} catch (InterruptedException e) {
//					//e.printStackTrace();
//					System.out.println("333被中断");
//				} catch (BrokenBarrierException e) {
//					System.out.println("在等待过程中，执行reset()方法，等待的线程抛出BrokenBarrierException异常，并不再等待");
//					//e.printStackTrace();
//				}
//			});
//		});
//
//		Thread.sleep(100);
//		barrier2.reset();
//		executorService2.shutdown();
//	}

	/**
	 * 练习await()/await(timeout,TimeUnit)/isBroken()的使用方法
	 * 理解破损标志位broken的状态转换
	 * @param args
	 */
//	public static void main(String[] args) throws InterruptedException{
//		CyclicBarrier barrier1 = new CyclicBarrier(3);
//		ExecutorService executorService = Executors.newCachedThreadPool();
//		executorService.submit(() -> {
//			try {
//				//等待，除非：1.屏障打开;2.本线程被interrupt;3.其他等待线程被interrupted;4.其他等待线程timeout;5.其他线程调用reset()
//				barrier1.await();
//			} catch (InterruptedException e) {
//				System.out.println(Thread.currentThread().getName() + " is interrupted.");
//				//e.printStackTrace();
//			} catch (BrokenBarrierException e) {
//				System.out.println(Thread.currentThread().getName() + " is been broken.");
//				//e.printStackTrace();
//			}
//		});
//
//		Thread.sleep(10);
//		System.out.println("刚开始，屏障是否破损：" + barrier1.isBroken());
//
//		//添加一个等待线程-并超时
//		executorService.submit(() -> {
//			try {
//				/**
//				 * 等待1s，除非：1.屏障打开(返回true);2.本线程被interrupt;3.本线程timeout;4.其他等待线程被interrupted;5.其他等待线程timeout;6.其他线程调用reset()
//				 */
//				barrier1.await(1, TimeUnit.SECONDS);
//			} catch (InterruptedException e) {
//				System.out.println(Thread.currentThread().getName() + " is interrupted.");
//				//e.printStackTrace();
//			} catch (BrokenBarrierException e) {
//				System.out.println(Thread.currentThread().getName() + " is been reset().");
//				//e.printStackTrace();
//			} catch (TimeoutException e) {
//				System.out.println(Thread.currentThread().getName() + " is timeout.");
//				//e.printStackTrace();
//			}
//		});
//
//		Thread.sleep(100);
//		System.out.println("当前等待线程数量：" + barrier1.getNumberWaiting());
//		Thread.sleep(1000);
//		System.out.println("当前等待线程数量：" + barrier1.getNumberWaiting());
//		System.out.println("当等待的线程timeout时，当前屏障是否破损：" + barrier1.isBroken());
//		System.out.println("等待的线程中，如果有一个出现问题，则此线程会抛出相应的异常；其他线程都会抛出BrokenBarrierException异常。");
//		System.out.println();
//		Thread.sleep(5000);
//		//通过reset()重置屏障回初始状态，也包括是否破损
//		barrier1.reset();
//		System.out.println("reset()之后，当前屏障是否破损：" + barrier1.isBroken());
//		System.out.println("reset()之后，当前等待线程数量：" + barrier1.getNumberWaiting());
//		executorService.shutdown();
//	}


	/**
	 * 练习CyclicBarrier(int parties, Runnable barrierAction)的用法
	 * 理解屏障线程的意义
	 * @param args
	 */
//	public static void main(String[] args){
//		//构造器：设置屏障放开前做的事情
//		CyclicBarrier barrier3 = new CyclicBarrier(2, ()->{
//			System.out.println("屏障放开，[屏障线程]先运行！");
//			try {
//				Thread.sleep(2000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			System.out.println("[屏障线程]的事情做完了!");
//		});
//
//		for(int i=0; i<2; i++){
//			new Thread(()->{
//				System.out.println(Thread.currentThread().getName() + " 等待屏障放开");
//				try {
//					barrier3.await();
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				} catch (BrokenBarrierException e) {
//					e.printStackTrace();
//				}
//				System.out.println(Thread.currentThread().getName() + "开始干活...干活结束");
//			}).start();
//		}
//	}

	/**
	 * 模拟多线程分组计算
	 * 有一个大小为50000的随机数组，用5个线程分别计算10000个元素的和
	 * 然后在将计算结果进行合并，得出最后的结果。
	 * @param args
	 */
	public static void main(String[] args) throws InterruptedException{
		//数组大小
		int size =5000000;
		//定义数组
		int[] numbers = new int[size];

		for(int i=0; i<size; i++){
			numbers[i] = (int)(Math.random()*1000);
		}

		LocalDateTime start = LocalDateTime.now();
		//单线计算结果
		Long sum = 0L;
		for(int i=0; i<numbers.length; i++){
			sum += numbers[i];
		}

		LocalDateTime end = LocalDateTime.now();
		Duration duration = Duration.between(start, end);

		System.out.println("单线程计算结果：" + sum + " 耗时：" + duration);

		ExecutorService executorService = Executors.newFixedThreadPool(5);
		//定义五个Future去保存子数组计算结果
		final int[] results = new int[5];
		final long[] sumsResult = new long[1];
		CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () -> {
			long sums = 0;
			for (int i = 0; i < 5; i++) {
				sums += results[i];
			}
			sumsResult[0] = sums;
			System.out.println("多线程计算结果：" + sums);
		});

		LocalDateTime start2 = LocalDateTime.now();
		//子数组长度
		int length = 1000000;
		//定义五个线程去计算
		for (int i = 0; i < 5; i++) {
			//定义子数组
			int[] subNumbers = Arrays.copyOfRange(numbers, (i * length), ((i + 1) * length));
			//盛放计算结果
			int finalI = i;
			executorService.submit(() -> {
				for (int j = 0; j < subNumbers.length; j++) {
					results[finalI] += subNumbers[j];
				}
				//等待其他线程进行计算
				try {
					cyclicBarrier.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
				}
			});
		}

		LocalDateTime end2 = LocalDateTime.now();
		Duration duration1 = Duration.between(start2, end2);
		Thread.sleep(100);
		System.out.println("多线程计算结果：" + sumsResult[0] + " 耗时：" + duration1);


		//关闭线程池
		executorService.shutdown();
	}

}
