package DP.Leetcode_931;

public class Solution {

    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int[] dp = new int[n];

        for (int j = 0; j < n; j++) {
            dp[j] = matrix[0][j];
        }

        for (int i = 1; i < n; i++) {
            int[] next = new int[n];
            for (int j = 0; j < n; j++) {
                int min = dp[j];

                if (j > 0) {
                    min = Math.min(min, dp[j - 1]);
                }
                if (j < n - 1) {
                    min = Math.min(min, dp[j + 1]);
                }
                next[j] = matrix[i][j] + min;
            }
            dp = next;
        }

        int ans = dp[0];
        for (int j = 1; j < n; j++) {
            ans = Math.min(dp[j], ans);
        }

        return ans;
    }
}
