package leetcode.arrary;

/**
 * 题目：寻找两个正序数组的中位数
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 * 示例 1：
 *      输入：nums1 = [1,3], nums2 = [2]
 *      输出：2.00000
 *      解释：合并数组 = [1,2,3] ，中位数 2
 *  提示：
 *      nums1.length == m
 *      nums2.length == n
 *      0 <= m <= 1000
 *      0 <= n <= 1000
 *      1 <= m + n <= 2000
 *      -106 <= nums1[i], nums2[i] <= 106
 *
 * @author tonysu
 * @version 1.0
 * @since 2021/6/24 2:33 下午
 */
public class LeetCode_4_FindMedianSortedArrays {
    public static void main(String[] args) {
        int[] nums1 = {1,3};
        int[] nums2 = {3,4};
        System.out.println(findMedianSortedArrays_1(nums1, nums2));
//        System.out.println((int)Math.ceil((float) 2/2));
//        System.out.println((int)Math.floor((float) 2/2));
    }

    /**
     * 先合并，再取中位数
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] copy_nums;
        if(nums1.length==0 && nums2.length==0){
            return 0;
        }else if(nums1.length==0){
            copy_nums = nums2;
        }else if(nums2.length==0){
            copy_nums = nums1;
        }else {
            copy_nums = new int[nums1.length + nums2.length];
            boolean flag = true;
            int nums1_index = 0;
            int copy_nums_index = 0;
            int nums2_index = 0;
            while(flag){
                if(nums1[nums1_index]<=nums2[nums2_index]){
                    copy_nums[copy_nums_index] = nums1[nums1_index];
                    nums1_index++;
                    copy_nums_index++;
                }else {
                    copy_nums[copy_nums_index] = nums2[nums2_index];
                    nums2_index++;
                    copy_nums_index++;
                }
                if(nums1_index == nums1.length){
                    for(int i=nums2_index; i<nums2.length; i++){
                        copy_nums[copy_nums_index] = nums2[i];
                        copy_nums_index++;
                    }
                    flag = false;
                }else if(nums2_index == nums2.length){
                    for(int i=nums1_index; i<nums1.length; i++){
                        copy_nums[copy_nums_index] = nums1[i];
                        copy_nums_index++;
                    }
                    flag = false;
                }
            }
        }
        if(copy_nums.length%2 == 0){
            return (double) (copy_nums[copy_nums.length/2]+copy_nums[copy_nums.length/2 - 1])/2;
        }else{
            return copy_nums[copy_nums.length/2];
        }
    }

    /**
     * 简化写法
     */
    public static double findMedianSortedArrays_1(int[] nums1, int[] nums2) {
        int[] nums = new int[nums1.length+nums2.length];
        int index1 = 0, index2 = 0;
        for(int i = 0;i < nums.length;i++) {
            if(index2 >= nums2.length || index1 < nums1.length && nums1[index1] < nums2[index2]) {
                nums[i] = nums1[index1++];
            }else {
                nums[i] = nums2[index2++];
            }
        }
        int midleIndex = nums.length / 2;
        return nums.length % 2 == 0 ? (1.0*(nums[midleIndex] + nums[midleIndex-1]) / 2):nums[midleIndex];
    }
}
