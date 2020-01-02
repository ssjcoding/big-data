package ml.classify.KDTree;

/**
 * 二叉树节点对象
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2018/6/14 下午2:04,
 */
public class Node implements Comparable<Node>{

	/**
	 * 树上节点的数据，是一个多维向量
	 */
	public double[] data;

	/**
	 * 与当前查询点的距离，初始化的时候是没有的
	 */
	public double distance;

	/**
	 * 左右和父亲节点
	 */
	public Node left, right, parent;

	/**
	 * 维度 建立树的时候判断的维度（即第n个特征）
	 */
	public int dim = -1;

	public Node(double[] data){
		this.data = data;
	}

	/**
	 * 返回指定索引上的数据（即返回第index维的特征值）
	 * @param index
	 * @return
	 */
	public double getData(int index){
		if(data==null || data.length<=index){
			return Integer.MIN_VALUE;
		}
		return data[index];
	}

	@Override
	public int compareTo(Node o){
		if(this.distance > o.distance){
			return 1;
		}else if(this.distance == o.distance){
			return 0;
		}else{
			return -1;
		}
	}

	/**
	 * 计算距离
	 * 		采用欧式距离计算方式
	 * @param node
	 * @return
	 */
	public double computeDiatance(Node node){
		if(this.data==null || node.data==null || this.data.length!=node.data.length){
			return Double.MAX_VALUE; //出问题了 距离最远
		}
		double d= 0;
		for(int i=0; i<this.data.length; i++){
			d+=Math.pow(this.data[i] - node.data[i], 2);
		}
		return Math.sqrt(d);
	}

	@Override
	public String toString(){
		if(data==null || data.length==0){
			return null;
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<data.length; i++){
			sb.append(data[i] + "");
		}
		sb.append(" d:" + this.distance);
		return sb.toString();
	}
}
