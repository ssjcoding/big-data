package sort;

/**
 *  快速排序
 *
 * 算法描述：
 *      从数列中挑出一个元素，成为"基准"（pivot）
 *      重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆放在基准的后面（相同的数可以到任一边）。在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作。
 *      递归地（recursive）把小于基准值元素的子数列和大于基准的子数列排序。
 *      递归的最底部情形，是数列的大小是零或一，也就是永远都已经被排序好了，虽然一直递归下去，但是这个算法总会退出，因为在每次的迭代（iteration）中，它至少会把一个元素摆到它最后的位置去。
 *
 * 算法分析：
 *      最差时间复杂度：O(n^2)
 *      最好时间复杂度：O(nlogn)
 *      平均时间复杂度：O(nlogn)
 *      空间复杂度：　　O(logn)
 *      稳定性：　　　　不稳定
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2018/6/14 下午5:10,
 */
public class QuickSort{

	/**
	 * 排序函数
	 * 		优化方案：同时查询
	 *
	 * @param data  输入数组
	 * @param start  开始位置
	 * @param end  结束位置
	 * @param order 排序方式：
	 *              true为升序
	 *              false为降序
	 * @return
	 */
	public int[] sort(int[] data, int start, int end, boolean order){

		if(start < end){
			int low = start;
			int high = end;
			//选择基准
			int pivot = data[low];

			while(low < high){

				if(order){
					while(low<high && data[high]>=pivot){
						high--;
					}

					if(low < high){
						int temp = data[high];
						data[high] = data[low];
						data[low] = temp;
						low++;
					}

					while(low<high && data[low]<=pivot){
						low++;
					}

					if(low < high){
						int temp = data[high];
						data[high] = data[low];
						data[low] = temp;
						high--;
					}
				}else{
					while(low<high && data[high]<=pivot){
						high--;
					}

					if(low < high){
						int temp = data[high];
						data[high] = data[low];
						data[low] = temp;
						low++;
					}

					while(low<high && data[low]>=pivot){
						low++;
					}

					if(low < high){
						int temp = data[high];
						data[high] = data[low];
						data[low] = temp;
						high--;
					}
				}
			}

			if(low > start){
				sort(data, start, low-1, order);
			}
			if(high < end){
				sort(data, low+1, end, order);
			}

		}

		return data;
	}

}
