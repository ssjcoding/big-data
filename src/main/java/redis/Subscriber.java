package redis;

import redis.clients.jedis.JedisPubSub;

import java.util.concurrent.TimeUnit;

/**
 * 订阅者
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2020/1/5 9:19 PM,
 */
public class Subscriber extends JedisPubSub{
	public Subscriber(){}
	@Override
	public void onMessage(String channel, String message) {       //收到消息会调用
		System.out.println(String.format("receive redis published message, channel %s, message %s", channel, message));
		try{TimeUnit.SECONDS.sleep(10);}catch(InterruptedException e){e.printStackTrace();}
	}
	@Override
	public void onSubscribe(String channel, int subscribedChannels) {    //订阅了频道会调用
		System.out.println(String.format("subscribe redis channel success, channel %s, subscribedChannels %d",
				channel, subscribedChannels));
	}
	@Override
	public void onUnsubscribe(String channel, int subscribedChannels) {   //取消订阅 会调用
		System.out.println(String.format("unsubscribe redis channel, channel %s, subscribedChannels %d",
				channel, subscribedChannels));

	}
}
