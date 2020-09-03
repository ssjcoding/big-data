package interview;

import java.lang.ref.SoftReference;

/**
 * 软引用：只要内存够用就不回收，当内存不足时 会被回收
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/12/24 12:22 AM,
 */
public class SoftReferenceDemo{
	public static void softRef_Memory_Enough(){
		Object object1 = new Object();
		SoftReference<Object> softReference = new SoftReference<>(object1);
		System.out.println(object1);
		System.out.println(softReference.get());
		object1 = null;
		System.gc();
		System.out.println(object1);
		System.out.println(softReference.get());
	}

	/**
	 * 设置-Xms10m -Xmx10m
	 */
	public static void softRef_Memory_Not_Enough(){
		Object object1 = new Object();
		SoftReference<Object> softReference = new SoftReference<>(object1);
		System.out.println(object1);
		System.out.println(softReference.get());
		object1 = null;
		try{
			byte[] bytes = new byte[30 * 1024 * 1024];
		}catch(OutOfMemoryError e){
		}finally{
			System.out.println(object1);
			System.out.println(softReference.get());
		}
	}

	public static void main(String[] args){
//		softRef_Memory_Enough();
		softRef_Memory_Not_Enough();
	}
}
