package leetcode.arrary;

import org.apache.commons.lang.StringUtils;

import java.util.Arrays;

/**
 * 题目：删除有序数组中的重复项
 *
 * 描述：给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
 *      不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 * 说明：为什么返回数值是整数，但输出的答案是数组呢?
 *      请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 *      你可以想象内部操作如下:
 *          // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
 *          int len = removeDuplicates(nums);
 *          // 在函数里修改输入数组对于调用者是可见的。
 *          // 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
 *          for (int i = 0; i < len; i++) {
 *              print(nums[i]);
 *          }
 *
 * 示例：
 *      输入：nums = [1,1,2]
 *      输出：2, nums = [1,2]
 *      解释：函数应该返回新的长度 2 ，并且原数组 nums 的前两个元素被修改为 1, 2 。不需要考虑数组中超出新长度后面的元素。
 *
 * 提示：
 *      0 <= nums.length <= 3 * 104
 *      -104 <= nums[i] <= 104
 *      nums 已按升序排列
 *
 *
 * @author tonysu
 * @version 1.0
 * @since 2021/6/28 6:19 下午
 */
public class LeetCode_26_RemoveDuplicateItemsFromAnOrderedArray {
    public static void main(String[] args) {
        int[] nums = {1,1,2};
        System.out.println(removeDuplicates(nums));
        System.out.println("=========>");
        for(int num : nums){
            System.out.println(num);
        }
    }

    public static int removeDuplicates(int[] nums) {
        if(nums.length<2){
            return nums.length;
        }
        int referNum = nums[0];
        int start = 1;
        int end = nums.length-1;
        do{
            if(referNum==nums[start]){
                for(int i=start; i<end; i++){
                    nums[i] = nums[i+1] ;
                }
                end--;
            }else {
                referNum = nums[start];
                start++;
            }
        }while (start<=end);

        return end+1;
    }

    public static int removeDuplicates_v2(int[] nums){
        int i = 0;
        for(int j=1;j<nums.length;j++){
            if(nums[i] != nums[j]){
                nums[++i] = nums[j];
            }
        }
        return ++i;
    }

    public int removeDuplicates_v3(int[] nums) {
        // i 指向最后一个唯一的元素
        int i = 0;
        // j 从 1 开始，依次指向所有元素
        int j = 1;
        for (; j < nums.length; j++) {
             /*
             如果第 j 个元素不等于当前最后一个唯一的元素
             代表第 j 个元素也是一个唯一的元素
             */
            if (nums[i] != nums[j]) {
                // 唯一元素的个数加 1
                i++;
                // 新增唯一的元素
                nums[i] = nums[j];
            }
        }
        // 返回唯一元素个数
        return i + 1;
    }
}
