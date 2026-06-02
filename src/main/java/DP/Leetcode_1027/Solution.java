package DP.Leetcode_1027;

public class Solution {

    public int longestArithSeqLength(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][1001]; // [i번째 원소][수열 최대 길이]
        int ans = 2;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int diff = nums[i] - nums[j] + 500;
                dp[i][diff] = Math.max(dp[i][diff], dp[j][diff] + 1);
                ans = Math.max(ans, dp[i][diff] + 1);
            }
        }
        return ans;
    }
}
