package sort;

import org.junit.Test;

/**
 * 堆排序测试类
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2018/6/22 下午12:42,
 */
public class HeapSortTest{
	@Test
	public void sort() throws Exception{
		int[] data={12,3,24,11,34,33,42,9,4,5,1,46,22,32,35,0,13,14,21,24,8,7,49,38,65,97,76,13,27,49,55,04,22,12,45,87,99,3,5,34,54,234,1,49,38,65,97,76,13,27,49,55,04,22,12,45,87,99,3,5,34,54,234,1,49,38,65,97,76,13,27,49,55,04,22,12,45,87,99,3,5,34,54,234,1,49,38,65,97,76,13,27,49,55,04,22,12,45,87,99,3,5,34,54,234,1,49,38,65,97,76,13,27,49,55,04,22,12,45,87,99,3,5,34,54,234,1,49,38,65,97,76,13,27,49,55,04,22,12,45,87,99,3,5,34,54,234,1,49,38,65,97,76,13,27,49,55,04,22,12,45,87,99,3,5,34,54,234,1,49,38,65,97,76,13,27,49,55,04,22,12,45,87,99,3,5,34,54,234,1,49,38,65,97,76,13,27,49,55,04,22,12,45,87,99,3,5,34,54,234,1,49,38,65,97,76,13,27,49,55,04,22,12,45,87,99,3,5,34,54,234,1,49,38,65,97,76,13,27,49,55,04,22,12,45,87,99,3,5,34,54,234,1,49,38,65,97,76,13,27,49,55,04,22,12,45,87,99,3,5,34,54,234,1,49,38,65,97,76,13,27,49,55,04,22,12,45,87,99,3,5,34,54,234,1,49,38,65,97,76,13,27,49,55,04,22,12,45,87,99,3,5,34,54,234,1,49,38,65,97,76,13,27,49,55,04,22,12,45,87,99,3,5,34,54,234,1,49,38,65,97,76,13,27,49,55,04,22,12,45,87,99,3,5,34,54,234,1,49,38,65,97,76,13,27,49,55,04,22,12,45,87,99,3,5,34,54,234,1,49,38,65,97,76,13,27,49,55,04,22,12,45,87,99,3,5,34,54,234,1,49,38,65,97,76,13,27,49,55,04,22,12,45,87,99,3,5,34,54,234,1,49,38,65,97,76,13,27,49,55,04,22,12,45,87,99,3,5,34,54,234,1,49,38,65,97,76,13,27,49,55,04,22,12,45,87,99,3,5,34,54,234,1,49,38,65,97,76,13,27,49,55,04,22,12,45,87,99,3,5,34,54,234,1,49,38,65,97,76,13,27,49,55,04,22,12,45,87,99,3,5,34,54,234,1,49,38,65,97,76,13,27,49,55,04,22,12,45,87,99,3,5,34,54,234,1,49,38,65,97,76,13,27,49,55,04,22,12,45,87,99,3,5,34,54,234,1,49,38,65,97,76,13,27,49,55,04,22,12,45,87,99,3,5,34,54,234,1,49,38,65,97,76,13,27,49,55,04,22,12,45,87,99,3,5,34,54,234,1,49,38,65,97,76,13,27,49,55,04,22,12,45,87,99,3,5,34,54,234,1,49,38,65,97,76,13,27,49,55,04,22,12,45,87,99,3,5,34,54,234,1,49,38,65,97,76,13,27,49,55,04,22,12,45,87,99,3,5,34,54,234,1,49,38,65,97,76,13,27,49,55,04,22,12,45,87,99,3,5,34,54,234,1,49,38,65,97,76,13,27,49,55,04,22,12,45,87,99,3,5,34,54,234,1,49,38,65,97,76,13,27,49,55,04,22,12,45,87,99,3,5,34,54,234,1,49,38,65,97,76,13,27,49,55,04,22,12,45,87,99,3,5,34,54,234,1,49,38,65,97,76,13,27,49,55,04,22,12,45,87,99,3,5,34,54,234,1,49,38,65,97,76,13,27,49,55,04,22,12,45,87,99,3,5,34,54,234,1,49,38,65,97,76,13,27,49,55,04,22,12,45,87,99,3,5,34,54,234,1,49,38,65,97,76,13,27,49,55,04,22,12,45,87,99,3,5,34,54,234,1,49,38,65,97,76,13,27,49,55,04,22,12,45,87,99,3,5,34,54,234,1,49,38,65,97,76,13,27,49,55,04,22,12,45,87,99,3,5,34,54,234,1,49,38,65,97,76,13,27,49,55,04,22,12,45,87,99,3,5,34,54,234,1,49,38,65,97,76,13,27,49,55,04,22,12,45,87,99,3,5,34,54,234,1,49,38,65,97,76,13,27,49,55,04,22,12,45,87,99,3,5,34,54,234,1,49,38,65,97,76,13,27,49,55,04,22,12,45,87,99,3,5,34,54,234,1,49,38,65,97,76,13,27,49,55,04,22,12,45,87,99,3,5,34,54,234,1,49,38,65,97,76,13,27,49,55,04,22,12,45,87,99,3,5,34,54,234,1,49,38,65,97,76,13,27,49,55,04,22,12,45,87,99,3,5,34,54,234,1,49,38,65,97,76,13,27,49,55,04,22,12,45,87,99,3,5,34,54,234,1};
		for(int i : data){
			System.out.print(i);
			System.out.print(", ");
		}
		HeapSort heapSort = new HeapSort();
		boolean order = false;
		long startTime = System.currentTimeMillis();
		int[] result = heapSort.sort(data, order);
		long endTime = System.currentTimeMillis();
		System.out.println("");
		for(int i : result){
			System.out.print(i);
			System.out.print(", ");
		}
		System.out.println();
		System.out.println("消耗时长：" + (endTime - startTime));
	}
}
