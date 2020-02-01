package mr.outputformat;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * ${DESCRIPTION}
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2020/2/1 9:47 AM,
 */
public class MyRecordWriter extends RecordWriter<LongWritable, Text>{

	private FSDataOutputStream atguigu;
	private FSDataOutputStream other;

	/**
	 * 初始化方法
	 * @param job
	 * @throws IOException
	 */
	public void initialize(TaskAttemptContext job) throws IOException{
		String outDir = job.getConfiguration().get(FileOutputFormat.OUTDIR);
		FileSystem fileSystem = FileSystem.get(job.getConfiguration());
		atguigu = fileSystem.create(new Path(outDir + "/atguigu.log"));
		other = fileSystem.create(new Path(outDir + "/other.log"));
	}

	/**
	 * 实现写出方法
	 * @param key
	 * @param value
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@Override
	public void write(LongWritable key, Text value) throws IOException, InterruptedException{
		String line = value.toString() + "\n";
		if(line.contains("atguigu")){
			atguigu.write(line.getBytes());
		}else {
			other.write(line.getBytes());
		}
	}

	/**
	 * 关闭流
	 * @param context
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@Override
	public void close(TaskAttemptContext context) throws IOException, InterruptedException{
		IOUtils.closeStream(atguigu);
		IOUtils.closeStream(other);
	}
}
