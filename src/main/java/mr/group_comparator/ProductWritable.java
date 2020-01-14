package mr.group_comparator;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * 商品可序列化流量对象
 * 		序列化与反序列化的顺序必须一致
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2020/1/14 11:34 PM,
 */
public class ProductWritable implements WritableComparable<ProductWritable>{
	/**
	 * 订单id
	 */
	private String orderId;
	/**
	 * 商品名称
	 */
	private String productName;
	/**
	 * 价格
	 */
	private float price;

	public String getOrderId(){
		return orderId;
	}

	public void setOrderId(String orderId){
		this.orderId = orderId;
	}

	public String getProductName(){
		return productName;
	}

	public void setProductName(String productName){
		this.productName = productName;
	}

	public float getPrice(){
		return price;
	}

	public void setPrice(float price){
		this.price = price;
	}

	@Override
	public void write(DataOutput out) throws IOException{
		out.writeUTF(orderId);
		out.writeUTF(productName);
		out.writeFloat(price);
	}

	@Override
	public void readFields(DataInput in) throws IOException{
		orderId = in.readUTF();
		productName = in.readUTF();
		price = in.readFloat();
	}

	@Override
	public String toString(){
		return orderId + '\t' + price;
	}

	@Override
	public int compareTo(ProductWritable o){
		int compare = this.orderId.compareTo(o.getOrderId());
		if(compare == 0){
			return Float.compare(o.getPrice(), this.price);
		}else{
			return compare;
		}
	}
}
