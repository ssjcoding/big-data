package interview;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * ${DESCRIPTION}
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/12/24 1:00 AM,
 */
public class PhantomReferenceDemo{
	public static void main(String[] args){
		Object object = new Object();
		ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
		PhantomReference<Object> phantomReference = new PhantomReference<>(object, referenceQueue);

		System.out.println(object);
		System.out.println(phantomReference.get());
		System.out.println(referenceQueue.poll());

		System.out.println("======================");

		object = null;
		System.gc();
		try{Thread.sleep(50);}catch(InterruptedException e){e.printStackTrace();}
		System.out.println(object);
		System.out.println(phantomReference.get());
		System.out.println(referenceQueue.poll());
	}
}
