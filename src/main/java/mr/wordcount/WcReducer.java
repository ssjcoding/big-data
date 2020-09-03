package mr.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * wordcount reducer 实现
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2020/1/9 12:06 AM,
 */
public class WcReducer extends Reducer<Text, IntWritable, Text, IntWritable>{

	private IntWritable total = new IntWritable();

	@Override
	protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException{
		//做累加
		int sum=0;
		for(IntWritable value : values){
			sum += value.get();
		}
		//包装结果并输出
		total.set(sum);
		context.write(key, total);
	}
}
