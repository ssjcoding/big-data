package basic.io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 使用FileRead，FileWriter复制文件
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/12/4 6:48 AM,
 */
public class FieldWriter{
	public static void main(String[] args){
		FileReader fr = null;
		FileWriter fw = null;
		try{
			fr = new FileReader("/Users/tony/Documents/lagou/日报/日报_2019.06");
			fw = new FileWriter("/Users/tony/Documents/lagou/日报/日报_2019.06.out");
			int i = 0;
			while((i=fr.read())!=-1){
				fw.write(i);
				System.out.println((char)i);//将得到的ASCCI码值转换成字符型
			}
			fr.close();
			fw.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
