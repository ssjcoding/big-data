package mr.inputformat;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

import java.io.IOException;

/**
 * 重写FileInputFormat
 * 		使得可以按文件读取数据
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2020/1/11 5:15 PM,
 */
public class WholeFileInputFormat extends FileInputFormat<Text, BytesWritable>{

	/**
	 * Is the given filename splitable? Usually, true, but if the file is
	 * stream compressed, it will not be.
	 *
	 * @param context the job context
	 * @param filename the file name to check
	 * @return is this file splitable?
	 */
	@Override
	protected boolean isSplitable(JobContext context, Path filename){
		return false;
	}


	/**
	 * Create a record reader for a given split. The framework will call
	 *
	 * @param split the split to be read
	 * @param context the information about the task
	 * @return a new record reader
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@Override
	public RecordReader<Text, BytesWritable> createRecordReader(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException{
		return new WholeFileRecordReader();
	}
}
