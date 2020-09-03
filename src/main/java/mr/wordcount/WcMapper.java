package mr.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * wordcount mapper 实现
 * 	Mapper阶段：
 * 		(1) 用户自定义的Mapper要继承自己的父类
 * 		(2) Mapper的输入数据是KV对的形式（KV的类型可自定义）
 * 		(3) Mapper中的业务逻辑写在map()方法中
 * 		(4)	Mapper的输出数据是KV对的形式（KV的类型可自定义）
 * 		(5) map()方法（MapTask进程）对每一个<K,V>调用一次
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2020/1/9 12:06 AM,
 */
public class WcMapper extends Mapper<LongWritable, Text, Text, IntWritable>{

	/**
	 * 防止大量new对象，所以写在上面
	 * 大量new对象会导致gc，使得程序变慢
	 */
	private Text word = new Text();
	private IntWritable one = new IntWritable(1);

	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException{
		// 拿到这一行数据
		String line = value.toString();
		//按照空格切分数据
		String[] words = line.split(" ");

		//遍历数据，把单词变成（word, 1）的形式交给框架
		for(String word : words){
			this.word.set(word);
			context.write(this.word, this.one);
		}
	}
}
