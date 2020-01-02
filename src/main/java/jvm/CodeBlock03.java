package jvm;

class MyCode{
	public MyCode(){
		System.out.println("Code 的构造方法1111");
	}
	{
		System.out.println("Code 的构造块2222");
	}
	static {
		System.out.println("Code 的静态代码块3333");
	}
}

/**
 * jvm加载类
 * 1、所有加载先加载静态，静态的加载一次
 * 2、静态代码块>代码块>构造方法 且静态代码块只加载一次
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/12/15 5:35 PM,
 */
public class CodeBlock03{
	static {
		System.out.println("CodeBlock03 的构造方法4444");
	}
	{
		System.out.println("CodeBlock03 的构造方法5555");
	}
	public CodeBlock03(){
		System.out.println("CodeBlock03 的构造方法6666");
	}

	public static void main(String[] args){
		System.out.println("==我是完美分割线====================CodeBlock03的main构造方法7777");
		new MyCode();
		System.out.println("--------------------------");
		new MyCode();
		System.out.println("--------------------------");
		new CodeBlock03();
	}
}
