package basic.thread.create;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 四种线程实现方法
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/11/30 5:25 PM,
 */
public class MyThread{
//	public static void main(String[] args){
//		for(int i=0; i<10; i++){
//			new ExtendsThread().start();
//		}
//		System.out.println(Thread.currentThread().getName());
//	}


//	public static void main(String[] args){
//		Runnable runnable = new ImplRunnable();
//		for(int i=0; i<10; i++){
//			new Thread(runnable).start();
//		}
//		System.out.println(Thread.currentThread().getName());
//	}

	public static void main(String[] args) throws InterruptedException, ExecutionException{
		for(int i=0; i<10; i++){
			Callable<Integer> implCallable = new ImplCallable();
			FutureTask<Integer> futureTask = new FutureTask<Integer>(implCallable);
			new Thread(futureTask).start();
			System.out.println(Thread.currentThread().getName() + "----" + futureTask.get());
		}
		System.out.println(Thread.currentThread().getName());
	}
}

/**
 * 继承Thread类，重写run方法
 * 	每次创建一个新的线程，都要新建一个Thread子类的对象
 * 	启动线程，new Thread子类().start()
 * 	创建线程实际调用的是父类Thread空参的构造器
 */
class ExtendsThread extends Thread{
	@Override
	public void run(){
		System.out.println(Thread.currentThread().getName());
		try{
			Thread.sleep(3000);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}

/**
 * 实现Runnable接口，重写run方法
 * 	不论创建多少个线程，只需要创建一个Runnable接口实现类的对象
 * 	启动线程，new Thread(Runnable接口实现类的对象).start()
 * 	创建线程调用的是Thread类Runnable类型参数的构造器
 */
class ImplRunnable implements Runnable {
	private volatile int i=0;

	@Override
	public void run(){
		System.out.println(Thread.currentThread().getName() + "--" + i++);
	}
}

/**
 * 实现Callable接口，重写call方法（有返回值）
 * 	自定义类实现Callable接口时，必须指定泛型，该泛型即返回值的类型
 * 	每次创建一个新的线程，都要创建一个新的Callable接口的实现类、
 * 	如何启动线程？
 * 		（1）创建一个Callable接口的实现类的对象
 * 		（2）创建一个FutureTask对象，传入Callable类型的参数
 * 				public FutureTask(Callable<V> callable){……}
 * 		（3）调用Thread类重载的参数为Runnable的构造器创建Thread对象
 * 				将FutureTask作为参数传递
 * 				public class FutureTask<V> implements RunnableFuture<V>
 * 				public interface RunnableFuture<V> extends Runnable, Future<V>
 * 	如何获取返回值？
 * 		调用FutureTask类的get()方法
 */
class ImplCallable implements Callable<Integer>{

	@Override
	public Integer call() throws Exception{
		int result = 0;
		for(int i=0; i<10; i++){
			result += 1;
		}
		System.out.println(Thread.currentThread().getName());
		return result;
	}
}


