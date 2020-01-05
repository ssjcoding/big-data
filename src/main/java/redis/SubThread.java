package redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 订阅者
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2020/1/5 9:19 PM,
 */
public class SubThread extends Thread{
	private JedisPool jedisPool;
	private  Subscriber subscriber = new Subscriber();

	private String channel = "channel";

	public SubThread(JedisPool jedisPool){
		super("SubThread");
		this.jedisPool = jedisPool;
	}

	@Override
	public void run(){
		System.out.println(String.format("subscribe redis, channel %s, thread will be blocked", channel));
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();   //取出一个连接
			jedis.subscribe(subscriber, channel);    //通过subscribe 的api去订阅，入参是订阅者和频道名
		} catch (Exception e) {
			System.out.println(String.format("subsrcibe channel error, %s", e));
		} finally {
			if (jedis != null) {
				jedis.close();
				System.out.println("退出订阅");
			}
		}
	}
}
