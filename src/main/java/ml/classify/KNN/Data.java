package ml.classify.KNN;

/**
 * 封装一条数据
 * 		数据采用《机器学习实战》中约会网站数据
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2018/6/11 下午4:52,
 */
public class Data implements Comparable<Data>{

	/**
	 * 每年获得的飞行常客里程数
	 */
	private  double mile;

	/**
	 * 玩视频游戏所耗时间占比
	 */
	private double time;

	/**
	 * 每周消费的冰淇淋公升数
	 */
	private double icecream;

	/**
	 * 1 代表不喜欢的人
	 * 2 代表魅力一般的人
	 * 3 代表极具魅力的人
	 */
	private int type;

	/**
	 * 与当前数据点的距离
	 */
	private double distance;

	public double getMile(){
		return mile;
	}

	public void setMile(double mile){
		this.mile = mile;
	}

	public double getTime(){
		return time;
	}

	public void setTime(double time){
		this.time = time;
	}

	public double getIcecream(){
		return icecream;
	}

	public void setIcecream(double icecream){
		this.icecream = icecream;
	}

	public int getType(){
		return type;
	}

	public void setType(int type){
		this.type = type;
	}

	public double getDistance(){
		return distance;
	}

	public void setDistance(double distance){
		this.distance = distance;
	}

	/**
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(Object)
	 * @return
	 */
	@Override
	public int compareTo(Data o){
		if(this.distance < o.distance){
			return -1;
		}else if(this.distance > o.distance){
			return 1;
		}
		return 0;
	}
}