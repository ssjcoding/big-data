package basic.thread;

/**
 * 需求是子线程执行结束，主线程等待启动的子线程都结束之后再结束
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/11/30 7:00 PM,
 */
public class IsAliveTest{
	public static void main(String[] args) throws Exception {
		Thread t = new Thread(new ThreadDemo());
		t.start();
		System.out.println("thread t status = " + t.isAlive());
		System.out.println("thread main status = " + Thread.currentThread().isAlive());
	}
}

class ThreadDemo implements Runnable {
	@Override
	public void run(){
		Thread t = Thread.currentThread();
		System.out.println("status=" + t.isAlive());
		try{
			Thread.sleep(3000L);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}


