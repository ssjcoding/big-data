package leetcode.arrary;

import java.util.Arrays;

/**
 * 题目：移除元素
 *
 * 描述：给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 *      不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 *      元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 *
 * 说明：为什么返回数值是整数，但输出的答案是数组呢?
 *      请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 *      你可以想象内部操作如下:
 *          // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
 *          int len = removeElement(nums);
 *          // 在函数里修改输入数组对于调用者是可见的。
 *          // 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
 *          for (int i = 0; i < len; i++) {
 *              print(nums[i]);
 *          }
 *
 * 示例：
 *      输入：nums = [3,2,2,3], val = 3
 *      输出：2, nums = [2,2]
 *      解释：函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。你不需要考虑数组中超出新长度后面的元素。例如，函数返回的新长度为 2 ，而 nums = [2,2,3,3] 或 nums = [2,2,0,0]，也会被视作正确答案。
 *
 * 提示：
 *      0 <= nums.length <= 100
 *      0 <= nums[i] <= 50
 *      0 <= val <= 100
 *
 * @author tonysu
 * @version 1.0
 * @since 2021/7/1 4:08 下午
 */
public class L27_RemoveElement {
    public static void main(String[] args) {
        int[] nums = {1,1};
        System.out.println(removeElement(nums, 2));
    }
    //原理，快慢指针
    public static int removeElement(int[] nums, int val) {
        int i = 0;
        for(int j = 0; j < nums.length; j++) {
            if(nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }
}
