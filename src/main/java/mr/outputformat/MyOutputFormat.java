package mr.outputformat;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * 重写文件输出
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2020/2/1 9:46 AM,
 */
public class MyOutputFormat extends FileOutputFormat<LongWritable, Text>{
	@Override
	public RecordWriter<LongWritable, Text> getRecordWriter(TaskAttemptContext job) throws IOException, InterruptedException{
		MyRecordWriter myRecordWriter = new MyRecordWriter();
		myRecordWriter.initialize(job);
		return myRecordWriter;
	}
}
