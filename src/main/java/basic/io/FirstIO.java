package basic.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 数据输入流测试
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/12/4 6:37 AM,
 */
public class FirstIO{
	public static void main(String[] args){
		int i=0;
		FileInputStream in = null;
		try{
			in = new FileInputStream("/Users/tony/Documents/lagou/日报/日报_2019.06");
		}catch(FileNotFoundException e){
			System.out.println("找不到文件位置");
			System.exit(-1);
			e.printStackTrace();
		}

		try{
			int num=0; //用于字节个数的计数
			while((i=in.read())!=-1){
				System.out.println((char)i); //将得到的ASCII码值转换成字符型
				num++;
			}
			in.close();
			System.out.println("传输字节个数：" + num);
		}catch(IOException e){
			System.out.println("读取文件错误");
			e.printStackTrace();
		}
	}
}
