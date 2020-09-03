package basic.io.tcp;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * 发送端
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/12/4 8:24 AM,
 */
public class TCPClient{
	public static void main(String[] args){
		try{
			//创建Socket对象
			Socket s = new Socket(InetAddress.getByName("127.0.0.1"), 10086);
			//获取输出流对象
			OutputStream os = s.getOutputStream();
			//发送数据
			String str = "This TCP, im comming";
			os.write(str.getBytes());
			//释放
			s.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
