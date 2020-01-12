package mr.writable;

import mr.flow.FlowWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * ${DESCRIPTION}
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2020/1/12 1:58 PM,
 */
public class SortMapper extends Mapper<LongWritable, Text, FlowWritable, Text>{
	private Text phone = new Text();
	private FlowWritable flowWritable = new FlowWritable();
	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException{
		String line = value.toString();
		String[] paras = line.split("\t");
		if(paras.length == 4){
			this.phone.set(paras[0]);
			this.flowWritable.set(Long.valueOf(paras[1]), Long.valueOf(paras[2]));
			context.write(this.flowWritable, this.phone);
		}
	}
}
