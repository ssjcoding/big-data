package leetcode.dynamic_programming;

/**
 * Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.
 * <p>
 * 算法地址：https://www.sohu.com/a/338910260_478315
 * <p>
 * You have the following 3 operations permitted on a word:
 * Insert a character
 * Delete a character
 * Replace a character
 * <p>
 * Example 1:
 * Input: word1 = "horse", word2 = "ros"
 * Output: 3
 * Explanation:
 * horse -> rorse (replace 'h' with 'r')
 * rorse -> rose (remove 'r')
 * rose -> ros (remove 'e')
 * <p>
 * Example 2:
 * Input: word1 = "intention", word2 = "execution"
 * Output: 5
 * Explanation:
 * intention -> inention (remove 't')
 * inention -> enention (replace 'i' with 'e')
 * enention -> exention (replace 'n' with 'x')
 * exention -> exection (replace 'n' with 'c')
 * exection -> execution (insert 'u')
 * <p>
 * if s1[i]==s2[j]:	dp[i][j] = dp[i-1][j-1]
 * if s1[i]!=s2[j]:
 * dp[i][j] = min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1]) + 1
 * 插入		删除		替换
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2020/2/24 7:48 下午,
 */
public class EditDistance {

    public static void main(String[] args) {
        String s1 = "intention";
        String s2 = "execution";
        System.out.println(minDistanceForce(s1, s2));
        System.out.println(minDistanceDpTable(s1, s2));
    }

    /**
     * 获取动态规划
     * 暴力求解(s1->s2)
     *
     * @param s1
     * @param s2
     * @return
     */
    public static int minDistanceForce(String s1, String s2) {
        return dp(s1, s2, s1.length() - 1, s2.length() - 1);
    }

    public static int dp(String s1, String s2, int i, int j) {
        //s1移动到头
        if (i == -1) {
            return j;
        }
        //s2移动到头
        if (j == -1) {
            return i;
        }
        if (s1.charAt(i) == s2.charAt(j)) {
            // 相等，上都不做
            return dp(s1, s2, i - 1, j - 1);
        } else {
            return min(
                    // 删除
                    dp(s1, s2, i - 1, j) + 1,
                    // 添加
                    dp(s1, s2, i, j - 1) + 1,
                    // 替换
                    dp(s1, s2, i - 1, j) + 1
            );
        }
    }

    /**
     * 采用DP Table去重方式计算
     * @param s1
     * @param s2
     * @return
     */
    public static int minDistanceDpTable(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        // base case
        for (int i = 1; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j < n; j++) {
            dp[0][j] = j;
        }
        // 自底向上
        for (int i = 1; i <= m; i++) {
            for (int j = 1; i <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = min(
                            dp[i - 1][j] + 1,
                            dp[i][j - 1] + 1,
                            dp[i - 1][j - 1] + 1
                    );
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 计算最小值
     * @param a
     * @param b
     * @param c
     * @return
     */
    public static int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }


}
