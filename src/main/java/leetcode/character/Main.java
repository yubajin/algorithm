package leetcode.character;

public class Main {
    public static void main(String[] args) {

    }

    /**
     * 经典：
     * 最长公共子序列
     */

    /**
     * 1143. 最长公共子序列
     * 输入：text1 = "abcde", text2 = "ace"
     * 输出：3
     * 解释：最长公共子序列是 "ace" ，它的长度为 3
     *
     * 主要就是两大情况：text1[i - 1] 与 text2[j - 1]相同，text1[i - 1] 与 text2[j - 1]不相同
     * 如果text1[i - 1] 与 text2[j - 1]相同，那么找到了一个公共元素，所以dp[i][j] = dp[i - 1][j - 1] + 1;
     * 如果text1[i - 1] 与 text2[j - 1]不相同，那就看看text1[0, i - 2]与text2[0, j - 1]的最长公共子序列 和 text1[0, i - 1]与text2[0, j - 2]的最长公共子序列，取最大的。
     * 即：dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]);。
     */
    public int longestCommonSubsequence(String text1, String text2) {
        // dp数组的定义
        int dp[][] = new int[text1.length()+1][text2.length()+1];
        // dp数组初始化, dp[0][*] = 0, dp[*][0] = 0
        // dp数组遍历
        for (int i = 1; i <= text1.length(); i++) {
            for (int j = 1; j <= text2.length(); j++) {
                if(text1.charAt(i-1) == text2.charAt(j-1)){
                    // dp数组递推公式
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else{
                    // dp数组递推公式
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[text1.length()][text2.length()];
    }

}
