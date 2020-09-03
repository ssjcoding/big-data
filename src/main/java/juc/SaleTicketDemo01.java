package juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket{
	private int number = 30;
	Lock lock = new ReentrantLock();
	public void sale(){
		lock.lock();
		try{
			if(number > 0){
				System.out.println(Thread.currentThread().getName() +" "+ (number--) + ",\t还剩票数" + number);
			}
		}finally{
			lock.unlock();
		}
	}
}

/**
 * 题目：三个售票员			卖出			30张票
 * 笔记：如何编写企业级的多线程代码
 * 	固定的编程套路+模板是什么？
 *
 * 	1 在高内聚低耦合的前提下，线程		操作			内部类
 * 		1.1 一言不合，先创建一个资源类
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/12/10 12:21 AM,
 */
public class SaleTicketDemo01{
	public static void main(String[] args){
		Ticket ticket = new Ticket();
		new Thread(() -> {for(int i=0; i<40; i++){ticket.sale();}}, "A").start();
		new Thread(() -> {for(int i=0; i<40; i++){ticket.sale();}}, "B").start();
		new Thread(() -> {for(int i=0; i<40; i++){ticket.sale();}}, "C").start();

	}
}
