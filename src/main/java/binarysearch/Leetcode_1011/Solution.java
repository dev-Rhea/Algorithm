package binarysearch.Leetcode_1011;

public class Solution {

    public int shipWithinDays(int[] weights, int days) {
        int min = 0;
        int max = 0;
        for (int w : weights) {
            min = Math.max(min, w);
            max += w;
        }

        while (min < max) {
            int mid = (min + max) / 2;

            if (isPossible(weights, days, mid)) {
                max = mid;
            } else {
                min = mid + 1;
            }
        }

        return min;
    }

    private boolean isPossible(int[] weights, int days, int max) {
        int day = 1;
        int now = 0;

        for (int w : weights) {
            if (now + w > max) {
                day++;
                now = w;
            } else {
                now += w;
            }
        }
        return day <= days;
    }

}
