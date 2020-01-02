package leetcode.arrary;

/**
 * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai).
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
 * Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 *
 * Note: You may not slant the container and n is at least 2.
 *
 * Example:
 * 	Input: [1,8,6,2,5,4,8,3,7]
 * 	Output: 49
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/3/5 2:11 PM,
 */
public class ContainerWithMostWater{

	public int getMaxArea(int[] height){
		if(height.length == 0){
			return 0;
		}
		int j = height.length-1;
		int maxArea = 0;
		for(int i=0; i<=j;){
			int tmpArea =  Math.min(height[j], height[i]) * (j - i);
			if(tmpArea>maxArea){
				maxArea=tmpArea;
			}
			if(height[i]<=height[j]){
				i++;
			}else if(height[i]>height[j]){
				j--;
			}
		}

		return maxArea;
	}


	public static void main(String[] args){
		ContainerWithMostWater containerWithMostWater = new ContainerWithMostWater();
		int[] height = {1,3,2,5,25,24,5};
		int result  = containerWithMostWater.getMaxArea(height);
		System.out.println(result);
	}

}
