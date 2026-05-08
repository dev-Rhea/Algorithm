package DP.Leetcode_740;

public class Solution {

    public int deleteAndEarn(int[] nums) {
        int[] sum = new int[10001];
        for (int n : nums) {
            sum[n] += n;
        }

        int prev1 = 0, prev2 = 0;
        for (int i = 1; i <= 10000; i++) {
            int now = Math.max(prev1, prev2 + sum[i]);
            prev2 = prev1;
            prev1 = now;
        }

        return prev1;
    }
}
