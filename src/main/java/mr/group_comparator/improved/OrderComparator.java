package mr.group_comparator.improved;

import mr.group_comparator.ProductWritable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * ${DESCRIPTION}
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2020/1/15 12:58 AM,
 */
public class OrderComparator extends WritableComparator{

	/**
	 * 由于框架所有组件之间传输数据都是序列化再反射，所以需要实现此构造函数
	 */
	public OrderComparator(){
		super(ProductWritable.class, true);
	}

	@Override
	public int compare(WritableComparable a, WritableComparable b){
		ProductWritable pa = (ProductWritable)a;
		ProductWritable pb = (ProductWritable)b;
		return pa.getOrderId().compareTo(pb.getOrderId());
	}
}
