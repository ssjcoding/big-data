package leetcode.arrary;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 	Description:
 * 		Given an array of integers, return indices of the two numbers
 * 			such that they add up to a specific target.
 *		You may assume that each input would have exactly one solution,
 *			and you may not use the same element twice.
 *
 *
 *	Example:
 *		Given nums = [2, 7, 11, 15], target = 9,
 *
 *		Because nums[0] + nums[1] = 2 + 7 = 9,
 *		return [0, 1].
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2018/3/2 下午2:23,
 */
public class AddTwoSum_1 {

	/**
	 * my solution
	 *
	 * Submission Detail:
	 * 		19 / 19 test cases passed.
	 * 		Runtime: 47 ms
	 * 		runtime beats 20.65 % of java submissions.
	 *
	 * @param nums
	 * @param target
	 * @return
	 */
	public static int[] twoSum(int[] nums, int target) {
		int[] index = new int[2];
		boolean flag = false;
		for(int i=0; i<nums.length; i++){
			for(int j=nums.length-1; j>i; j-- ){
				if(nums[i] + nums[j] == target){
					index[0]=i;
					index[1]=j;
					flag = true;
					break;
				}
			}
			if(flag){
				break;
			}
		}
		return index;
	}

	public static void main(String[] args){
		int[] nums = new int[]{2, 7, 11, 15};
		int target = 9;
		long startTime = System.currentTimeMillis();
		int[] result = twoSum(nums, target);
		long endTime = System.currentTimeMillis();
		System.out.println("返回结果：" + result[0] + "," + result[1]);
		System.out.println("总共耗时：" + (endTime - startTime));
	}



	//---------Official Solution-------

	/**
	 * Approach #1 (Brute Force)
	 *
	 * 	Solution Description:
	 * 		The brute force approach is simple.
	 * 		Loop through each element xx and find if there is another value that equals to target - x.
	 *
	 * 	Complexity Analysis
	 * 		Time complexity: O(n^2).
	 * 			For each element, we try to find its complement by looping through the rest of array which takes O(n) time.
	 * 			Therefore, the time complexity is O(n^2)
	 *		Space complexity: O(1).
	 *
	 * @param nums
	 * @param target
	 * @return
	 */
	public int[] twoSum_1(int[] nums, int target){
		for(int i=0; i<nums.length; i++){
			for(int j=i+1; j<nums.length; j++){
				if(nums[i] == target - nums[j]){
					return new int[]{nums[i], nums[j]};
				}
			}
		}
		throw new IllegalArgumentException("No two sum solution");
	}

	/**
	 * Approach #2 (Two-pass Hash Table)
	 *
	 * 	Solution Description:
	 * 		Reduced the loop up time from O(n) to O(1) by trading space for speed. A hash table is built exactly for this purpose,
	 * 		it supports fast look up in near constant time. I say "near" because if a collision occurred, a look up could degenerate to O(n) time.
	 * 		But look up in hash table should be amortized O(1) time as long as the hash funciton was chosen carefully.
	 *		A simple implementation uses two iterations. In the first iteration, we add each elemet's value and its index to the table.
	 *		Then, in the second iteration we check if each element's complement(target - nums[i])
	 *		exists in the table. Beware that the complement must not be nums[i] itself!
	 *
	 *	Complexity Analisis
	 *		Time complexity: O(n).
	 *			We traverse the list containing n elements exactly twice. Since the hash table reduces the look up time to O(1),
	 *			the	time complexity is O(n).
	 *		Space complexity: O(n).
	 *			The extra space required depends on the number of items stored in the hash table, which stores exactly n elements.
	 *
	 * @param nums
	 * @param target
	 * @return
	 */
	public int[] twoSum_2(int[] nums, int target){
		Map<Integer, Integer> map = new HashMap<>();
		for(int i=0; i<nums.length; i++){
			map.put(nums[i], i);
		}
		for(int i=0; i<nums.length; i++){
			int complement = target - nums[i];
			if(map.containsKey(complement) && map.get(complement) != i){
				return new int[]{i, map.get(complement)};
			}
		}
		throw new IllegalArgumentException("No two sum solutioin");
	}


	/**
	 * Approach #3 (one-pass Hash Table)
	 *
	 * 	Solution Description:
	 * 		It turns out we can do it in one-pass. While we iterate and inserting elements into the table,
	 * 		we also look back to check if current element's complement already exists in the table.
	 * 		If it exists, we have found a solution and return immediately.
	 *
	 *	Complexity Analisis
	 *		Time complexity: O(n).
	 *			We traverse the list containing n elements only once. Each look up in the table costs only O(1) time.
	 *		Space complexity: O(n).
	 *			The extra space required depends on the number of items stored in the hash table, which stores at most n elements.
	 *
	 * @param nums
	 * @param target
	 * @return
	 */
	public int[] twoSum_3(int[] nums, int target){
		Map<Integer, Integer> map = new HashMap<>();
		for(int i=0; i<nums.length; i++){
			int complement = target - nums[i];
			if(map.containsKey(complement)){
				return new int[]{map.get(complement), i};
			}
			map.put(nums[i], i);
		}
		throw new IllegalArgumentException("No two sum solution");
	}


}
