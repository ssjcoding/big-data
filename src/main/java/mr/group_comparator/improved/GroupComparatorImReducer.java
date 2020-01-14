package mr.group_comparator.improved;

import mr.group_comparator.ProductWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * wordcount reducer 实现
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2020/1/9 12:06 AM,
 */
public class GroupComparatorImReducer extends Reducer<ProductWritable, NullWritable, ProductWritable, NullWritable>{

	@Override
	protected void reduce(ProductWritable productWritable, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException{
		context.write(productWritable, NullWritable.get());
	}
}
