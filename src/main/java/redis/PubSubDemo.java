package redis;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 发布订阅者主类
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2020/1/5 9:22 PM,
 */
public class PubSubDemo{
	public static void main(String[] args){
		// 连接redis服务端
		JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), "127.0.0.1", 6379);

		System.out.println(String.format("redis pool is starting, redis ip %s, redis port %d", "127.0.0.1", 6379));

		SubThread subThread = new SubThread(jedisPool);  //订阅者1
		subThread.start();

		SubThread subThread2 = new SubThread(jedisPool);  //订阅者2
		subThread2.start();

		SubThread subThread3 = new SubThread(jedisPool);  //订阅者2
		subThread3.start();

		Publisher publisher = new Publisher(jedisPool);    //发布者
		publisher.start();
	}
}
