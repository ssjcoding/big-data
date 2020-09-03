package ml.classify.KNN;

import java.io.*;
import java.util.*;

/**
 * K近邻分类器
 * 		算法：
 * 			1）计算已知类别数据集中的点与当前点之间的距离；
 * 			2）按照距离递增次序排序；
 * 			3）选取与当前距离最小的k个点；
 * 			4）确定前k个点所在类别的出现频率；
 * 			5）返回前k个点出现频率最高的类别作为当前点的预测分类
 * 		不足：
 * 			1）计算量大
 * 			2）当样本不平衡，即：一个类的样本容量很大，而其他类样数量很小时，很有可能导致当输入一个未知样本时，该样本的K个邻居中大数量类的样本站多数。
 * 			但是这类样本并不接近目标样本，而数量小的这类样本很靠近目标样本。这个时候，我们有理由认为该位置样本属于数量小的样本所属的一类，但是，KNN却不关心这个问题，它只关心哪类样本的数量最多，而不去把距离远近考虑在内，
 *
 * 		改进：
 * 			1）kd树存储结构
 * 			2）我们可以采用权值的方法来改进。和该样本距离小的邻居权值大，和该样本距离大的邻居权值则相对较小，由此，将距离远近的因素也考虑在内，避免因一个样本过大导致误判的情况。
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2018/6/11 下午4:00,
 */
public class KNN{
	private List<Data> dataset = null;

	public KNN(String fileName) throws IOException{
		dataset = initDataSet(fileName);
	}

	private List<Data> initDataSet(String fileName) throws IOException {
		List<Data> list = new ArrayList<Data>();

		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(new FileInputStream(new File(fileName))));
		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			Data data = new Data();
			String[] s = line.split("\t");
			data.setMile(Double.parseDouble(s[0]));
			data.setTime(Double.parseDouble(s[1]));
			data.setIcecream(Double.parseDouble(s[2]));
			if (s[3].equals("largeDoses")) {
				data.setType(3);
			} else if (s[3].equals("smallDoses")) {
				data.setType(2);
			} else {
				data.setType(1);
			}
			list.add(data);
		}
		return list;
	}

	/**
	 * 算法核心
	 *
	 * @param data
	 * @param dataset
	 * @param k
	 */
	public int knn(Data data, List<Data> dataset, int k) {

		for (Data data2 : dataset) {
			double distance = calDistance(data, data2);
			data2.setDistance(distance);
		}
		// 对距离进行排序，倒序
		Collections.sort(dataset);
		// 从前k个样本中，找到出现频率最高的类别
		int type1 = 0, type2 = 0, type3 = 0;
		for (int i = 0; i < k; i++) {
			Data d = dataset.get(i);
			if (d.getType() == 1) {
				++type1;
				continue;
			} else if (d.getType() == 2) {
				++type2;
				continue;
			} else {
				++type3;
			}
		}
		if (type1 > type2) {
			if (type1 > type3) {
				return 1;
			}else {
				return 3;
			}
		}else {
			if (type2 > type3) {
				return 2;
			}else {
				return 3;
			}
		}
	}

	/**
	 * 计算两个样本点之间的距离
	 * 		采用欧氏距离计算
	 *
	 * @param data
	 * @param data2
	 * @return
	 */
	private double calDistance(Data data, Data data2) {
		double sum = Math.pow((data.getMile() - data2.getMile()), 2)
				+ Math.pow((data.getIcecream() - data2.getIcecream()), 2)
				+ Math.pow((data.getTime() - data2.getTime()), 2);
		return Math.sqrt(sum);
	}

	/**
	 * 将数据集归一化处理<br>
	 * <br>
	 * newValue = (oldValue - min) / (max - min)
	 *
	 * @param oldDataSet
	 * @return
	 */
	private List<Data> autoNorm(List<Data> oldDataSet) {
		List<Data> newDataSet = new ArrayList<Data>();
		// find max and min
		Map<String, Double> map = findMaxAndMin(oldDataSet);
		for (Data data : oldDataSet) {
			data.setMile(calNewValue(data.getMile(),
					map.get("maxDistance"), map.get("minDistance")));
			data.setTime(calNewValue(data.getTime(), map.get("maxTime"),
					map.get("minTime")));
			data.setIcecream(calNewValue(data.getIcecream(),
					map.get("maxIcecream"), map.get("minIcecream")));
			newDataSet.add(data);
		}
		return newDataSet;
	}

	/**
	 * @param oldValue
	 * @param maxValue
	 * @param minValue
	 * @return newValue = (oldValue - min) / (max - min)
	 */
	private double calNewValue(double oldValue, double maxValue, double minValue) {
		return (double)(oldValue - minValue) / (maxValue - minValue);
	}


	/**
	 * find the max and the min
	 *
	 * @return
	 */
	private Map<String, Double> findMaxAndMin(List<Data> oldDataSet) {
		Map<String, Double> map = new HashMap<String, Double>();

		double maxDistance = Integer.MIN_VALUE;
		double minDistance = Integer.MAX_VALUE;
		double maxTime = Double.MIN_VALUE;
		double minTime = Double.MAX_VALUE;
		double maxIcecream = Double.MIN_VALUE;
		double minIcecream = Double.MAX_VALUE;

		for (Data data : oldDataSet) {
			if (data.getMile() > maxDistance) {
				maxDistance = data.getMile();
			}
			if (data.getMile() < minDistance) {
				minDistance = data.getMile();
			}
			if (data.getTime() > maxTime) {
				maxTime = data.getTime();
			}
			if (data.getTime() < minTime) {
				minTime = data.getTime();
			}
			if (data.getIcecream() > maxIcecream) {
				maxIcecream = data.getIcecream();
			}
			if (data.getIcecream() < minIcecream) {
				minIcecream = data.getIcecream();
			}
		}
		map.put("maxDistance",  maxDistance);
		map.put("minDistance",  minDistance);
		map.put("maxTime", maxTime);
		map.put("minTime", minTime);
		map.put("maxIcecream", maxIcecream);
		map.put("minIcecream", minIcecream);

		return map;
	}

	/**
	 * 取已有数据的10%作为测试数据，这里我们选取100个样本作为测试样本，其余作为训练样本
	 * @throws IOException
	 */
	public void test() throws IOException {
		List<Data> testDataSet = initDataSet("src/main/resources/data/test.txt");
		//归一化数据
		List<Data> newTestDataSet = autoNorm(testDataSet);
		List<Data> newDataSet = autoNorm(dataset);
		int errorCount = 0;
		for (Data data : newTestDataSet) {
			int type = knn(data, newDataSet, 6);
			if (type != data.getType()) {
				++errorCount;
			}
		}

		System.out.println("错误率：" + (double)errorCount / testDataSet.size() * 100 + "%");
	}

	public static void main(String[] args) throws IOException {
		System.out.println(Thread.currentThread().getContextClassLoader().getResource("").getPath());
		File file = new File("src/main/resources/data/datingTestSet2.txt");
		Long filelength = file.length();
		System.out.println(filelength);
		KNN knn = new KNN("src/main/resources/data/datingTestSet2.txt");
		knn.test();
	}

}


