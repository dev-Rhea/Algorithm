package datastructure.queue.Leetcode_1642;

import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {

    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        Queue<Integer> queue = new PriorityQueue<>();

        for (int i = 0; i < heights.length - 1; i++) {
            int diff = heights[i + 1] - heights[i];

            if (diff <= 0) {
                continue;
            }

            queue.add(diff);
            if (queue.size() > ladders) {
                bricks -= queue.poll();

                if (bricks < 0) {
                    return i;
                }
            }
        }

        return heights.length - 1;
    }
}
