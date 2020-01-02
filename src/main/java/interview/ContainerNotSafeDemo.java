package interview;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 集合不安全类
 *
 * ArrayList
 *
 * 不要只会用，会用只不过是一个API调用工程师
 * 底层原理？
 *
 * 1 故障现象
 * 		java.util.ConcurrentModificationException
 * 2 导致原因
 *		并发争抢修改导致，参考我们的花名册签名情况。
 *		一个人正在写入，另外一个同学过来抢夺，导致数据不一致异常，并发修改异常
 * 3 解决方案
 * 		3.1 new Vector<>()
 *		3.2 Collections.synchronizedList<new ArrayList<>()>
 *		3.3 new CopyOnWriteArrayList()
 * 4 优化建议（同样的错误不犯第2次）
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/12/18 7:48 AM,
 */
public class ContainerNotSafeDemo{
	public static void main(String[] args){
		Set<String> set = new CopyOnWriteArraySet<>();

	}

	private static void arrayListNotSafe(){
		//		List<String> list = new ArrayList<>();
// 		List<String> list = Collections.synchronizedList(new ArrayList<>());

		List<String> list = new CopyOnWriteArrayList();
		Vector vector = new Vector();
		list.stream().forEach(System.out::println);

		for(int i = 0; i<3; i++){
		    new Thread(()->{
		    	list.add(UUID.randomUUID().toString().substring(0, 8));
//				vector.add(UUID.randomUUID().toString().substring(0, 8));
				System.out.println(list);
			}, String.valueOf(i)).start();
		}
	}
}
