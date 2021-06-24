package sort;

/**
 * 冒泡排序
 *
 * 算法描述：
 *		将序列中所有元素两两比较，将最大的放在最后面
 *		将剩余序列中所有元素两两比较，将最大的放在最后面
 *
 * 算法分析：
 * 		最差时间复杂度：O(n^2)
 *      最好时间复杂度：O(n)
 *      平均时间复杂度：O(n^2)
 *      空间复杂度：　　O(1)
 *      稳定性：　　　　稳定
 *
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2018/6/14 下午4:19,
 */
public class BubbleSort{

	/**
	 * 冒泡排序
	 *
	 * @param data  输入数组
	 * @param order 排序方式：
	 *              true为升序
	 *              false为降序
	 * @return
	 */
	public int[] sort(int[] data, boolean order){

		int length = data.length;

		if(length == 0 || data == null){
			throw new NullPointerException("数组为空");
		}

		if(length == 1){
			return data;
		}

		for(int i = 0; i<length; i++){
			for(int j=0; j<length-i-1; j++){ //注意第二次循环的条件
				if(order){
					if(data[j] > data[j+1]){
						int temp = data[j];
						data[j] = data[j+1];
						data[j+1] = temp;
					}
				}else{
					if(data[j] > data[j+1]){
						int temp = data[j];
						data[j] = data[j+1];
						data[j+1] = temp;
					}
				}
			}
		}

		return data;
	}

}
