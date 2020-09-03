package mr.group_comparator.improved;

import mr.group_comparator.ProductWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * group comparator mapper 实现
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2020/1/9 12:06 AM,
 */
public class GroupComparatorImMapper extends Mapper<LongWritable, Text, ProductWritable, NullWritable>{

	/**
	 * 防止大量new对象，所以写在上面
	 * 大量new对象会导致gc，使得程序变慢
	 */
	private ProductWritable productWritable = new ProductWritable();
	private int TERM_SIZE = 3;

	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException{
		// 拿到这一行数据
		String line = value.toString();
		// 按照\t切分数据
		String[] terms = line.split("\t");

		//遍历数据，把单词变成（orderId, productWritable）的形式交给框架
		if(terms.length == TERM_SIZE){
			productWritable.setOrderId(terms[0]);
			productWritable.setProductName(terms[1]);
			productWritable.setPrice(Float.valueOf(terms[2]));
			context.write(this.productWritable, NullWritable.get());
		}

	}
}
