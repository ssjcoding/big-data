package interview;

import java.util.concurrent.TimeUnit;

/**
 * ${DESCRIPTION}
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/12/22 2:01 AM,
 */
public class HelloGc{
	public static void main(String[] args){
//		System.out.println("===============");
//		try{TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);}catch(InterruptedException e){e.printStackTrace();}

		System.out.println("******************GC");
//		byte[] bytes = new byte[500 * 1024* 1024];jps
		try{
			TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);}catch(InterruptedException e){e.printStackTrace();}

	}
}
