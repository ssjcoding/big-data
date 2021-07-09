package leetcode.arrary;

import akka.actor.FSM;

/**
 * 题目：在排序数组中查找元素的第一个和最后一个位置
 *
 * 描述：给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 *      如果数组中不存在目标值 target，返回 [-1, -1]
 *
 * 示例：
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 *
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 *
 * 提示：
 * 0 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 * nums 是一个非递减数组
 * -10^9 <= target <= 10^9
 *
 * 进阶：你可以设计一个时间复杂度为 O(log n) 的解决方案吗？
 *
 * @author tonysu
 * @version 1.0
 * @since 2021/7/8 5:41 下午
 */
public class LeetCode_34_SearchRange {

    public static void main(String[] args) {
        int[] nums = {5,7,7,8,8,10};
        int[] result = searchRange(nums, 6);
        System.out.println(result[0] + ", " + result[1]);
    }

    public static int[] searchRange(int[] nums, int target) {
        if(nums.length==0){
            return new int[]{-1, -1};
        }
        int left = 0;
        int right = nums.length-1;
        int mid = (left+right)/2;
        while(left<=right){
            if(nums[mid] > target){
                right = mid-1;
            }else if(nums[mid] < target){
                left = mid+1;
            }else {
                break;
            }
            mid = (left+right)/2;
        }
        if(nums[mid] == target){
            left = mid;
            right = mid;
            while((left-1)>=0&&nums[left-1]==target) left--;
            while ((right+1)<=nums.length-1&&nums[right+1]==target) right++;
            return new int[]{left, right};
        }
        return new int[]{-1, -1};
    }
}
