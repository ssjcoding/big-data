package basic.io.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 接收端
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/12/4 8:26 AM,
 */
public class TCPServer{
	public static void main(String[] args){
		try{
			//创建Socket对象
			ServerSocket ss = new ServerSocket(10086);
			//监听（阻塞）
			Socket s = ss.accept();
			//获取输入流对象
			InputStream is = s.getInputStream();
			//获取数据
			byte[] bys = new byte[1024];
			int len;
			len = is.read(bys);
			//输出数据
			InetAddress address = s.getInetAddress();
			System.out.println("sender:" + address);
			System.out.println(new String(bys, 0, len));
			//释放
			s.close();
		}catch(IOException e){
			e.printStackTrace();
		}

	}
}
