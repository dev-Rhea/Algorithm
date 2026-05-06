package DP.Leetcode_746;

public class Solution {

    public int minCostClimbingStairs(int[] cost) {
        int prev1 = 0, prev2 = 0;

        for (int i = 2; i <= cost.length; i++) {
            int now = Math.min(prev1 + cost[i - 1], prev2 + cost[i - 2]);
            prev2 = prev1;
            prev1 = now;
        }

        return prev1;
    }
}
