package mr.flow;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * ${DESCRIPTION}
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2020/1/9 1:51 AM,
 */
public class FlowReducer extends Reducer<Text, FlowWritable, Text, FlowWritable>{
	private FlowWritable result = new FlowWritable();
	@Override
	protected void reduce(Text key, Iterable<FlowWritable> values, Context context) throws IOException, InterruptedException{
		long upFlowSum = 0;
		long downFlowSum = 0;
		long sum = 0;
		for(FlowWritable flowWritable : values){
			upFlowSum += flowWritable.getUpFlow();
			downFlowSum += flowWritable.getDownFlow();
			sum += flowWritable.getSumFlow();
		}
		this.result.set(upFlowSum, downFlowSum);
		this.result.setSumFlow(sum);
		context.write(key, this.result);
	}
}
