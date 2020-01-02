package interview;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

class MyThread implements Callable<Integer>{

	@Override
	public Integer call() throws Exception{
		try{TimeUnit.SECONDS.sleep(4);}catch(InterruptedException e){e.printStackTrace();}
		return 300;
	}
}

/**
 * ${DESCRIPTION}
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/12/21 9:41 PM,
 */
public class CallableDemo{
	public static void main(String[] args){
		int result1 = 100;
		FutureTask<Integer> futureTask = new FutureTask(()->{
			try{TimeUnit.SECONDS.sleep(3);}catch(InterruptedException e){e.printStackTrace();}
			return 100;
		});

		int result = 100;
		new Thread(futureTask).start();

		while(!futureTask.isDone()){

		}
		int result02 = 0;
		try{
			result02 = futureTask.get();
		}catch(InterruptedException e){
			e.printStackTrace();
		}catch(ExecutionException e){
			e.printStackTrace();
		}

	}
}
