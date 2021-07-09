package leetcode.arrary;

import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;

import java.util.Arrays;

/**
 * 题目：下一个排列
 *
 * 描述：实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 *      如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 *      必须 原地 修改，只允许使用额外常数空间。
 *
 * 示例：
 *      输入：nums = [1,2,3]
 *      输出：[1,3,2]
 *
 *      输入：nums = [3,2,1]
 *      输出：[1,2,3]
 *
 *
 * 提示：
 *      1 <= nums.length <= 100
 *      0 <= nums[i] <= 100
 *
 * 字典序（dictionary order），又称 字母序（alphabetical order），原意是表示英文单词在字典中的先后顺序，在计算机领域中扩展成两个任意字符串的大小关系。
 * 从上面的全排列也可以看出来了，从左往右依次增大，对这就是字典序法。可是如何用算法来实现字典序法全排列呢？
 * 我们再来看一段文字描述：（用字典序法找124653的下一个排列）
 *      （1）如果当前排列是124653，找它的下一个排列的方法是，从这个序列中从右至左找第一个左邻小于右邻的数
 *      （2）如果找不到，则所有排列求解完成，如果找得到则说明排列未完成
 *      （3）本例中将找到46，计4所在的位置为i,找到后不能直接将46位置互换，而又要从右到左到第一个比4大的数
 *      （4）本例找到的数是5，其位置计为j，将i与j所在元素交换125643
 *      （5）然后将i+1至最后一个元素从小到大排序得到125346，这就是124653的下一个排列
 *
 * @author tonysu
 * @version 1.0
 * @since 2021/7/5 11:37 上午
 */
public class L27_NextPermutation {
    public static void main(String[] args) {
        int[] nums = {1,3,2};
        nextPermutation(nums);
        for(int num : nums){
            System.out.println(num);
        }
    }

    public static int[] nextPermutation(int[] nums) {
        for(int i=nums.length-1; i>0; i--){
            //从右至左找第一个左邻小于右邻的数
            if(nums[i] > nums[i-1]){
                for(int j=nums.length-1; j>i-1; j--) {
                    if(nums[j] > nums[i-1]){
                        int tmp = nums[i-1];
                        nums[i-1] = nums[j];
                        nums[j] = tmp;
                        Arrays.sort(nums, i, nums.length);
                        return nums;
                    }
                }
            }
        }
        Arrays.sort(nums);
        return nums;
    }
}


