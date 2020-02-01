package stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * java 8 stream 样例
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2020/1/17 12:04 AM,
 */
public class StreamDemo{

	public static void main(String[] args){
		reduce3th();
	}

	private static void learnStream(){
		List<Integer> lists = new ArrayList<>();
		lists.add(4);
		lists.add(3);
		lists.add(6);
		lists.add(1);
		lists.add(5);
		lists.add(2);

//		//看看List里面的数据是什么样子的
//		System.out.println("List里面的数据：");
//		for(int value : lists){
//			System.out.print(value + " ");
//		}
//
//		System.out.println();
//
//		//最小值
//		System.out.println("List中最小的值为：");
//		Stream<Integer> stream = lists.stream();
//		Optional<Integer> min = stream.min(Integer::compareTo);
//
//		if(min.isPresent()){
//			System.out.println(min.get());//1
//		}
//
//		//最大值
//		System.out.println("List中的最大值：");
//		lists.stream().max(Integer::compareTo).ifPresent(System.out::println);//6
//
//		//排序
//		System.out.println("将List流进行排序：");
//		Stream<Integer> sorted = lists.stream().sorted();
//		sorted.forEach(elem -> System.out.print(elem + "")); //1 2 3 4 5 6
//
//		System.out.println();
//		//过滤
//		System.out.println("过滤List流，只剩下那些大于3的元素：");
//		lists.stream()
//				.filter(elem -> elem > 3)
//				.forEach(elem -> System.out.print(elem + " ")); //4 5 6
//
//		System.out.println("===========end===========");
//
//		//经过了前面的这么多流操作，我们再来看看List里面的值有没有发生什么改变
//		System.out.println("原List里面的数据：");
//		for(Integer elem : lists) System.out.println(elem + " ");//4 3 6 1 5 2

//		Optional<Integer> sum = lists.stream().reduce((a, b) -> a + b);
//		if(sum.isPresent()) System.out.println("list的总和为：" + sum.get());		//21

//		Integer sum2 = lists.stream().reduce(0, (a, b) -> a + b);
//		System.out.println("list的总和为：" + sum2);		//21

//		Optional<Integer> sum3 = lists.stream().reduce((a, b) -> a * b);
//		if(sum3.isPresent()) System.out.println("list的积为：" + sum3.get());		//720

//		Integer sum4 = lists.stream().reduce(1, (a, b) -> a * b);
//		System.out.println("list的积为：" + sum4);		//720


		//并行流
		Optional<Integer> sum = lists.parallelStream().reduce((a, b) -> a + b);//这里把stream()换成parallelStream()
		if(sum.isPresent()) System.out.println("list的总和为:" + sum.get()); //21

		Integer sum2 = lists.stream().reduce(0, (a, b) -> a + b);
		System.out.println("list的总和为：" + sum2);

		Optional<Integer> product = lists.stream().reduce((a, b) -> a * b);
		if(product.isPresent()) System.out.println("list的积为：" + product.get()); //720

		Integer product2 = lists.parallelStream().reduce(1, (a, b) -> a * b);
		System.out.println("list的积为：" + product2); //720

	}


	/**
	 * accumulator被称为累加器， combiner被称为合成器
	 *
	 * 累加器部分（水平向右）
	 * accumulator
	 * -----------------------------›
	 * thread-1:   1 * 1 * 2   =   2    |    合并器方向（竖直向下）
	 * thread-2:   1 * 2 * 2   =   4    |         combiner
	 * thread-3:   1 * 3 * 2   =   6    |   因此最终的答案是2  *  4  *  6  =   48（没毛病）
	 * ˇ
	 * 注：水平方向最前面的1就是identity的值
	 */
	public static void reduce3th(){
		List<Integer> lists = new ArrayList<>();
		lists.add(1);
		lists.add(2);
		lists.add(3);
		lists.add(4);
		lists.add(5);
		lists.add(6);
		lists.add(7);

		//需求1：现在的需求是分别让List里面的每个元素都放大两倍后，再求积
//		Integer product2 = lists.parallelStream().reduce(1, (a, b) -> a * (b*2), (a, b) -> a * b);
//		System.out.println("list的积为：" + product2);	//48
//
//		//采用映射实现需求1
//		Stream<Integer> productNewMapStream = lists.parallelStream().map((a) -> a * 2);
//		Integer productMap = productNewMapStream.reduce(1, (a, b) -> a * 2);
//		System.out.println("productMapL" + productMap);

		//需求2：list中的每个元素放大两倍后，再求积
		//    	累加器部分（水平向右）
		//		accumulator
		//		-----------------------------›
		//		thread-1:   1 * 1 * 2   =   1  *  2    |    合并器方向（竖直向下）
		//		thread-2:   1 * 2 * 2   =   4  *  2    |         combiner
		//		thread-3:   1 * 3 * 2   =   6  *  2    |   因此最终的答案是2  *  （ 4  *  2 ） *  （6  *  2）  =   192（没毛病）
		//		ˇ
		//		注：水平方向最前面的1就是identity的值
//		Integer product2 = lists.parallelStream().reduce(1, (a, b) -> a * (b*2), (a, b) -> a * (b*2));
//		System.out.println("list的积为：" + product2);		//192

		Object[] result = lists.parallelStream().map(e -> {
			int a = e * 2;
			return a;
		}).toArray();

		for(Object r : result){
			System.out.println(r.getClass());
		}
	}

}
