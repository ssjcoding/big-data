package mr.writable;

import mr.flow.FlowWritable;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * 根据Flow产生的结果再次对总流量进行排序
 * 		输入数据:
 * 			第一次处理后的数据
 * 			手机号			上行流量	下行流量	总流量
 *			13631672222     124     237     361
 *			13731672222     125     238     363
 *			13831672222     126     239     365
 *			13931672222     127     230     357
 *			15931672221     249     466     715
 *			15931672222     245     471     716
 *
 *
 *
 * 		期望输出:
 * 		13509468723	7335	110349	117684
 *		13736230513	2481	24681	27162
 *		13956435636	132		1512	1644
 *		13846544121	264		0		264
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2020/1/12 1:58 PM,
 */
public class FlowDriver{
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException{
		//1、获取一个Job实例
		Job job = Job.getInstance(new Configuration());

		//2、设置类路径（Classpath），随便填写一个相关类
		job.setJarByClass(FlowWritable.class);

		//3、设置Mapper和Reducer
		job.setMapperClass(SortMapper.class);
		job.setReducerClass(SortReducer.class);

		//4、设置Mapper和Reducer的输出类型
		job.setMapOutputKeyClass(FlowWritable.class);
		job.setMapOutputValueClass(Text.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(FlowWritable.class);

		//5、设置输入输出数据
		FileInputFormat.setInputPaths(job, new Path("/Users/tony/bigdata/output"));
		FileOutputFormat.setOutputPath(job, new Path("/Users/tony/bigdata/output2"));

		//6、提交我们的Job
		boolean b = job.waitForCompletion(true);
		System.exit(b ? 0 : 1);
	}
}
