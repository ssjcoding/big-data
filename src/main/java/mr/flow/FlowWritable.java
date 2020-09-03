package mr.flow;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * 可序列化流量对象
 * 		序列化与反序列化的顺序必须一致
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2020/1/9 1:52 AM,
 */
public class FlowWritable implements WritableComparable<FlowWritable>{

	//必须具有一个空参构造器，因为mr会调用此对象的反射机制
	public FlowWritable(){}

	public void set(long upFlow, long downFlow){
		this.upFlow = upFlow;
		this.downFlow = downFlow;
		this.sumFlow = upFlow + downFlow;
	}

	/**
	 * 上行流量
	 */
	private long upFlow;

	/**
	 * 下行流量
	 */
	private long downFlow;

	/**
	 * 总流量
	 */
	private long sumFlow;

	/**
	 * 序列化方法		通过out交给框架
	 * @param out 框架给我们提供的数据出口
	 * @throws IOException
	 */
	@Override
	public void write(DataOutput out) throws IOException{
		out.writeLong(upFlow);
		out.writeLong(downFlow);
		out.writeLong(sumFlow);
	}

	/**
	 * 反序列化方法		通过in将数据交还给我们
	 * @param in 框架给我们提供的数据来源
	 * @throws IOException
	 */
	@Override
	public void readFields(DataInput in) throws IOException{
		this.upFlow = in.readLong();
		this.downFlow = in.readLong();
		this.sumFlow = in.readLong();
	}

	@Override
	public String toString(){
		return upFlow + "\t" + downFlow + "\t" + sumFlow;
	}

	public long getUpFlow(){
		return upFlow;
	}

	public void setUpFlow(long upFlow){
		this.upFlow = upFlow;
	}

	public long getDownFlow(){
		return downFlow;
	}

	public void setDownFlow(long downFlow){
		this.downFlow = downFlow;
	}

	public long getSumFlow(){
		return sumFlow;
	}

	public void setSumFlow(long sumFlow){
		this.sumFlow = sumFlow;
	}

	@Override
	public int compareTo(FlowWritable o){
		return Long.compare(o.getSumFlow(), this.sumFlow);
	}
}
