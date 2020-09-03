package thread;

import java.util.ArrayList;
import java.util.concurrent.*;


/**
 * 通过实现Callable接口可以获取返回值
 * 通过 Future 与 FutureTask 获取返回值
 * FutureTask 实现Runnable 接口，所以可以当线程用
 *
 * get()方法会阻塞到执行
 * get(time,时间单位),会设置超时时间
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/12/12 5:20 PM,
 */
public class TaskCallable implements Callable<String>{

	int num;
	public TaskCallable(int i){
		num = i;
	}

	@Override
	public String call() throws Exception{
		TimeUnit.MILLISECONDS.sleep(200);
		return Thread.currentThread().getName() + "task" + num;
	}

	public static void main(String[] args){
		ExecutorService executorService = Executors.newCachedThreadPool();
		ArrayList<Future<String>> results = new ArrayList<>();
		for(int i=0; i<10; i++){
			results.add(executorService.submit(new TaskCallable(i)));
		}

		long start = System.currentTimeMillis();
		for(Future future : results){
			try{
				String result = (String)future.get(300, TimeUnit.MILLISECONDS);
				System.out.println(result);
			}catch(InterruptedException e){
				e.printStackTrace();
			}catch(ExecutionException e){
				e.printStackTrace();
			}catch(TimeoutException e){
				e.printStackTrace();
			}
		}
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}
}
