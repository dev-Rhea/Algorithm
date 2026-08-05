package binarysearch.Leetcode_2064;

import java.util.Arrays;

public class Solution {

    public int minimizedMaximum(int n, int[] quantities) {
        int m = quantities.length;

        int low = 1;
        int high = Arrays.stream(quantities).max().getAsInt();

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (isPossible(quantities, mid, n)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    private boolean isPossible(int[] qt, int mid, int n) {
        long r = 0;
        for (int i = 0; i < qt.length; i++) {
            r += (qt[i] + mid - 1) / mid;
            if (r > n) {
                return false;
            }
        }

        return true;
    }
}
