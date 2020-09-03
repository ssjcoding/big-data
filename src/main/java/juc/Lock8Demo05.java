package juc;

import java.util.concurrent.TimeUnit;

class Phone{
	public static synchronized void sendEmail() throws Exception{
		TimeUnit.SECONDS.sleep(4);
		System.out.println("*****sendEmail");
	}

	public synchronized void sendSMS() throws Exception{
		System.out.println("*****sendSMS");
	}

	public void sayHello(){
		System.out.println("*****sayHello");
	}
}

/**
 * 会锁资源类，锁的是对象，不是方法
 * 8 lock
 * 1 标准访问，请问先打印邮件还是短信									答案：不确定
 * 2 暂停4秒钟在邮件方法，请问先打印邮件还是短信							答案：邮件
 * 3 新增普通sayHello方法，请问先打印邮件还是hello						答案：hello
 * 4 两部手机，请问先打印邮件还是短信									答案：短信
 * 5 两个静态同步方法，同一部手机，请问先打印邮件还是短信					答案：邮件
 * 6 两个静态同步方法，两部手机，请问先打印邮件还是短信					答案：邮件
 * 7 1个静态同步方法，1个普通同步方法，同一部手机，请问先打印邮件还是短信	答案：短信
 * 8 1个静态同步方法，1个普通同步方法，两部手机，请问先打印邮件还是短信		答案：短信
 *
 * * 一个对象里面如果有多个synchronized方法，某一时刻内，只要一个线程去调用其中的一个synchronized方法了
 * * 其它线程都只能等待，换句话说，某一时刻内，只能有唯一一个线程去访问这些synchronized方法
 * * 锁的是当前对象this，被锁定后，其它线程都不能进入当前对象的其它的synchronized方法
 * * 加一个普通方法后发现和同步锁无关
 * * 换成两个对象后，不是同一把锁了，情况立刻变化
 * * static锁的是全局锁
 * * synchronized实现同步的基础：java中的每一个对象都可以作为锁
 * * 具体表现为以下3种形式。
 * * 对于普通同步方法，锁是当前实例对象。
 * * 对于同步方法块，锁是synchronized括号里配置的对象
 * * 对于静态同步方法，锁的是当前Class对象
 *
 * * 当一个线程试图访问同步代码块时，它首先必须得到锁，退出或抛出异常时必须释放锁
 * * 也就是说如果一个实例对象的非静态同步方法获取锁后，该实例对象的其它非静态同步方法必须等待获取锁的方法释放后才能获取锁
 * * 可是别的实例对象的非静态同步方法因为跟该市里对象的非静态同步方法用的是不同的锁
 * * 所以无需等待该实例对象已获取锁的费静态同步方法释放锁就可以获取他们自己的锁
 *
 * * 所有的静态同步方法用的也是同一把锁--类对象Class本身，
 * * 这两把锁是两个不同的对象，所以静态同步方法与非静态同步方法之间是不会有竞态条件的
 * * 而不管是同一个实例对象的静态方法之间，
 * * 还是不同的实例对象的静态方法之间，只要它们同一个类的实例对象！
 *
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/12/11 1:06 AM,
 */
public class Lock8Demo05{
	public static void main(String[] args) throws InterruptedException{
		Phone phone = new Phone();
		Phone phone2 = new Phone();
		new Thread(() -> {
			try{
				Phone.sendEmail();
			}catch(Exception e){
				e.printStackTrace();
			}
		}, "A").start();

		Thread.sleep(100);
		new Thread(() -> {
			try{
//				phone.sendSMS();
//				phone.sayHello();
				phone.sendSMS();
			}catch(Exception e){
				e.printStackTrace();
			}
		}, "B").start();
	}

}
