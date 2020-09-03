package interview;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache{ //资源类

	private volatile Map<String, Object> cache = new HashMap<>();
	private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

	public void put(String key, Object value){
		reentrantReadWriteLock.writeLock().lock();
		try{
			System.out.println(Thread.currentThread().getName() + " 开始写入");
			cache.put(key, value);
			System.out.println(Thread.currentThread().getName() + " 写入完成 " + key);
		}catch(Exception e){
		    e.printStackTrace();
		}finally{
			reentrantReadWriteLock.writeLock().unlock();
		}

	}

	public void get(String key){
		reentrantReadWriteLock.readLock().lock();
		try{
			System.out.println(Thread.currentThread().getName() + " 开始读取");
			try{
			    TimeUnit.SECONDS.sleep(1);
			}catch(InterruptedException e){
			    e.printStackTrace();
			}
			Object result = cache.get(key);
			System.out.println(Thread.currentThread().getName() + " 读取完成 " + result);
		}catch(Exception e){
		    e.printStackTrace();
		}finally{
			reentrantReadWriteLock.readLock().unlock();
		}
	}

}

/**
 * 多个线程同时读一个资源类没有任何问题，所以为了满足并发量，读取共享资源应该可以同时进行。
 * 但是
 * 如果有一个线程想去写共享资源来，就不应该再有其他线程可以对该资源进行读或写
 * 小总结：
 * 		读-读能共存
 * 		读-写不能共存
 * 		写-写不能共存
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/12/19 10:45 PM,
 */
public class ReadWriteLockDemo{
	public static void main(String[] args){
		MyCache myCache = new MyCache();
		for(int i = 0; i<5; i++){
			final int tmpI = i;
		    new Thread(()->{
		    	myCache.put(String.valueOf(tmpI), String.valueOf(tmpI));
			}, String.valueOf(i)).start();
		}

		for(int i = 0; i<5; i++){
			final int tmpI = i;
			new Thread(()->{
		    	myCache.get(String.valueOf(tmpI));
			}, String.valueOf(i)).start();
		}
	}
}
