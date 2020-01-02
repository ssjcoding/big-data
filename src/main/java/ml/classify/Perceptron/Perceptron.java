package ml.classify.Perceptron;

import java.util.ArrayList;

/**
 * 感知机分类器
 * 		模型：f(x) = sign(w*x+b)
 * 			其中sign为符号函数，w表示权值，b表示偏置
 *
 * 		策略：
 * 			误分类点到超平面距离之和
 * 			L(w,b)=−∑xiϵMyi(w∗x0+b)
 *
 * 		算法：随机梯度下降法
 *
 * 		是否更新判断条件:
 *
 *
 * 		参数更新推导结果:
 * 			(1)原始形式
 * 				是否更新条件：
 * 					yi(wxi+b)<=0
 * 				更新方式：
 * 					w:=w+ηyixi
 * 					b:=b+ηyi
 *
 * 			(2)对偶形式
 * 				是否更新条件：
 * 					yi(∑j=1Nαjyjxj⋅xi+b)≤0
 * 				更新方式：
 * 					w:=w+η
 * 					b:=b+ηyi
 *
 * 		特点：
 * 			(1)如果没有误分类点，损失函数值是0
 * 			(2)学习算法采用不同的初始值或者选取不同的训练点（以及顺序），得到的分隔超平面可能不同
 *
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2018/6/11 上午11:30,
 */
public class Perceptron{
	//权值向量组
	private double[] w;
	//阈值
	private double b = 0;
	//步长
	private double step_length = 1;
	//总迭代次数
	private int total_n_iter = 1000;
	ArrayList<Point> arrayList;

	/**
	 * 初始化分类器，传入需要分组的数据与参数更新步长
	 * @param step_length 参数更新步长
	 * @param total_n_iter 总迭代次数
	 */
	public Perceptron(double step_length, int total_n_iter){
		this.total_n_iter = total_n_iter;
		this.step_length = step_length;
	}

	/**
	 * 初始化分类器，传入需要分组的数据，参数更新步长默认为1，迭代次数默认为1000
	 *
	 */
	public Perceptron(){
		this.step_length = 1;
		this.total_n_iter = 1000;
	}

	/**
	 * 分类器训练函数
	 */
	public void train(ArrayList<Point> train){
		w = new double[train.get(0).x.length];
		int n_iter = 0;
		while(n_iter < total_n_iter){
			int index = (int)(Math.random()*train.size());
			Point point = train.get(index);

			if(point.y*(dotProduct(w, point.x) + b) <= 0){ //原始形式
				for(int j=0; j<w.length; j++){
					w[j] += step_length * point.y * point.x[j];
				}
				b += step_length * point.y;
			}
			n_iter++;
		}
	}

	/**
	 * 进行分类计算
	 *
	 */
	public Point classify(Point point){
		if(point.x.length != w.length){
			throw new IllegalArgumentException("特征个数不一致");
		}

		if(dotProduct(point.x, w) + b >= 0){
			point.y=1;
		}else{
			point.y=-1;
		}

		return point;
	}

	/**
	 * 向量点乘
	 * @param x1 乘数
	 * @param x2 乘数
	 * @return	点乘的积（点积）
	 */
	private double dotProduct(double[] x1, double[] x2){
		int len = x1.length;
		double sum = 0;
		for(int i=0; i<len; i++){
			sum += x1[i] * x2[i];
		}
		return sum;
	}


	public static void main(String[] args){
		Point p1 = new Point(new double[]{0,0,0,0}, -1);
		Point p2 = new Point(new double[]{1,0,0,0}, 1);
		Point p3 = new Point(new double[]{2,1,0,0}, 1);
		Point p4 = new Point(new double[]{2,1,0,1}, -1);
		ArrayList<Point> list = new ArrayList<>();
		list.add(p1);
		list.add(p2);
		list.add(p3);
		list.add(p4);
		double step_length = 0.5;
		int total_n_iter = 1000;
		Perceptron classifier = new Perceptron(step_length, total_n_iter);
		classifier.train(list);
		Point test = new Point(new double[]{-2,2,0,0});
		classifier.classify(test);
		System.out.println(test);

	}

}


