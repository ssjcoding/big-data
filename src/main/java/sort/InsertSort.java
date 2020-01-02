package sort;

/**
 * 插入排序
 *
 * 算法描述：
 *      将n个元素的数列分为已有序和无序两个部分，每次处理就是将无序数列的第一个元素与有序数列的元素从后往前逐个进行比较，
 *      找出插入位置，将该元素插入到有序数列的合适位置中。
 *
 * 算法分析：
 *      最差时间复杂度：O(n^2)
 *      最好时间复杂度：O(n)
 *      平均时间复杂度：O(n^2)
 *      空间复杂度：　　O(1)
 *      稳定性：　　　　稳定
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2017/11/6 下午5:25,
 */
public class InsertSort{

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

        for(int i = 1; i<data.length; i++){
            int key = data[i];
            int j = i - 1;
            if(order){
                while(j>=0 && key<data[j]){
                    data[j+1] = data[j];
                    j--;
                }
            }else{
                while(j>=0 && key>data[j]){
                    data[j+1] = data[j];
                    j--;
                }
            }

            data[j+1] = key;
        }

        return data;
    }
}
