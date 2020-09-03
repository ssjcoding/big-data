package basic.thread.create;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.*;

/**
 * FutureTask超时获取结果测试
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/12/1 8:08 AM,
 */
public class FutureTaskTimeOut{
	/**
	 * 创建线程池
	 */
	private ExecutorService executorService = Executors.newFixedThreadPool(3);

	public void futureTaskDemo(){
		//创建一个执行任务
		FutureTask<String> future = new FutureTask<>(() -> {
			//业务逻辑
			for(int i=0; i<3; i++){
				Thread.sleep(1000);
			}
			System.out.println("********************");
			return "success";
		});

		try{
			//执行任务
			executorService.execute(future);
			String reult = future.get(5, TimeUnit.SECONDS);
		}catch(InterruptedException e){
			e.printStackTrace();
		}catch(ExecutionException e){
			e.printStackTrace();
		}catch(TimeoutException e){
			e.printStackTrace();
		}finally{
			System.out.println("cancel");
			future.cancel(true);
		}
		System.out.println("FutureTask end....................");
	}

	/**
	 * 测试
	 * @param args
	 */
	public static void main(String[] args){
//		FutureTaskTimeOut demo = new FutureTaskTimeOut();

//		Runnable runnable1 = () -> {
//			for(int i=0; i<2; i++){
//				LocalDateTime localDateStart = LocalDateTime.now();
//				System.out.println("Runnable1 " + i + " start");
//				demo.futureTaskDemo();
//				LocalDateTime localDateEnd = LocalDateTime.now();
//				Duration duration = Duration.between(localDateStart, localDateEnd);
//				System.out.println("Runnable1 " + i + " end cast time :" + duration);
//			}
//		};
//
//		Runnable runnable2 = () -> {
//			for(int i=0; i<2; i++){
//				LocalDateTime localDateStart = LocalDateTime.now();
//				System.out.println("Runnable2 " + i + " start");
//				demo.futureTaskDemo();
//				LocalDateTime localDateEnd = LocalDateTime.now();
//				Duration duration = Duration.between(localDateStart, localDateEnd);
//				System.out.println("Runnable2 " + i + " end cast time:" + duration);
//			}
//		};
//
//		Runnable runnable3 = () -> {
//			for(int i=0; i<2; i++){
//				LocalDateTime localDateStart = LocalDateTime.now();
//				System.out.println("Runnable3 " + i + " start");
//				demo.futureTaskDemo();
//				LocalDateTime localDateEnd = LocalDateTime.now();
//				Duration duration = Duration.between(localDateStart, localDateEnd);
//				System.out.println("Runnable3 " + i + " end cast time:" + duration);
//			}
//		};
//
//		ExecutorService executorService2 = Executors.newFixedThreadPool(4);
//		executorService2.submit(runnable1);
//		executorService2.submit(runnable2);
//		executorService2.submit(runnable3);

//		Thread thread1 = new Thread(runnable1);
//		Thread thread2 = new Thread(runnable2);
//		thread1.start();
//		thread2.start();

		/**
		 * 创建线程池
		 */
		ExecutorService executorService2 = Executors.newFixedThreadPool(3);

		FutureTask<String> future1 = new FutureTask<>(() -> {
			//业务逻辑
			for(int i=0; i<3; i++){
				Thread.sleep(1000);
			}
			System.out.println("********************");
			return "test1";
		});

		FutureTask<String> future2 = new FutureTask<>(() -> {
			//业务逻辑
			for(int i=0; i<4; i++){
				Thread.sleep(1000);
			}
			System.out.println("********************");
			return "test2";
		});

		FutureTask<String> future3 = new FutureTask<>(() -> {
			//业务逻辑
			for(int i=0; i<2; i++){
				Thread.sleep(1000);
			}
			System.out.println("********************");
			return "test3";
		});

		try{
			LocalDateTime localDateStart = LocalDateTime.now();
			//执行任务
			executorService2.execute(future1);
			executorService2.execute(future2);
			executorService2.execute(future3);


			String result1 = future1.get(5, TimeUnit.SECONDS);
			String result2 = future2.get(5, TimeUnit.SECONDS);
			String result3 = future3.get(5, TimeUnit.SECONDS);
			System.out.println(result1);
			System.out.println(result2);
			System.out.println(result3);

			LocalDateTime localDateEnd = LocalDateTime.now();
			Duration duration = Duration.between(localDateStart, localDateEnd);
			System.out.println("cast time " + duration);

		}catch(InterruptedException e){
			e.printStackTrace();
		}catch(ExecutionException e){
			e.printStackTrace();
		}catch(TimeoutException e){
			e.printStackTrace();
		}finally{
			System.out.println("cancel");
		}
		System.out.println("FutureTask end....................");
	}

}
