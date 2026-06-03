package DP.Leetcode_354;

import java.util.Arrays;

public class Solution {

    public int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;

        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });

        int[] dp = new int[n];
        int len = 0;

        for (int[] e : envelopes) {
            int h = e[1];
            int l = 0;
            int hi = len;

            while (l < hi) {
                int mid = (l + hi) / 2;
                if (dp[mid] < h) {
                    l = mid + 1;
                } else {
                    hi = mid;
                }
            }
            dp[l] = h;
            if (l == len) {
                len++;
            }
        }

        return len;
    }
}
