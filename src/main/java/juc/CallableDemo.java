package juc;


import java.util.concurrent.*;

class MyThread implements Callable<Integer>{

	@Override
	public Integer call() throws Exception{
		System.out.println("******come in");
		TimeUnit.SECONDS.sleep(4);
		return 1234;
	}
}

/**
 * ${DESCRIPTION}
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/12/14 12:35 PM,
 */
public class CallableDemo{
	public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException{
		FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());
		new Thread(futureTask, "A").start();
		int result = futureTask.get(3, TimeUnit.SECONDS);
		System.out.println(result);
	}
}
