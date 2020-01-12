package mr.partition;

import mr.flow.FlowMapper;
import mr.flow.FlowReducer;
import mr.flow.FlowWritable;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * 手机号上行流量，下行流量，总流量统计
 * 样例数据
 *  手机号			上行流量		下行流量
 * 	15931672221		123			234
 *	15931672222		123			234
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2020/1/9 1:51 AM,
 */
public class PartitionerDriver{
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException{
		//1、获取一个Job实例
		Job job = Job.getInstance(new Configuration());

		//2、设置类路径（Classpath），随便填写一个相关类
		job.setJarByClass(PartitionerDriver.class);

		//3、设置Mapper和Reducer
		job.setMapperClass(FlowMapper.class);
		job.setReducerClass(FlowReducer.class);

		//设置Partition
		job.setPartitionerClass(MyPartition.class);
		job.setNumReduceTasks(5);

		//4、设置Mapper和Reducer的输出类型
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(FlowWritable.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(FlowWritable.class);

		//5、设置输入输出数据
		FileInputFormat.setInputPaths(job, new Path("/Users/tony/bigdata/input"));
		FileOutputFormat.setOutputPath(job, new Path("/Users/tony/bigdata/output"));

		//6、提交我们的Job
		boolean b = job.waitForCompletion(true);
		System.exit(b ? 0 : 1);
	}
}
