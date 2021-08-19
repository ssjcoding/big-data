package sort;

/**
 * 选择排序
 *
 * 算法描述：
 *      首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置。
 *      再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
 *      重复第二步，直到所有元素均排序完毕。
 *
 * 算法分析：
 *      最差时间复杂度：O(n^2)
 *      最好时间复杂度：O(n^2)
 *      平均时间复杂度：O(n^2)
 *      空间复杂度：　　O(1)
 *      稳定性：　　　　不稳定
 *
 * @author tonysu
 * @version 1.0
 * @since 2021/8/18 3:20 下午
 */
public class SelectSort {
    /**
     * 排序函数
     *
     * @param data  输入数组
     * @return
     */
    public static int[] sort(int[] data){

        if(data.length == 0 || data == null){
            return data;
        }

        if(data.length == 1){
            return data;
        }

        for(int i = 0; i<data.length; i++){
            int min = i;
            for(int j=i+1; j<data.length; j++){
                if(data[j]<data[min]){
                    min = j;
                }
            }
            swap(data, min, i);
        }

        return data;
    }

    // 交换
    public static void swap(int[] data, int min, int i){
        if(min!=i){
            int tmp = data[i];
            data[i] = data[min];
            data[min] = tmp;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 4, 0, 5, 9, 6};
        int[] result = sort(nums);
        for (int num : result){
            System.out.println(num);
        }
    }

}
