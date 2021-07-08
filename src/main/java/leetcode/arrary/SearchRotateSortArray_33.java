package leetcode.arrary;

/**
 * 题目：搜索旋转排序数组
 * <p>
 * 描述：整数数组 nums 按升序排列，数组中的值 互不相同 。
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
 * <p>
 * 注意：答案中不可以包含重复的四元组
 * 示例：
 * 输入：nums = [4,5,6,7,0,1,2], target = 0
 * 输出：4
 * <p>
 * 输入：nums = [4,5,6,7,0,1,2], target = 3
 * 输出：-1
 * <p>
 * 提示：
 * 1 <= nums.length <= 5000
 * -10^4 <= nums[i] <= 10^4
 * nums 中的每个值都 独一无二
 * 题目数据保证 nums 在预先未知的某个下标上进行了旋转
 * -10^4 <= target <= 10^4
 * <p>
 * 进阶：你可以设计一个时间复杂度为 O(log n) 的解决方案吗？
 *
 * 参考：https://blog.csdn.net/qq_39360985/article/details/83793352
 *
 * @author tonysu
 * @version 1.0
 * @since 2021/7/7 10:40 上午
 */
public class SearchRotateSortArray_33 {
    public static void main(String[] args) {
        int[] nums = {5,1,2,3,4};
        System.out.println(search(nums, 1));
    }

    public static int search(int[] nums, int target) {
        if(nums.length<1){
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (right + left) / 2;
            if(target==nums[left]){
                return left;
            }
            if(target==nums[right]){
                return right;
            }
            if(target==nums[mid]){
                return mid;
            }
            if(nums[mid] > nums[0]){
                if(target>nums[mid]){
                    left = mid+1;
                }else if(target<nums[mid]){
                    if(target>nums[0]){
                        right = mid-1;
                    }else {
                        left = mid+1;
                    }
                }
            }else {
                if(target<nums[mid]){
                    right = mid-1;
                }else {
                    if(target>nums[0]){
                        right = mid-1;
                    }else {
                        left = mid+1;
                    }
                }
            }
        }
        return -1;
    }
}
