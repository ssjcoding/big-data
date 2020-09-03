package mr.wordcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * wordcount driver
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2020/1/9 12:05 AM,
 */
public class WcDriver{
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException{
		//1、获取一个Job实例
		Job job = Job.getInstance(new Configuration());

		//2、设置类路径（Classpath），随便填写一个相关类
		job.setJarByClass(WcDriver.class);

		//3、设置Mapper和Reducer
		job.setMapperClass(WcMapper.class);
		job.setReducerClass(WcReducer.class);

		//4、设置Mapper和Reducer的输出类型
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		//设置combiner
		job.setCombinerClass(WcReducer.class);

		//5、设置输入输出数据
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		//6、提交我们的Job
		boolean b = job.waitForCompletion(true);
		System.exit(b ? 0 : 1);
	}
}
