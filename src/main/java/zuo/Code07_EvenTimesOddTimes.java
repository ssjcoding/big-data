package zuo;

/**
 * 异或操作
 *
 * @author tonysu
 * @version 1.0
 * @since 2021/8/19 3:58 下午
 */
public class Code07_EvenTimesOddTimes {

    /**
     * 题目：
     *  打印数据中最右侧出现的1
     * 思路：
     *  采用先取反+1 再异或得到  [(num)&(~num+1)]
     * @param num
     */
    public static void printRightOne(int num){
        System.out.println(Math.abs(num&((~num)+1)));
    }

    /**
     * 题目：
     *  输出二进制为1的位数
     * @param num
     * @return
     */
    public static void bit1Counts(int num){
        int count=0;
        while(num != 0){
            int rightOne = num & ((~num) + 1);
            count++;
            num ^= rightOne;
        }
        System.out.println(count);
    }

    /**
     * 题目：
     *  一个数组中有一种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这种数
     * 思路：
     *  采用异或操作，遍历一遍，剩下的数据即为此数
     * @param arr
     */
    public static void printOddTimesNum1(int[] arr){
        int eor=0;
        for(int i=0; i<arr.length; i++){
            eor = eor ^ arr[i];
        }
        System.out.println(eor);
    }

    /**
     * 题目
     *  一个数组中有两种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这两种数
     *
     * 思路
     *  （1）首先遍历一遍数组数据，对所有数据进行异或操作得到eor
     *  （2）eor1转换为二进制，得到其中任意一位为1的位【用(eor&(~eor+1))得到最右侧一个为1的数】
     *  （3）然后再遍历一遍数组，用（2）中同位为1的数据求异或，得到a，b中的一个
     *
     * @param arr
     */
    public static void printOddTimesNum2(int[] arr){
        int eor = 0;
        for(int i=0; i<arr.length; i++){
            eor = eor ^ arr[i];
        }

        int eor1 = 0;
        int rightOne = eor&(~eor+1);
        for(int i=0; i<arr.length; i++){
            if((arr[i] & rightOne) != 0){
                eor1 = eor1^arr[i];
            }
        }

        System.out.println(eor1);

        System.out.println(eor^eor1);
    }

    public static void main(String[] args) {
//        printRightOne(12);
//        int[] array = {1,1,1,1,2,2,2,2,3,3,3,5,5,5,5,0,0,0,0,1};
//        printOddTimesNum2(array);
        bit1Counts(7);
    }
}
