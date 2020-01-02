package jmm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 1 CAS是什么？==> compareAndSet
 * 		比较并交换
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/12/16 7:42 AM,
 */
public class CASDemo{
	public static void main(String[] args){
		AtomicInteger atomicInteger = new AtomicInteger(5);
		atomicInteger.getAndIncrement();
		System.out.println(atomicInteger.compareAndSet(5,2019) + "\t" + atomicInteger.get());
		System.out.println(atomicInteger.compareAndSet(5,2012) + "\t" + atomicInteger.get());
	}
}
