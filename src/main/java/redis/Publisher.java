package redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import scala.tools.jline_embedded.internal.InputStreamReader;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * 发布者
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2020/1/5 9:17 PM,
 */
public class Publisher extends Thread{
	private JedisPool jedisPool;

	public Publisher(JedisPool jedisPool){
		this.jedisPool = jedisPool;
	}

	@Override
	public void run(){
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		Jedis jedis = jedisPool.getResource();   //连接池中取出一个连接
		while (true) {
			String line = null;
			try {
				line = reader.readLine();
				if (!"quit".equals(line)) {
					System.out.println("输入内容：" + line);
					jedis.publish("channel", line);   //从 mychannel 的频道上推送消息
				} else {
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
