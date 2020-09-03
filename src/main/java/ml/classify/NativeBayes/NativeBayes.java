package ml.classify.NativeBayes;

import util.FileReaderUtil;

import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 朴素贝叶斯分类器
 *
 * 描述：
 * 		P(A|B)=P(B|A)*P(A)/P(B)
 *		假设特征独立：
 *			P(x1，x2|c)=P(x1|c)*P(x2|c)
 *
 * 优点：
 * 		(1) 算法逻辑简单，易于实现
 * 		(2) 分类过程中时空开销小
 * 不足：
 * 		理论上，朴素贝叶斯模型与其他分类方法相比具有最小的误差率。但是实际上并非总是如此，这是因为朴素贝叶斯模型假设属性之间相互独立，
 * 		这个假设实际应用中往往是不成立的，在属性个数较多或者属性之间相关性较大时，分类效果不好。
 * 改进：
 * 		(1) 半朴素贝叶斯
 *
 * 常用模型:
 * 		高斯模型：当特征为连续时使用，假设这些一个特征的所有属于某个类别的观测值符合高斯分布
 * 			P(xi|yk)=12πσ2yk√exp(−(xi−μyk)22σ2yk)
 *
 *
 * 		伯努利模型[词集模型]：不考虑词在文档中出现的次数，只考虑出不出现，因此在这个意义上相当于假设词是等权重的
 * 		多项式模型[词袋模型]：考虑在文档中的出现次数。
 * 			P(xi|yk)=Nykxi+αNyk+αn
 * 			其中Nykxi是类别yk下特征xi出现的总次数；Nyk是类别yk下所有特征出现的总次数。α的取值范围是[0,1]，比较常见的是取值为1
 * 			待预测样本中的特征xi在训练时可能没有出现，如果没有出现，则Nykxi值为0，如果直接拿来计算该样本属于某个分类的概率，结果都将是0。在分子中加入α，在分母中加入αn可以解决这个问题
 *
 *
 * 在文本分类中的应用：
 * 		在多项式模型中：
 * 			在多项式模型中， 设某文档d=(t1,t2,…,tk)，tk是该文档中出现过的单词，允许重复，则
 * 			先验概率P(c)= 类c下单词总数/整个训练样本的单词总数
 *			类条件概率P(tk|c)=(类c下单词tk在各个文档中出现过的次数之和+1)/(类c下单词总数+|V|)
 * 			V是训练样本的单词表（即抽取单词，单词出现多次，只算一个），|V|则表示训练样本包含多少种单词。 P(tk|c)可以看作是单词tk在证明d属于类c上提供了多大的证据，而P(c)则可以认为是类别c在整体上占多大比例(有多大可能性)。
 *
 * 		在伯努利模型中：
 * 			P(c)= 类c下文件总数/整个训练样本的文件总数
 * 			P(tk|c)=(类c下包含单词tk的文件数+1)/(类c下单词总数+2)
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2018/6/22 下午12:50,
 */
public class NativeBayes{
	/**
	 * 默认频率
	 */
	private double defaultFrequency = 0.1;

	/**
	 * 训练数据的比例
	 */
	private Double trainingPercent = 0.8;

	/**
	 * 所有文件
	 */
	private Map<String, List<String>> files_all = new HashMap();

	/**
	 * 训练数据文件
	 */
	private Map<String, List<String>> files_train = new HashMap();

	/**
	 * 测试数据文件
	 */
	private Map<String, List<String>> files_test = new HashMap();

	public NativeBayes(){}

	/**
	 * 每个分类的频率
	 */
	private Map<String, Integer> classFrequency = new HashMap<>();
	/**
	 * 每个类别的概率
	 */
	private Map<String, Double> classProbability = new HashMap<>();

	/**
	 * 特征总数
	 */
	private Set<String> wordDict = new HashSet<>();

	/**
	 * 不同类别中个特征的频率
	 */
	private Map<String, Map<String, Integer>> classFeatureFrequency = new HashMap<>();
	/**
	 * 不同类别中个特征的概率
	 */
	private Map<String, Map<String, Double>> classFeatureProbability = new HashMap<>();

	/**
	 * 类别默认概率
	 */
	private Map<String, Double> classDefaultProbability = new HashMap<>();

	/**
	 * 计算准确率
	 * @param realList 真实类别
	 * @param pridList 预测类别
	 */
	public void evaluate(List<String> realList, List<String> pridList){
		double correctNum = 0.0;
		for(int i=0; i<realList.size(); i++){
			if(realList.get(i) == pridList.get(i)){
				correctNum++;
			}
		}
		double accuracy = correctNum / realList.size();
		System.out.println("准确率为：" + accuracy);
	}

	/**
	 * 计算精确率与召回率
	 * @param realList 真实类别
	 * @param pridList 预测类别
	 * @param className 类别名称
	 */
	public void calPreRec(List<String> realList, List<String> pridList, String className){
		double correctNum = 0.0;
		double allNum = 0.0;//测试数据中，某个分类的文章总数
		double preNum = 0.0;//测试数据中，预测为该分类的文章总数

		for(int i=0; i<realList.size(); i++){
			if(realList.get(i) == className){
				allNum++;
				if(realList.get(i) == pridList.get(i)){
					correctNum++;
				}
			}
			if(pridList.get(i) == className){
				preNum++;
			}
		}
		System.out.println(className + "精确率（跟预测分类比较）：" + correctNum/preNum + " 召回率（跟真实分类比较）：" + correctNum/allNum);
	}

	/**
	 * 用模型进行预测
	 * 对计算公式求log，将特征概率相乘变为相加
	 */
	public void predict(){
		List<String> realList = new ArrayList<>();
		List<String> pridList = new ArrayList<>();

		for(Map.Entry<String, List<String>> entry : files_test.entrySet()){
			String realClassName = entry.getKey();
			List<String> files = entry.getValue();

			for(String file : files){
				realList.add(realClassName);

				List<String> classNameList = new ArrayList<>();
				List<Double> scoreList = new ArrayList<>();
				for(Map.Entry<String, Double> entry_1 : classProbability.entrySet()){
					String className = entry_1.getKey();
					//以e为底的log
					Double score = Math.log(entry_1.getValue());

					String[] words = FileReaderUtil.readFile(file).split(" ");

					for(String word : words){
						if(!wordDict.contains(word)){
							continue;
						}

						if(classFeatureProbability.get(className).containsKey(word)){
							score += Math.log(classFeatureProbability.get(className).get(word));
						}else{
							score += Math.log(classDefaultProbability.get(className));
						}
					}

					classNameList.add(className);
					scoreList.add(score);
				}

				Double maxProb = Collections.max(scoreList);
				int idx = scoreList.indexOf(maxProb);
				pridList.add(classNameList.get(idx));
			}
		}

		evaluate(realList, pridList);
		for(String className : files_test.keySet()){
			calPreRec(realList, pridList, className);
		}

	}

	/**
	 * 训练模型
	 * 		多项式模型
	 */
	public void trian(){

		double sum = 0.0;

		//计算总频率
		for(Map.Entry<String, Integer> entry : classFrequency.entrySet()){
			sum += entry.getValue();
		}

		//计算各个类别出现的概率
		for(Map.Entry<String, Integer> entry : classFrequency.entrySet()){
			classProbability.put(entry.getKey(), entry.getValue()/sum);
		}

		//计算各个类别中每个特征的出现概率
		for(Map.Entry<String, Map<String, Integer>> entry: classFeatureFrequency.entrySet()){
			sum = 0.0;
			String className = entry.getKey();
			//计算当前类别中特征总频率
			for(Map.Entry<String, Integer> entry_1 : entry.getValue().entrySet()){
				sum += entry_1.getValue();
			}


			double newSum = sum + wordDict.size() * defaultFrequency;

			Map<String, Double> featureProbability = new HashMap<>();
			classFeatureProbability.put(className, featureProbability);

			//计算各个特征的出现概率
			for(Map.Entry<String, Integer> entry_1 : entry.getValue().entrySet()){
				String word = entry_1.getKey();
				featureProbability.put(word, (entry_1.getValue() + defaultFrequency)/newSum);
			}
			//计算当前类别默认概率
			classDefaultProbability.put(className, defaultFrequency/newSum);
		}
	}

	/**
	 * 加载数据
	 */
	public void loadTrainData(){
		for(Map.Entry<String, List<String>> entry : files_train.entrySet()){
			String classname = entry.getKey();
			List<String> docs = entry.getValue();

			classFrequency.put(classname, docs.size());

			Map<String, Integer> feaFreq = new HashMap<>();
			classFeatureFrequency.put(classname, feaFreq);

			for(String doc : docs){
				String[] words = FileReaderUtil.readFile(doc).split(" ");
				for(String word : words){
					wordDict.add(word);

					if(feaFreq.containsKey(word)){
						int num = feaFreq.get(word) + 1;
						feaFreq.put(word, num);
					}else{
						feaFreq.put(word, 1);
					}
				}
			}
		}

		System.out.println(classFrequency.size() + " 分类，" + wordDict.size() + " 特征词");
	}

	/**
	 * 将数据分为训练数据和测试数据
	 * @param dataDir
	 */
	public void splitData(String dataDir){
		Pattern pattern = Pattern.compile("\\d+([a-z]+?)\\.");
		File f = new File(dataDir);
		File[] files = f.listFiles();
		for(File file : files){
			String fname = file.getName();
			Matcher matcher = pattern.matcher(fname);
			if(matcher.find()){
				String className = matcher.group(1);
				if(files_all.containsKey(className)){
					files_all.get(className).add(file.toString());
				}else{
					List<String> fileNames = new ArrayList<>();
					fileNames.add(file.toString());
					files_all.put(className, fileNames);
				}
			}else{
				System.out.println("err: " + file);
			}
		}

		System.out.println("统计数据：");
		for(Map.Entry<String, List<String>> entry : files_all.entrySet()){
			String className = entry.getKey();
			List<String> value = entry.getValue();

			List<String> train = new ArrayList<>();
			List<String> test = new ArrayList<>();

			for(int i=0; i<value.size(); i++){

				// 80%用来训练，20%用来测试
				if(i<=trainingPercent*value.size()){
					train.add(value.get(i));
				}else{
					test.add(value.get(i));
				}
			}

			files_train.put(className, train);
			files_test.put(className, test);
		}
	}


}
