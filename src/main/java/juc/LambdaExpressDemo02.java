package juc;

@FunctionalInterface //只能有一个未实现方法
interface Foo{
	int add(int x, int y);
	default int mul(int x, int y){
		return x*y;
	}

	static int mul2(int x, int y){
		return x*y;
	}
}

/**
 * lambda表达式
 *
 * (1) 拷贝小括号，写死右箭头，落地大括号
 * (2) @FunctionalInterface
 * (3) default:多个
 * (4) static:多个
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/12/10 11:04 PM,
 */
public class LambdaExpressDemo02{
	public static void main(String[] args){
		Foo foo = (int x, int y) -> {
			System.out.println("say hello");
			return x+y;
		};
		System.out.println(foo.add(2,3));
		System.out.println(foo.mul(2,3));
		System.out.println(Foo.mul2(2,3));
	}
}
