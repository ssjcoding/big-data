package mr.inputformat;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * 需求：
 * 		将多个小文件合并成一个SequenceFile（SequenceFile文件是Hadoop用来存储二进制形式的key-value对的文件格式），
 * 		SequenceFile里面存储着多个文件，存储的形式为文件路径+名称为key，文件内容为value
 *
 * 	输入数据：
 * 		one.txt	two.txt	three.txt
 *
 * 	输出文件
 * 		part-r-00000
 *
 * 	分析：
 * 		每个文件不论大小都不进行切片，所以需要重写是否可以切片函数
 * 		需要key为文件路径+名称，value为文件内容，所以需要重写RecordReader
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2020/1/12 12:58 AM,
 */
public class WholeFileDriver{

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException{
		Job job = Job.getInstance(new Configuration());

		job.setJarByClass(WholeFileDriver.class);

		//因为Mapper与Reducer都不需要处理任何事情所以不用设置，采用默认的就可以
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(BytesWritable.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);

		job.setInputFormatClass(WholeFileInputFormat.class);

		FileInputFormat.setInputPaths(job, new Path("/Users/tony/bigdata/input"));
		FileOutputFormat.setOutputPath(job, new Path("/Users/tony/bigdata/output"));

		boolean b = job.waitForCompletion(true);
		System.exit(b ? 0:1);
	}
}
