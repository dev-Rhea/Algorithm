package datastructure.queue.Leetcode_1882;

import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {

    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;

        Queue<int[]> free = new PriorityQueue<>((o1, o2) -> {
            if (o1[1] == o2[1]) {
                return o1[0] - o2[0];
            }
            return o1[1] - o2[1];
        });

        Queue<long[]> busy = new PriorityQueue<>((o1, o2) -> Long.compare(o1[0], o2[0]));

        for (int i = 0; i < servers.length; i++) {
            free.add(new int[]{i, servers[i]});
        }

        int[] ans = new int[m];
        long t = 0;

        for (int i = 0; i < m; i++) {
            t = Math.max(t, i);

            while (!busy.isEmpty() && busy.peek()[0] <= t) {
                long[] done = busy.poll();
                free.add(new int[]{(int) done[1], (int) done[2]});
            }

            if (free.isEmpty()) {
                t = busy.peek()[0];

                while (!busy.isEmpty() && busy.peek()[0] <= t) {
                    long[] done = busy.poll();
                    free.add(new int[]{(int) done[1], (int) done[2]});
                }
            }

            int[] s = free.poll();
            ans[i] = s[0];

            busy.add(new long[]{t + tasks[i], s[0], s[1]});
        }

        return ans;
    }
}
