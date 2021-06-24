package leetcode.arrary;

import java.util.*;

/**
 * 三个整数求和
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/10/21 10:13 AM,
 */
public class ThreeSum{

	public static void main(String[] args){
		int[] test = {-1, 0, 1, 2, -1, -4};
//		int[] test = {-1, 0, 1};
		ThreeSum threeSum = new ThreeSum();
		List<List<Integer>> result = threeSum.threeSum(test);
		System.out.println("---------------------------------------------------");
		for(List<Integer> list : result){
			if(list.size() == 3){
				System.out.println(list.get(0) + ", " + list.get(1) + ", " + list.get(2));
			}
		}
		System.out.println("---------------------------------------------------");
	}

	/**
	 * 1、采用排序算法进行排序,时间复杂度O(nlogn)
	 * 2、从左往右遍历a，时间复杂度O(n)；然后b+c=0-a，b与c采用二分查找，时间复杂度O(logn)；
	 * 第二部整体时间复杂度O(nlogn)
	 * 3、此算法整体时间复杂度O(nlogn) + O(nlogn) = 2*O(nlogn) = O(nlogn)
	 **/
	public List<List<Integer>> threeSum(int[] nums){
		List<List<Integer>> result = new ArrayList<>();
		if(nums.length>=3){
			//排序
			int[] sortNums = sort(nums, 0, nums.length - 1);
			//寻找
			result = find(sortNums);
		}
		for(int num : nums){
			System.out.println(num);
		}
		return result;
	}

	/**
	 * 二分查找查找
	 **/
	public List<List<Integer>> find(int[] nums){
		Map<String, List<Integer>> result = new HashMap<>();
		for(int i = 0; i<nums.length; i++){
			int firstNum = nums[i];
			int leftNum = 0 - firstNum;
			for(int j = i + 1; j<nums.length; j++){
				int secondNum = nums[j];
				int secondLeftNum = leftNum - secondNum;
				int start = j + 1;
				int end = nums.length - 1;
				int middle = (start + end) / 2;
				System.out.println("============" + firstNum + ", " + secondNum + ", " + secondLeftNum);
				System.out.println("--------------index: " + start + ", " + middle + ", " + end);
				String key = firstNum + "_" + secondNum + "_" + secondLeftNum;
				while(start<middle){
					if(secondLeftNum == nums[middle]){
						List<Integer> tmp = new ArrayList<>();
						tmp.add(firstNum);
						tmp.add(secondNum);
						tmp.add(nums[middle]);
						result.put(key, tmp);
						break;
					}else if(secondLeftNum<nums[middle]){
						end = middle;
					}else{
						start = middle;
					}
					middle = (start + end) / 2;
				}

				if(start == middle){
					List<Integer> tmp = new ArrayList<>();
					if(secondLeftNum == nums[middle]){
						tmp.add(firstNum);
						tmp.add(secondNum);
						tmp.add(nums[middle]);
						result.put(key, tmp);
					}else if(secondLeftNum == nums[end]){
						tmp.add(firstNum);
						tmp.add(secondNum);
						tmp.add(nums[end]);
						result.put(key, tmp);
					}

				}
			}
		}

		return new ArrayList<>(result.values());
	}

	/**
	 * 归并排序
	 **/
	public int[] sort(int[] nums, int start, int end){
		int startOrigin = start;
		int endOrigin = end;
		int leftMiddle = (start + end) / 2;
		int rightMiddle = leftMiddle + 1;
		if(start == end){
			return nums;
		}
		sort(nums, start, leftMiddle);
		sort(nums, rightMiddle, end);

		//merge逻辑
		int[] tmp = new int[end - start + 1];
		int length = tmp.length;
		for(int index = 0; index<length; index++){
			if(start<=leftMiddle && rightMiddle<=end && nums[start]<=nums[rightMiddle]){
				tmp[index] = nums[start];
				start++;
			}else if(start<=leftMiddle && rightMiddle<=end && nums[start]>nums[rightMiddle]){
				tmp[index] = nums[rightMiddle];
				rightMiddle++;
			}else if(start<=leftMiddle){
				tmp[index] = nums[start];
				start++;
			}else{
				tmp[index] = nums[rightMiddle];
				rightMiddle++;
			}
		}

		for(int num : tmp){
			nums[startOrigin] = num;
			startOrigin++;
		}
		return nums;
	}

	/**
	 * 优化后查找答案，查找采用指针
	 */
	public List<List<Integer>> find2(int[] nums){
		List<List<Integer>> result = new ArrayList<>();
		for(int i = 0; i<nums.length; i++){
			if (i > 0 && nums[i] == nums[i - 1]) {
				continue;
			}
			int target = -nums[i];
			int low = i+1;
			int high = nums.length-1;
			while(low < high){
				if(nums[low]+nums[high]==target){
					result.add(Arrays.asList(nums[i], nums[low], nums[high]));
					low++;
					high--;
					while(low<high && nums[low] == nums[low-1]) low++;
					while(high>low && nums[high] == nums[high+1]) high++;
				}else if(nums[low]+nums[high]<target){
					low++;
				}else{
					high--;
				}
			}
		}
		return result;
	}


}
