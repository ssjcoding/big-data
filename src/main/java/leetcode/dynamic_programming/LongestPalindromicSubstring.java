package leetcode.dynamic_programming;

/**
 * 最大回文串
 *
 * Given a string s, find the longest palindromic substring in s.
 * You may assume that the maximum length of s is 1000.
 *
 * Example 1:
 * 		Input: "babad"
 * 		Output: "bab"
 * 		Note: "aba" is also a valid answer.
 * Example 2:
 * 		Input: "cbbd"
 * 		Output: "bb"
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2020/2/24 5:17 下午,
 */
public class LongestPalindromicSubstring{

	public static void main(String[] args){
//		String s = "aaabaaaa";
		String s = "bananas";
		String result = longestPalindrome(s);
		System.out.println(result);
//		System.out.println(s.indexOf(0));
//		System.out.println(s.indexOf(s.charAt(0)));
	}

	/**
	 * 解决思路：
	 * 	采用动态规划方法
	 * 	lps(j) 表示以当前位置字符为结尾的最长回文长度
	 * 	max(lps(j)) =
	 * 		if s[j-lps(j-1)-1] == s[j] then lps(j-1)+2
	 * 		else 以s[j] 为结尾的最长回文长度
	 * @param s
	 * @return
	 */
	public static String longestPalindrome(String s) {
		if(s.length()<=1){
			return s;
		}
		int[] lpsl = new int[s.length()];
		lpsl[0] = 1;
		for(int i=1; i<s.length(); i++){
			int start_index = i-lpsl[i-1]-1;
			if(start_index>=0 && s.charAt(start_index) == s.charAt(i)){
					lpsl[i] = lpsl[i - 1] + 2;
			}else{
				int start = i-lpsl[i-1];
				int end = i+1;
				lpsl[i] = 1;
				while(start<end && start>=0){
					String subString = s.substring(start, end);
					if(isPalindromic(subString)){
						lpsl[i] = subString.length();
						break;
					}
					int index = subString.indexOf(s.charAt(i));
					if(index == 0){
						start += 1;
					}else{
						start += subString.indexOf(s.charAt(i));
					}

				}
			}
		}
		int maxEndIndex = 0;
		for(int i=0; i<lpsl.length; i++){
			if(lpsl[i] > lpsl[maxEndIndex]){
				maxEndIndex = i;
			}
		}

		return s.substring(maxEndIndex-lpsl[maxEndIndex]+1, maxEndIndex+1);
	}

	private static boolean isPalindromic(String str){
		if(str.length()==1){
			return true;
		}
		int startIndex = 0;
		int endIndex = str.length()-1;
		while(startIndex < endIndex){
			if(str.charAt(startIndex) != str.charAt(endIndex)){
				return false;
			}
			startIndex++;
			endIndex--;
		}
		return true;
	}
}
