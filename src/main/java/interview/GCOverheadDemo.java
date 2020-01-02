package interview;

import java.util.ArrayList;
import java.util.List;

/**
 * JVM参数配置演示
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 *
 * GC回收时间过长会抛出OutOfMemoryError，过长的定义是，超过98%的时间来做GC并且回收了不到2%的堆内存
 * 连续多次GC都只回收了不到2%的极端情况下才会抛出。假如不抛出GC overhead limit 错误会发生什么？
 * 那就是GC清理的这点内存很快会再次堆满，迫使GC再次执行，这样就形成恶性循环，
 * CPU使用率一直是100%，而GC却没有任何成果
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/12/25 1:07 AM,
 */
public class GCOverheadDemo{
	public static void main(String[] args){
		int i=0;
		List<String> list = new ArrayList<>();
		try{
			while(true){
				list.add(String.valueOf(++i).intern());
			}
		}catch(Throwable e){
			System.out.println("=================");
			e.printStackTrace();
			throw e;
		}

 	}
}
