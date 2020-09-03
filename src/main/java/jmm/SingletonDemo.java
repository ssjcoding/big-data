package jmm;

/**
 * 基于DCL（双端检索机制）的单例模式
 * 如果不加volatile存在指令重排，有可能导致，获取值的时候还未创建，只进行了声明，导致线程不安全
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/12/16 12:24 AM,
 */
public class SingletonDemo{
	private static volatile SingletonDemo instance = null;
	private SingletonDemo(){
		System.out.println(Thread.currentThread().getName() + "\t 我是构造方法SingletonDemo()");
	}

	//DCL (Double Check Lock 双端检索机制)
	public static SingletonDemo getInstance(){
		if(instance == null){
			synchronized(SingletonDemo.class){
				if(instance == null){
					instance = new SingletonDemo();
				}
			}
		}
		return instance;
	}

	public static void main(String[] args){
		System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
		System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
		System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
	}
}
