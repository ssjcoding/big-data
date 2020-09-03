package interview;

import java.util.concurrent.*;

/**
 * 第四种获得/使用java多线程的方式，线程池
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/12/21 10:03 PM,
 */
public class MyThreadPoolDemo{

	public static void main(String[] args){
		ExecutorService executorService = new ThreadPoolExecutor(
				2,
				5,
				1L,
				TimeUnit.SECONDS,
				new LinkedBlockingQueue<>(3),
				Executors.defaultThreadFactory(),
				new ThreadPoolExecutor.DiscardPolicy());
		try{
			for(int i = 0; i<10; i++){
				executorService.execute(()->{
					System.out.println(Thread.currentThread().getName() + "\t" + "办理业务");
					try{TimeUnit.SECONDS.sleep(1);}catch(InterruptedException e){e.printStackTrace();}
				});
			}
		}catch(Exception e){

		}finally{
			executorService.shutdown();
		}


	}

}
