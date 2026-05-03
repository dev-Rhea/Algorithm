package binarysearch.Leetcode_875;

public class Solution {

    public int minEatingSpeed(int[] piles, int h) {
        long max = 0;
        long min = 1;
        for (int p : piles) {
            if (p > max) {
                max = p;
            }
        }

        while (min < max) {
            long mid = min + (max - min) / 2;

            if (isPossible(piles, h, mid)) {
                max = mid;
            } else {
                min = mid + 1;
            }
        }

        return (int) min;
    }

    private boolean isPossible(int[] piles, int h, long k) {
        long time = 0;
        for (int p : piles) {
            time += (p + k - 1) / k;
            if (time > h) {
                return false;
            }
        }
        return true;
    }
}
