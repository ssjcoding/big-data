package mr.group_comparator;

import org.apache.hadoop.io.NullWritable;
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
public class GroupComparatorReducer extends Reducer<Text, ProductWritable, NullWritable, ProductWritable>{

	private ProductWritable maxPriceProduct = null;
	private NullWritable nullWritable = NullWritable.get();
	@Override
	protected void reduce(Text key, Iterable<ProductWritable> values, Context context) throws IOException, InterruptedException{
		for(ProductWritable value : values){
			if(maxPriceProduct == null){
				maxPriceProduct = value;
			}else {
				if(maxPriceProduct.getPrice() < value.getPrice()){
					maxPriceProduct = value;
				}
			}
		}

		context.write(nullWritable, maxPriceProduct);
	}
}
