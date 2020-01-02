package basic.io.udp;

import java.io.IOException;
import java.net.*;

/**
 * UDP Client端
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/12/4 8:02 PM,
 */
public class UdpClient{
	private String sendStr = "hello";
	private String netAddress = "255.255.255.255";
	private final int PORT = 5060;

	private DatagramSocket datagramSocket;
	private DatagramPacket datagramPacket;

	public UdpClient(){
		try{
			datagramSocket = new DatagramSocket();
			byte[] buf = sendStr.getBytes();
			InetAddress address = InetAddress.getByName(netAddress);
			datagramPacket = new DatagramPacket(buf, buf.length, address, PORT);
			datagramSocket.send(datagramPacket);
			byte[] receBuf = new byte[1024];
			DatagramPacket recePacket = new DatagramPacket(receBuf, receBuf.length);
			datagramSocket.receive(recePacket);
			String receString = new String(recePacket.getData(), 0, recePacket.getLength());
			System.out.println(receString);
			//获取服务端ip
			String serverIp = recePacket.getAddress().getHostAddress();
			System.out.println(serverIp);
		}catch(UnknownHostException e){
			e.printStackTrace();
		}catch(SocketException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public static void main(String[] args){
		for(int i=0; i<100; i++){
			new Thread(() -> {
				UdpClient udpClient = new UdpClient();
			}).start();
		}
	}
}
