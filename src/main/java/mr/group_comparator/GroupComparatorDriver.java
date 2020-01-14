package mr.group_comparator;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * 分组排序driver
 * 	需求：
 *		取出每个订单中最高的价格商品
 *	思路：
 *		按照订单分组，然后按照商品价格排序
 * 	样例数据：
 * 		订单id	商品id	价格
 * 		0000002 Pdt_05  722.4
 *		0000002 Pdt_03  522.8
 *		0000003 Pdt_06  232.8
 *		0000001 Pdt_01  222.8
 *		0000002 Pdt_04  122.4
 *		0000001 Pdt_02  33.8
 *		0000003 Pdt_02  33.8
 *	预期输出：
 *		0000002	722.4
 *		0000003	232.8
 *		0000001	222.8
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2020/1/14 11:29 PM,
 */
public class GroupComparatorDriver{

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException{
		Job job = Job.getInstance(new Configuration(), "group comparator");

		job.setJarByClass(GroupComparatorDriver.class);

		job.setMapperClass(GroupComparatorMapper.class);
		job.setReducerClass(GroupComparatorReducer.class);

		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(ProductWritable.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(ProductWritable.class);

		FileInputFormat.setInputPaths(job, new Path("/Users/tony/bigdata/gc_input"));
		FileOutputFormat.setOutputPath(job, new Path("/Users/tony/bigdata/gc_output"));

		boolean status = job.waitForCompletion(true);
		System.exit(status ? 0 : 1);

	}

}
