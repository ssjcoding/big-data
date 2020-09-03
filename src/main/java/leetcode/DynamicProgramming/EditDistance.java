package leetcode.DynamicProgramming;

/**
 * Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.
 *
 * You have the following 3 operations permitted on a word:
 * 		Insert a character
 * 		Delete a character
 * 		Replace a character
 *
 * Example 1:
 * 		Input: word1 = "horse", word2 = "ros"
 * 		Output: 3
 * 		Explanation:
 * 			horse -> rorse (replace 'h' with 'r')
 * 			rorse -> rose (remove 'r')
 * 			rose -> ros (remove 'e')
 *
 * Example 2:
 * 		Input: word1 = "intention", word2 = "execution"
 * 		Output: 5
 * 		Explanation:
 * 			intention -> inention (remove 't')
 * 			inention -> enention (replace 'i' with 'e')
 * 			enention -> exention (replace 'n' with 'x')
 * 			exention -> exection (replace 'n' with 'c')
 * 			exection -> execution (insert 'u')
 *
 * 	if s1[i]==s2[j]:	dp[i][j] = dp[i-1][j-1]
 * 	if s1[i]!=s2[j]:
 * 		dp[i][j] = min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1]) + 1
 *						插入		删除		替换
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2020/2/24 7:48 下午,
 */
public class EditDistance{
}
