package mr.partition;


import mr.flow.FlowWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * 自定义分区：
 * 	分区数量应该小于等于reduce task
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2020/1/12 2:52 AM,
 */
public class MyPartition extends Partitioner<Text, FlowWritable>{
	@Override
	public int getPartition(Text text, FlowWritable flowWritable, int numPartitions){
		String phone = text.toString();
		switch(phone.substring(0, 3)){
			case "136":
				return 0;
			case "137":
				return 1;
			case "138":
				return 2;
			case "139":
				return 3;
			default:
				return 4;
		}
	}
}
