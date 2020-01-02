package interview;

/**
 * 大量对象，使得堆内存失败
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/12/25 12:58 AM,
 */
public class JavaHeapSpaceDemo{
	public static void main(String[] args){
		String str = "atguigu";
		byte[] bytes = new byte[80 * 1024 * 1024];

	}
}
