package ik;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.IOException;
import java.io.StringReader;

/**
 * @author tonysu,
 * @version 1.0v.
 * @Create 2020/3/5 12:27 下午,
 */
public class IKTest{
	public static void main(String[] args) throws IOException{

		String text="YF-JAVA商务版权（TO C商业化)";
		StringReader sr=new StringReader(text);
		IKSegmenter ik=new IKSegmenter(sr, true);
		Lexeme lex=null;
		while((lex=ik.next())!=null){
			System.out.print(lex.getLexemeText()+"|");
		}

	}
}
