package leetcode.arrary;

/**
 * 题目：成最多水的容器
 *
 * 描述：给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
 * 		在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。
 * 		找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 说明：你不能倾斜容器。
 *
 * Example:
 * 	Input: [1,8,6,2,5,4,8,3,7]
 * 	Output: 49
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/3/5 2:11 PM,
 */
public class L11_ContainerWithMostWater {

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
		L11_ContainerWithMostWater containerWithMostWater11 = new L11_ContainerWithMostWater();
		int[] height = {1,3,2,5,25,24,5};
		int result  = containerWithMostWater11.getMaxArea(height);
		System.out.println(result);
	}

}
