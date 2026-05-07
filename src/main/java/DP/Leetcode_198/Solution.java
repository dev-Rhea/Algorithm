package DP.Leetcode_198;

public class Solution {

    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        int prev1 = Math.max(nums[0], nums[1]);
        int prev2 = nums[0];

        for (int i = 2; i < nums.length; i++) {
            int now = Math.max(prev1, prev2 + nums[i]);
            prev2 = prev1;
            prev1 = now;
        }

        return prev1;
    }
}
