package xgboost;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * ${DESCRIPTION}
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/12/30 5:40 PM,
 */
public class XgBooster{
	private Model model;

	public XgBooster(String modelPath){
		loadModel(modelPath);
	}

	private void loadModel(String modelPath) {
		model = new Model(modelPath);
	}

	public double score(Map<Integer, Double> vec){
		return model.score(vec);
	}

	public double score(String vecString) {
		String[] features = vecString.split(" ");
		Map<Integer, Double> vec = Maps.newHashMap();
		for (String feature : features) {
			String[] args = feature.split(":");
			vec.put(Integer.parseInt(args[0]), Double.parseDouble(args[1]));
		}
		return score(vec);
	}

	public static void main(String[] args) {
		XgBooster xgb = new XgBooster("/Users/tony/Documents/private/data_mining/sklearn/xgboost.model");
		//这里的key一定要对应python中的列名，一开始我在网上找的例子是随便起的名字，不管输入什么数据返回结果都是0
		System.out.println(xgb.score("1:17.19 2:22.07 3:111.6 4:928.3 5:0.09726 6:0.08995 7:0.09061 8:0.06527 9:0.1867 10:0.0558 11:0.4203 12:0.7383 13:2.819 14:45.42 15:0.004493 16:0.01206 17:0.02048 18:0.009875 19:0.01144 20:0.001575 21:21.58 22:29.33 23:140.5 24:1436.0 25:0.1558 26:0.2567 27:0.3889 28:0.1984 29:0.3216 30:0.0757"));
	}
}
