package basic.io.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;

/**
 * udp连接，用于动态ip, pos向255.255.255.255：5060发送请求即可
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/12/4 7:51 PM,
 */
public class UdpServer extends Thread implements Runnable{
	private final int MAX_LENGTH = 1024;
	private final int PORT = 5060;
	private DatagramSocket datagramSocket;

	@Override
	public void run(){
		try{
			byte[] buffer = new byte[MAX_LENGTH];
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			receive(packet);
			String receStr = new String(packet.getData(), 0, packet.getLength());
			System.out.println("接收数据包：" + receStr);
			byte[] bt = new byte[packet.getLength()];
			System.arraycopy(packet.getData(), 0, bt, 0, packet.getLength());
			System.out.println(packet.getAddress().getHostAddress() + ": " + packet.getPort() + ":" + Arrays.toString(bt));
			packet.setData(bt);
			response(packet);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("udp线程出现异常：" + e.toString());
		}
	}

	public void receive(DatagramPacket packet) throws Exception{
		datagramSocket.receive(packet);
	}

	public void response(DatagramPacket packet) throws Exception{
		datagramSocket.send(packet);
	}

	/**
	 * 初始化连接
	 */
	public void init(){
		try{
			datagramSocket = new DatagramSocket(PORT);
		}catch(Exception e){
			datagramSocket = null;
			System.out.println("udp服务端启动失败");
			e.printStackTrace();
		}
	}

	public static void main(String[] args){
		UdpServer udpServer = new UdpServer();
		udpServer.start();
	}
}
