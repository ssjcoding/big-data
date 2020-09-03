package ml.classify.Perceptron;



import java.util.Arrays;

/**
 * 定义一个Point,里面包含两个部分，用来分类。
 * x表示输入R维空间向量，y表示分类值，只有-1和+1两类
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2018/6/14 下午3:36,
 */
public class Point{
	double[] x = new double[2];
	double y = 0;

	Point(double[] x, double y){
		this.x = x;
		this.y = y;
	}

	Point(double[] x){
		this.x = x;
	}


	public Point(){}

	@Override
	public String toString(){
		return "Point{" +
				"x=" + Arrays.toString(x) +
				", y=" + y +
				'}';
	}
}
