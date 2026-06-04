package DP.Leetcode_1964;

public class Solution {

    public int[] longestObstacleCourseAtEachPosition(int[] obstacles) {
        int n = obstacles.length;
        int[] ans = new int[n];
        int[] tails = new int[n];
        int len = 0;

        for (int i = 0; i < n; i++) {
            int l = 0;
            int h = len;

            while (l < h) {
                int mid = (l + h) / 2;
                if (tails[mid] <= obstacles[i]) {
                    l = mid + 1;
                } else {
                    h = mid;
                }
            }
            tails[l] = obstacles[i];
            if (l == len) {
                len++;
            }
            ans[i] = l + 1;
        }
        return ans;
    }
}
