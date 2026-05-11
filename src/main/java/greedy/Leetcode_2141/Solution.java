package greedy.Leetcode_2141;

import java.util.Arrays;

public class Solution {

    public long maxRunTime(int n, int[] batteries) {
        Arrays.sort(batteries);

        long total = (long) Arrays.stream(batteries).sum();

        for (int i = batteries.length - 1; i >= 0; i--) {
            if (batteries[i] <= total / n) {
                break;
            }

            total -= batteries[i];
            n--;
        }
        return total / n;
    }
}
