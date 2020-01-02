package interview;

/**
 * 强引用：死了都不收
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/12/24 12:16 AM,
 */
public class StrongReferenceDemo{
	public static void main(String[] args){
		Object object = new Object();
		Object object1 = object;
		object = null;
		System.gc();
		System.out.println(object1);
	}
}
