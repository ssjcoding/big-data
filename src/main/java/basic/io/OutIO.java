package basic.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 输入输出流测试
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/12/4 6:43 AM,
 */
public class OutIO{
	public static void main(String[] args){
		int i=0;
		FileInputStream in = null;
		FileOutputStream out = null;
		try{
			 in = new FileInputStream("/Users/tony/Documents/lagou/日报/日报_2019.06");
			 out = new FileOutputStream("/Users/tony/Documents/lagou/日报/日报_2019.06.out");

			 while((i = in.read())!=-1){
			 	out.write(i);
			 	System.out.println(i);
			 }
			 in.close();
			 out.close();
			 System.out.println("文件已复制");
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
