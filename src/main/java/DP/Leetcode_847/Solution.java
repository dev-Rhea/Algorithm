package DP.Leetcode_847;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Solution {

    public int shortestPathLength(int[][] graph) {
        int n = graph.length;
        int full = (1 << n) - 1;

        int[][] dp = new int[1 << n][n];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        Queue<int[]> queue = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            dp[1 << i][i] = 0;
            queue.add(new int[]{1 << i, i});
        }

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int visited = now[0];
            int dist = dp[visited][now[1]];

            if (visited == full) {
                continue;
            }

            for (int next : graph[now[1]]) {
                int nv = visited | (1 << next);
                if (dp[nv][next] > dist + 1) {
                    dp[nv][next] = dist + 1;
                    queue.add(new int[]{nv, next});
                }
            }
        }

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            ans = Math.min(ans, dp[full][i]);
        }

        return ans;
    }

}
