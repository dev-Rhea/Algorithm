package DP.Leetcode_1218;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    public int longestSubsequence(int[] arr, int difference) {
        Map<Integer, Integer> dp = new HashMap<>();
        int max = 1;

        for (int a : arr) {
            int prev = dp.getOrDefault(a - difference, 0);
            dp.put(a, prev + 1);

            max = Math.max(max, dp.get(a));
        }

        return max;
    }
}
