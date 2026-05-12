package implementation.Leetcode_1499;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {

    public int findMaxValueOfEquation(int[][] points, int k) {
        int max = Integer.MIN_VALUE;
        Deque<int[]> queue = new ArrayDeque<>();

        for (int[] p : points) {
            int x = p[0];
            int y = p[1];

            while (!queue.isEmpty() && x - queue.peekFirst()[1] > k) {
                queue.pollFirst();
            }
            if (!queue.isEmpty()) {
                max = Math.max(max, y + x + queue.peekFirst()[0]);
            }

            while (!queue.isEmpty() && queue.peekLast()[0] <= y - x) {
                queue.pollLast();
            }
            queue.addLast(new int[]{y - x, x});
        }
        return max;
    }
}
