package basic.thread;

/**
 * ${DESCRIPTION}
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/11/30 5:35 PM,
 */
public class App{
	public static void main( String[] args ){
		System.out.println("a");
		Runnable t=new Runnable() {

			@Override
			public void run() {
				System.out.println("aa");
				try {
					System.out.println(Thread.activeCount());
					Thread.sleep(10000);
					System.out.println(Thread.activeCount());

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("bb");
			}
		};
		Thread thread = new Thread(t);
		thread.start();
		Thread.yield();
		System.out.println("b");
		Thread.currentThread().stop();
		System.out.println("stopped");
	}
}
