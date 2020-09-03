package mr.flow;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * 样例数据
 *  手机号			上行流量		下行流量
 * 	15931672221		123			234
 *	15931672222		123			234
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2020/1/9 1:51 AM,
 */
public class FlowMapper extends Mapper<LongWritable, Text, Text, FlowWritable>{
	private Text phone = new Text();
	private FlowWritable flowWritable = new FlowWritable();
	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException{
		String line = value.toString();
		String[] paras = line.split("\t");
		if(paras.length == 3){
			this.phone.set(paras[0]);
			this.flowWritable.set(Long.valueOf(paras[1]), Long.valueOf(paras[2]));
			context.write(this.phone, this.flowWritable);
		}
	}
}
