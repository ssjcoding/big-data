package interview;

import java.lang.ref.WeakReference;

/**
 * 弱引用：不管JVM内存够不够用，都会回收
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/12/24 12:32 AM,
 */
public class WeakReferenceDemo{
	public static void main(String[] args){
		Object object = new Object();
		WeakReference<Object> weakReference = new WeakReference<>(object);
		System.out.println(object);
		System.out.println(weakReference.get());
		object = null;
		System.gc();
		System.out.println(object);
		System.out.println(weakReference.get());
	}
}
