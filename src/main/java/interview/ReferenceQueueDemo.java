package interview;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * ${DESCRIPTION}
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/12/24 12:57 AM,
 */
public class ReferenceQueueDemo{
	public static void main(String[] args){
		Object object = new Object();
		ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
		WeakReference<Object> weakReference = new WeakReference<>(object, referenceQueue);
		System.out.println(object);
		System.out.println(weakReference.get());
		System.out.println(referenceQueue.poll());

		object=null;
		System.out.println("======================");

		System.gc();
		System.out.println(object);
		System.out.println(weakReference.get());
		System.out.println(referenceQueue.poll());
	}
}
