package DP.Leetcode_673;

import java.util.Arrays;

public class Solution {

    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int[] len = new int[n];
        int[] cnt = new int[n];

        Arrays.fill(len, 1);
        Arrays.fill(cnt, 1);

        int max = 1;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    if (len[j] + 1 > len[i]) {
                        len[i] = len[j] + 1;
                        cnt[i] = cnt[j];
                    } else if (len[j] + 1 == len[i]) {
                        cnt[i] += cnt[j];
                    }
                }
            }
            max = Math.max(max, len[i]);
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (len[i] == max) {
                ans += cnt[i];
            }
        }

        return ans;
    }
}
