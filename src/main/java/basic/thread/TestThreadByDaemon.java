package basic.thread;

import java.io.IOException;

/**
 * 测试守护线程
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/11/30 6:42 PM,
 */
public class TestThreadByDaemon extends Thread{
	@Override
	public void run(){
		for(int i=0;; i++){
			try{
				Thread.sleep(1000L);
				System.out.println("守护线程存在");
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args){
		TestThreadByDaemon testThreadByDaemon = new TestThreadByDaemon();
		testThreadByDaemon.setDaemon(true);//调试时可以设置为false，那么这个程序是个死循环，没有退出条件。设置为true，即可主线程结束，test线程也结束
		testThreadByDaemon.start();
		System.out.println("isDaemon = " + testThreadByDaemon.isDaemon());
		try {
			System.in.read();// 接受输入，使程序在此停顿，一旦接收到用户输入，main线程结束，守护线程自动结束
			System.out.println("守护线程自动结束");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
