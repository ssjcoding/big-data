package interview;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * ${DESCRIPTION}
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/12/20 1:04 AM,
 */
public class SemaphoreDemo{
	public static void main(String[] args){
		Semaphore semaphore = new Semaphore(3);
		for(int i = 0; i<6; i++){
		    new Thread(()->{
				try{
					semaphore.acquire();
					System.out.println(Thread.currentThread().getName() + "\t 抢到车位");
					try{TimeUnit.SECONDS.sleep(3);}catch(InterruptedException e){e.printStackTrace();}
					System.out.println(Thread.currentThread().getName() + "\t停车3秒后离开车位");
				}catch(InterruptedException e){
					e.printStackTrace();
				}finally{
					semaphore.release();
				}
			}, String.valueOf(i)).start();
		}
	}
}
