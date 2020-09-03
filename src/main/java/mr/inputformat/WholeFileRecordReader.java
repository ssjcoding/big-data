package mr.inputformat;

import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

/**
 * 自定义RecordReader，处理一个文件，把这个文件读成一个KV值
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2020/1/11 11:52 PM,
 */
public class WholeFileRecordReader extends RecordReader{

	/**
	 * 是否读取标志位
	 */
	private boolean notRead = true;

	private Text key = new Text();

	private BytesWritable value = new BytesWritable();

	private FSDataInputStream fsDataInputStream;

	private FileSplit fileSplit;

	/**
	 * 初始化方法，框架会在开始的时候调用一次
	 * @param split
	 * @param context
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@Override
	public void initialize(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException{
		//转换切片类型到文件切片
		fileSplit = (FileSplit) split;
		//通过切片获取路径
		Path path = fileSplit.getPath();
		//通过路径获取文件系统
		FileSystem fs = path.getFileSystem(context.getConfiguration());
		//开流
		fsDataInputStream = fs.open(path);
	}

	/**
	 * 读取下一组key，value对
	 * @return 成功读取到返回true，读完了，则返回false
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@Override
	public boolean nextKeyValue() throws IOException, InterruptedException{
		if(notRead){
			//具体读文件的内容
			//读key
			key.set(fileSplit.getPath().toString());
			//读取value
			byte[] buf = new byte[(int)fileSplit.getLength()];
			fsDataInputStream.read(buf);
			value.set(buf, 0, buf.length);
			notRead = false;
			return true;
		}else {
			return false;
		}
	}

	/**
	 * 获取当前key
	 * @return 当前key
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@Override
	public Object getCurrentKey() throws IOException, InterruptedException{
		return key;
	}

	/**
	 * 获取当前值
	 * @return 当前value
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@Override
	public Object getCurrentValue() throws IOException, InterruptedException{
		return value;
	}

	/**
	 * 当前数据读取的进度
	 * @return 当前进度
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@Override
	public float getProgress() throws IOException, InterruptedException{
		return notRead ? 0 : 1;
	}

	/**
	 * 关闭资源
	 * @throws IOException
	 */
	@Override
	public void close() throws IOException{
		IOUtils.closeStream(fsDataInputStream);
	}
}
