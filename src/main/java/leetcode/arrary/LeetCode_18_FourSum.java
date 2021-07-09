package leetcode.arrary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 题目：四数之和
 *
 * 描述：给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 * 注意：答案中不可以包含重复的四元组
 * 示例：
 *      输入：nums = [1,0,-1,0,-2,2], target = 0
 *      输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 *
 * 提示：
 *      0 <= nums.length <= 200
 *      -109 <= nums[i] <= 109
 *      -109 <= target <= 109
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/11/18 10:37 PM,
 */
public class LeetCode_18_FourSum {
	public static void main(String[] args){
		int[] test = {1, 0, -1, 0, -2, 2};
		LeetCode_18_FourSum fourSum18 = new LeetCode_18_FourSum();
		int target=0;
		List<List<Integer>> result = fourSum18.fourSum(test, target);
		System.out.println("---------------------------------------------------");
		for(List<Integer> list : result){
			if(list.size() == 4){
				System.out.println(list.get(0) + ", " + list.get(1) + ", " + list.get(2) + ", " + list.get(3));
			}
		}
		System.out.println("---------------------------------------------------");
	}

	public List<List<Integer>> fourSum(int[] nums, int target) {
		List<List<Integer>> result = new ArrayList<>();
		//不够四个数，自然无意义
		if(nums.length < 4){
			return result;
		}
		//同样先从小到大排序
		Arrays.sort(nums);
		if((nums[0]+nums[1]+nums[2]+nums[3])>target){
			return result;
		}
		int length = nums.length;
		for(int i=0; i<nums.length-3; i++){
			// 找a，与上一个数相同，跳过，否则就重复了
			if (i != 0 && nums[i] == nums[i - 1]) {
				continue;
			}
			// 如果从这里开始的连续四个数就已经比目标大了，那说明不会存在这样的四个数，因为此时已经是最小了
			if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
				break;
			}
			//如果当前数和后三个数相加都比目标小，那说明肯定找不到a为当前数的四个数组合，因为此时是a固定后的最大值
			if (nums[i] + nums[length - 3] + nums[length - 2] + nums[length - 1] < target) {
				continue;
			}
			//找b，后面两个数要留给c和d，因此小于length-2
			for(int j=i+1; j<nums.length-2; j++){
				if (j != i + 1 && nums[j] == nums[j - 1])
					//找b，与上一个b相同，跳过，否则会有重复
					continue;
				//当前四个数相加都大于目标，那么不存在四个数相加等于目标，因为此时已经是最小了
				if (nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {
					break;
				}
				//如果当前a和b和后两个数相加都比目标小，那说明肯定找不到a，b为当前数的四个数组合，因为此时是a，b固定后的最大值
				if (nums[i] + nums[j] + nums[length - 2] + nums[length - 1] < target) {
					continue;
				}
				int low = j+1;
				int heigh = nums.length-1;
				while(low < heigh){
					int sum = nums[i] + nums[j] + nums[low] + nums[heigh];
					if(sum == target){
						result.add(Arrays.asList(nums[i], nums[j], nums[low], nums[heigh]));
						//这才是跳出了下一个c
						low++;
						//这才是跳出了下一个d
						heigh--;
						//这时还只是移动到下一个c
						while(low<heigh && nums[low]==nums[low-1]){
							low++;
						}
						//如果d与上一个d相同，跳过
						while(low<heigh && nums[heigh]==nums[heigh+1]){
							//这时还只是移动到下一个d
							heigh--;
						}
					}else if(sum<target){
						//此时要让c增大，d已经到最大了
						low++;
					}else{
						//此时要让d减小，c已经到最小了
						heigh--;
					}
				}
			}
		}
		return result;
	}
}
