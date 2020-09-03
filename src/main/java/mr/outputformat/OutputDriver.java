package mr.outputformat;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * ${DESCRIPTION}
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2020/2/1 9:48 AM,
 */
public class OutputDriver{

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException{
		Job job = Job.getInstance(new Configuration());
		job.setJarByClass(OutputDriver.class);
		job.setOutputFormatClass(MyOutputFormat.class);
		FileInputFormat.setInputPaths(job, new Path("/Users/tony/bigdata/input"));
		FileOutputFormat.setOutputPath(job, new Path("/Users/tony/bigdata/output"));

		boolean b = job.waitForCompletion(true);
		System.exit(b?0:1);
	}
}
