package sort;

/**
 * 归并排序
 *
 * 归并排序，是创建在归并操作上的一种有效的排序算法。算法是采用分治法（Divide and Conquer）的一个非常典型的应用，且各层分治递归可以同时进行。归并排序思路简单，速度仅次于快速排序，为稳定排序算法，一般用于对总体无序，但是各子项相对有序的数列。
 *
 *
 * 1. 基本思想
 *      归并排序是用分治思想，分治模式在每一层递归上有三个步骤：
 *
 *              分解（Divide）：将n个元素分成个含n/2个元素的子序列。
 *              解决（Conquer）：用合并排序法对两个子序列递归的排序。
 *              合并（Combine）：合并两个已排序的子序列已得到排序结果。
 *
 * 2. 优化改进
 *      归并排序和选择排序一样，归并排序的性能不受输入数据的影响，但表现比选择排序好的多，因为始终都是O(n log n）的时间复杂度。代价是需要额外的内存空间。
 * @author tonysu
 * @version 1.0
 * @since 2020/9/3 5:01 下午
 */
public class MergeSort {
    /**
     * 排序函数
     *
     * @param data  输入数组
     * @param order 排序方式：
     *              true为升序
     *              false为降序
     * @return
     */
    public int[] sort(int[] data, boolean order){

        if(data.length == 0 || data == null){
            throw new NullPointerException("数组为空");
        }

        if(data.length == 1){
            return data;
        }

        mergeSortRecursive(data, new int[data.length], 0, data.length-1);
        return data;
    }

    // 归并排序（Java-递归版）
    private static void mergeSortRecursive(int[] arr, int[] result, int start, int end) {
        if (start >= end)
            return;
        int len = end - start, mid = (len >> 1) + start;
        int start1 = start, end1 = mid;
        int start2 = mid + 1, end2 = end;
        mergeSortRecursive(arr, result, start1, end1);
        mergeSortRecursive(arr, result, start2, end2);
        int k = start;
        while (start1 <= end1 && start2 <= end2)
            result[k++] = arr[start1] < arr[start2] ? arr[start1++] : arr[start2++];
        while (start1 <= end1)
            result[k++] = arr[start1++];
        while (start2 <= end2)
            result[k++] = arr[start2++];
        for (k = start; k <= end; k++)
            arr[k] = result[k];
    }

    public static void main(String[] args) {
        int[] num = new int[10];
        num[0] = 0;
        num[1] = 1;
        num[2] = 2;
        num[3] = 4;
        num[4] = 5;
        num[5] = 4;
        num[6] = 5;
        num[7] = 6;
        num[8] = 7;
        num[9] = 11;

        MergeSort mergeSort = new MergeSort();
        for(int value : mergeSort.sort(num, true)){
            System.out.println(value);
        }

    }
}
