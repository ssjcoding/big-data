package basic.jni;

/**
 * Java调用C的"Hello，JNI"
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/11/30 10:32 PM,
 */
public class HelloNative{
	static{
		System.loadLibrary("HelloNative");
	}

	public static native void sayHello();

	public static void mian(String[] args){
		HelloNative.sayHello();
	}
}
