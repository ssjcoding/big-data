package basic.io;

import java.io.IOException;
import java.net.Socket;

/**
 * ${DESCRIPTION}
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/12/4 8:18 AM,
 */
public class SocketText{
	public static void main(String[] args){
		try{
			Socket s = new Socket("www.openlab.com.cn", 13);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
