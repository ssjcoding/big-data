package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * 文件读取类
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2018/7/6 下午2:03,
 */
public class FileReaderUtil{

	public static String readFile(String filePath){
		File file = new File(filePath);
		BufferedReader reader = null;
		StringBuilder result = new StringBuilder();
		try{
			reader = new BufferedReader(new FileReader(file));
			String line = null;
			while((line = reader.readLine()) != null){
				result.append(line);
			}
		}catch(IOException e){
			System.out.println("文件读取失败");
			e.printStackTrace();
		}finally{
			if(reader != null){
				try{
					reader.close();
				}catch(IOException e){
					System.out.println("文件关闭失败");
					e.printStackTrace();
				}
			}
		}
		return result.toString();
	}
}
