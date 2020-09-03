package interview;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * JVM参数
 * -XX:MetaspaceSize=8m -XX:MaxMetaspaceSize=8m
 *
 * Java8及之后的版本使用Metaspace来替代永久代
 *
 * Metaspace是方法区在HotSpot中的实现，它与持久代最大的区别在于：Metaspace并不在虚拟机内存中而是使用本地内存
 * 也即在java8中，class metadata（the virtual machines internal persentation of java class），被存储在叫做
 * Metaspace的native memory
 *
 * 永久代（java8后会被元空间Metaspace取代了）存放以下信息：
 *
 * 虚拟机加载的类信息
 * 常量池
 * 静态变量
 * 即时编译后的代码
 *
 * 模拟Metaspace空间溢出，我们不断生成类往元空间灌，类占据的空间总是会超过Metaspace指定的空间大小的
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/12/25 2:03 AM,
 */
public class MetaspaceOOMTest{

	static class OOMTest{

	}

	public static void main(String[] args){
		int i=0;
		try{
			while(true){
				i++;
				Enhancer enhancer = new Enhancer();
				enhancer.setSuperclass(OOMTest.class);
				enhancer.setUseCache(false);
				enhancer.setCallback((MethodInterceptor)(o, method, objects, methodProxy) -> methodProxy.invokeSuper(o, args));
			}
		}catch(Throwable e){
			System.out.println(i);
			e.printStackTrace();
		}
	}
}
