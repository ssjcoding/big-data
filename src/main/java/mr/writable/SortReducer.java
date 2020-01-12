package mr.writable;

import mr.flow.FlowWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * ${DESCRIPTION}
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2020/1/12 1:58 PM,
 */
public class SortReducer extends Reducer<FlowWritable, Text, Text, FlowWritable>{
	private FlowWritable result = new FlowWritable();
	@Override
	protected void reduce(FlowWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException{
		for(Text text: values){
			context.write(text, key);
		}

	}
}
