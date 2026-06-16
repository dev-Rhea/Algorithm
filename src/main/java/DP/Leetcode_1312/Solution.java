package DP.Leetcode_1312;

public class Solution {

    public int minInsertions(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j + i - 1 < n; j++) {
                int k = i + j - 1;
                if (s.charAt(j) == s.charAt(k)) {
                    dp[j][k] = dp[j + 1][k - 1];
                } else {
                    dp[j][k] = Math.min(dp[j + 1][k], dp[j][k - 1]) + 1;
                }
            }
        }

        return dp[0][n - 1];
    }
}
