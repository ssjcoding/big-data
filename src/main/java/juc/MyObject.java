package juc;

/**
 * ${DESCRIPTION}
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/12/14 3:38 PM,
 */
public class MyObject{
	public static void main(String[] args){
		Object object = new Object();
		System.out.println(object.getClass().getClassLoader());

		MyObject myObject = new MyObject();
		System.out.println(myObject.getClass().getClassLoader());
		System.out.println(myObject.getClass().getClassLoader().getParent());
		System.out.println(myObject.getClass().getClassLoader().getParent().getParent());
	}
}
