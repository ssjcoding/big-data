package sort;

/**
 * 堆排序
 *
 * 堆结构介绍：
 * 		堆是具有以下性质的完全二叉树：
 * 			每个结点的值都大于或等于其左右孩子结点的值，称为大顶堆；
 * 			每个结点的值都小于或等于其左右孩子结点的值，称为小顶堆；
 * 		大顶堆：arr[i]>=arr[2i+1]&&arr[i]>=arr[2i+2]
 * 		小顶堆：arr[i]<=arr[2i+1]&&arr[i]<=arr[2i+2]
 *
 * 算法描述：
 *		将待排序序列构造成一个大顶堆，此时，整个序列的最大值就是堆顶的根节点。将其与末尾元素进行交换，此时末尾就是最大值。
 *		然后将剩余n-1个元素重新构造成一个堆，这样会得到n个元素的次小值。
 *		如此反复执行，便能得到一个有序序列了。
 *
 *  大顶堆构建步骤：
 *  	(1) 从最后一个非叶子结点开始（叶节点自然不用调整，第一个非叶子结点arr.length/2-1），从左至右，从下至上进行调整。
 *
 *	排序步骤：
 *		(1) 将无序序列构成一个堆，根据升序降序需求选择大顶堆或小顶堆（一般升序采用大顶堆，降序采用小顶堆）
 *		(2) 将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端
 *		(3) 重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序
 *
 * 算法分析：
 * 		最差时间复杂度：O(nlogn)
 *      最好时间复杂度：O(nlogn)
 *      平均时间复杂度：O(nlogn)
 *      空间复杂度：　　O(1)
 *      稳定性：　　　　不稳定
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2018/6/21 下午6:55,
 */
public class HeapSort{

	/**
	 * 排序函数
	 *
	 * @param data  输入数组
	 * @param order 排序方式：
	 *              true为升序,构建最大堆
	 *              false为降序,构建最小堆
	 * @return
	 */
	public int[] sort(int[] data, boolean order){
		//构建大顶堆
		for(int i=data.length/2-1; i>=0; i--){
			adjustHeap(data, i, data.length, order);
		}
		//调整堆结构+交换堆顶元素与末尾元素
		for(int j=data.length-1; j>0; j--){
			swap(data, 0, j);//将堆顶元素与末尾元素交换
			adjustHeap(data, 0, j, order);
		}
		return data;
	}

	/**
	 * 调整大顶堆（仅是调整过程，建立在大顶堆已构建的基础上）
	 * @param data
	 * @param i
	 * @param length
	 */
	private void adjustHeap(int[] data, int i, int length, boolean order){
		int temp = data[i];//先取出当前元素i
		for(int k=i*2+1; k<length; k=k*2+1){//从i结点的左子结点开始，也就是2i+1处开始
			if(order){
				if(k+1<length && data[k]<data[k+1]){//如果左子结点小于右子结点，k指向右子结点
					k++;
				}
				if(data[k] > temp){//如果子节点大于父节点，将子节点赋值给父节点（不用进行交换）
					data[i] = data[k];
					i = k;
				}else{
					break;
				}
			}else{
				if(k+1<length && data[k]>data[k+1]){//如果左子结点小于右子结点，k指向右子结点
					k++;
				}
				if(data[k] < temp){//如果子节点大于父节点，将子节点赋值给父节点（不用进行交换）
					data[i] = data[k];
					i = k;
				}else{
					break;
				}
			}

		}
		data[i] = temp;//将temp值放到最终的位置
	}

	/**
	 * 交换元素
	 * @param data
	 * @param a
	 * @param b
	 */
	private void swap(int[] data, int a, int b){
		int temp = data[a];
		data[a] = data[b];
		data[b] = temp;
	}

}
