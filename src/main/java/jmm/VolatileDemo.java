package jmm;

import java.util.concurrent.atomic.AtomicInteger;

class MyData{
	volatile int number = 0;
	public void addTo60(){
		this.number = 60;
	}

	public void addPlusPlus(){
		number++;
	}

	AtomicInteger atomicInteger = new AtomicInteger();
	public void addAtomic(){
		atomicInteger.getAndIncrement();
	}
}

/**
 * 1 验证volatile的可见性
 * 	1.1 假如 int number = 0；， number变量之前根本没有添加volatile关键字修饰
 * 	1.2 添加了volatile可以解决可见性问题
 *
 * 2 验证volatile不保证原子性
 * 	2.1 原子性指的是什么意思？
 * 		不可分割，完整性，也即某个线程正在做某个具体业务时，中间不可以被加塞或者被分割。
 * 		需要整体完整要么同时完成，要么同时失败
 *
 * 	2.2 volatile不保证原子性的按理延时
 * 	2.3 why?
 * 		i++ 底层编译后会变成三条指令，如果在写入主存时被挂起，则丢失写
 * 	2.4 怎么解决原子性?
 *		* 加synchronized
 *		* 使用AtomicInteger
 *
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/12/15 10:21 PM,
 */
public class VolatileDemo{
	public static void main(String[] args){//main是一切方法的运行入口
		MyData myData = new MyData();
		for(int i = 0; i<20; i++){
			new Thread(()->{
				for(int j = 0; j<1000; j++){
					myData.addPlusPlus();
					myData.addAtomic();
				}
			}, String.valueOf(i)).start();
		}

		//需要等待上面20个线程都全部计算完后，再用main线程去的最终的结果值看是多少
		while(Thread.activeCount() > 2){
			Thread.yield();
		}

		System.out.println(Thread.currentThread().getName() + "\t int type finally number value:" + myData.number);
		System.out.println(Thread.currentThread().getName() + "\t AtomicInteger finally number value:" + myData.atomicInteger);
	}

	//volatile可以保证可见性，及时通知其它线程，主物理内存的值已经被修改
	private static void seeOkByVolatile(){
		MyData myData = new MyData();
		new Thread(()->{
			try{
			    Thread.sleep(3000);
			}catch(InterruptedException e){
			    e.printStackTrace();
			}
			myData.addTo60();
			System.out.println(Thread.currentThread().getName() + " 添加成功");
		}, "A").start();

		while(myData.number == 0){

		}

		System.out.println("退出");
	}
}
