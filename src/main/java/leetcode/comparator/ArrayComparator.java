package leetcode.comparator;

import sort.SelectSort;

import java.util.Arrays;

/**
 * 数组类型对数器
 *    生成随机数组
 *    对比排序结果是否相同
 *
 * @author tonysu
 * @version 1.0
 * @since 2021/8/18 6:19 下午
 */
public class ArrayComparator {
    /**
     * 生成随机个数的随机数组
     * @param maxSize：数组最大size
     * @param maxValue：数组中最大值
     * @return
     */
    public static int[] generateRandomArray(int maxSize, int maxValue){
        int[] array = new int[(int)((maxSize+1)*Math.random())];
        for(int i=0; i<array.length; i++){
            array[i] = (int)((maxValue+1)*Math.random() - (maxValue+1)*Math.random());
        }
        return array;
    }

    public static int[] copyArray(int[] array){
        return Arrays.copyOf(array, array.length);
    };

    public static void comparator(int[] array){
        Arrays.sort(array);
    }

    public static boolean isEqual(int[] arr1, int[] arr2){
        if(arr1==null || arr2==null){
            return false;
        }

        if(arr1.length != arr2.length){
            return false;
        }

        for(int i=0; i<arr1.length; i++){
            if(arr1[i]!=arr2[i]){
                return false;
            }
        }
        return true;
    }

    public static void printArray(int[] array){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        if(array!=null && array.length>0){
            for(int i=0; i<array.length; i++){
                stringBuilder.append(array[i]).append(",");
            }
        }
        stringBuilder.append("]");
        System.out.println(stringBuilder.toString());
    }

    public static void main(String[] args) {
        int testTime = 1000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for(int i=0; i<testTime; i++){
            int[] arr1 = generateRandomArray(maxSize,maxValue);
            int[] arr2 = copyArray(arr1);
            SelectSort.sort(arr1);
            comparator(arr2);
            if(!isEqual(arr1,arr2)){
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked");
    }


}
