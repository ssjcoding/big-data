package sort;

/**
 * 折半插入排序
 *
 * 算法描述：
 *      折半插入排序是直接插入排序的一种优化，
 *      在直接插入排序中待排序的元素需要与有序数列的每个元素从后往前逐个进行比较，
 *      直接插入排序对基本有序数列具有很高的排序效率，但是当乱序情况下，其比较次数会很多。
 *      折半插入排序在直接排序的基础上在位置查找部分采用折半（二分查找）算法进行插入位置的确定，进而节省查找时间。
 *
 * 算法分析：
 *      最差时间复杂度：O(n^2)
 *      最好时间复杂度：O(nlogn)
 *      平均时间复杂度：O(n^2)
 *      空间复杂度：　　O(1)
 *      稳定性：　　　　稳定
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2017/11/12 下午9:13,
 */
public class BinaryInsertSort{

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

        for(int i=1; i<data.length; i++){

            int key = data[i];
            int left = 0;
            int right = i - 1;
            if(order){
                while(left <= right){
                    int mid = (left+right)/2;
                    if(data[mid] > key){
                        right = mid - 1;
                    }else{
                        left = mid + 1;
                    }
                }

            }else{
                while(left <= right){
                    int mid = (left+right)/2;
                    if(data[mid] < key){
                        right = mid - 1;
                    }else{
                        left = mid + 1;
                    }
                }
            }

            for(int j=i-1; j>=left; j--){
                data[j+1] = data[j];
            }

            data[left] = key;

        }

        return data;
    }
}
