package interview;

import java.util.concurrent.CountDownLatch;

/**
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/12/20 12:01 AM,
 */
public class CountDownLatchDemo{

	public static int COUNT_DOWN_DEFAULT=6;

	public static void main(String[] args) throws InterruptedException{
		CountDownLatch countDownLatch = new CountDownLatch(COUNT_DOWN_DEFAULT);

		for(int i = 1; i<7; i++){
		    new Thread(()->{
				System.out.println(Thread.currentThread().getName() + "\t 被灭");
				countDownLatch.countDown();
			}, CountryEnum.forEach_CountryEnum(i).getRetMessage()).start();
		}

		countDownLatch.await();
		System.out.println("===================秦国一统天下");
	}
}
