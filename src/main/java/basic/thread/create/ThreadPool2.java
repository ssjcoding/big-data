package basic.thread.create;

import java.util.concurrent.*;

/**
 * 有返回值的线程池
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/11/30 11:40 PM,
 */
public class ThreadPool2{

	public static void main(String[] args){
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		for(int i=0; i<5; i++){
			Future<Integer> future = executorService.submit(() -> {
				int result = 0;
				for(int i1 = 0; i1<=10; i1++){
					result += i1;
				}
				return result;
			});

			try{
				System.out.println(Thread.currentThread().getName() + "---" + future.get());
			}catch(InterruptedException | ExecutionException e){
				e.printStackTrace();
			}
		}

		System.out.println(Thread.currentThread().getName() + "---");
		System.out.println(Thread.activeCount()+ "---");
	}
}
