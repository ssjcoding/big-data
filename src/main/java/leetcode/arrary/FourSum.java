package leetcode.arrary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/11/18 10:37 PM,
 */
public class FourSum{
	public static void main(String[] args){
		int[] test = {1, 0, -1, 0, -2, 2};
		FourSum fourSum = new FourSum();
		int target=0;
		List<List<Integer>> result = fourSum.fourSum(test, target);
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
		if(nums.length < 4){
			return result;
		}
		Arrays.sort(nums);
		if(nums[0] > target || (nums[0]+nums[1]+nums[2]+nums[3])>target){
			return result;
		}
		for(int i=0; i<nums.length; i++){
			for(int j=i+1; j<nums.length; j++){
				int low = j+1;
				int heigh = nums.length-1;
				while(low < heigh){
					int sum = nums[i] + nums[j] + nums[low] + nums[heigh];
					if(sum == target){
						result.add(Arrays.asList(nums[i], nums[j], nums[low], nums[heigh]));
						do{
							low++;
						}while(low<heigh && nums[low]==nums[low+1]);
					}else if(sum<target){
						do{
							low++;
						}while(low<heigh && nums[low]==nums[low+1]);
					}else{
						do{
							heigh--;
						}while(low<heigh && nums[heigh]==nums[heigh-1]);
					}
				}
			}
		}
		return result;
	}
}
