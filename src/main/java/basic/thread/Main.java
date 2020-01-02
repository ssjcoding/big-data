package basic.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ${DESCRIPTION}
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/12/2 11:39 PM,
 */
public class Main{
	public static void main(String[] args){
		ExecutorService threadPool = Executors.newFixedThreadPool(5);
		for(int i=0; i<5; i++){
			threadPool.submit(()->{
				System.out.println("current thread name" + Thread.currentThread().getName());
				try{
					Object object = null;
					System.out.println("result ##" + object.toString());
				}catch(Exception e){
					System.out.println("程序出异常拉！！！");
				}
			});
		}
	}

}
