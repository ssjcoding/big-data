package leetcode.arrary;

/**
 * 题目：搜索插入位置
 *
 * 描述：给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 *      你可以假设数组中无重复元素。
 *
 * 示例：
 * 输入：nums = [1,3,5,6], 5
 * 输出：2
 *
 * 输入：[1,3,5,6], 2
 * 输出：1
 *
 *
 * @author tonysu
 * @version 1.0
 * @since 2021/7/8 5:59 下午
 */
public class LeetCode_35_SearchInsert {
    public static void main(String[] args) {
        int[] nums = {1,3,5,6};
        System.out.println(searchInsert(nums, 0));
    }

    public static int searchInsert(int[] nums, int target) {
        if(nums==null || nums.length<1){
            return 0;
        }
        int left = 0;
        int right = nums.length-1;
        int mid = (left+right)/2;
        while(left<=right){
            if(nums[mid]>target){
                right = mid-1;
            }else if(nums[mid]<target){
                left = mid+1;
            }else {
                return mid;
            }
            mid = (left+right)/2;
        }

        if(nums[mid]>target){
            return mid;
        }else {
            return mid+1;
        }
    }
}
