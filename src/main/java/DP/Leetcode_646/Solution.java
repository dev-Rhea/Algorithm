package DP.Leetcode_646;

import java.util.Arrays;

public class Solution {

    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, (o1, o2) -> o1[0] - o2[0]);

        int[] dp = new int[pairs.length];
        Arrays.fill(dp, 1);

        for (int i = 0; i < pairs.length; i++) {
            for (int j = i + 1; j < pairs.length; j++) {
                if (pairs[i][1] < pairs[j][0]) {
                    dp[j] = dp[i] + 1;
                }
            }
        }

        int cnt = 0;
        for (int i = 0; i < pairs.length; i++) {
            cnt = Math.max(cnt, dp[i]);
        }
        return cnt;
    }
}
