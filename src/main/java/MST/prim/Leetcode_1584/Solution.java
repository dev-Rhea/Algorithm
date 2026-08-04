package MST.prim.Leetcode_1584;

import java.util.Arrays;

public class Solution {

    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        if (n <= 1) {
            return 0;
        }

        int[] dist = new int[n];
        boolean[] used = new boolean[n];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;

        int ans = 0;

        for (int t = 0; t < n; t++) {
            int now = -1;

            for (int i = 0; i < n; i++) {
                if (!used[i] && (now == -1 || dist[i] < dist[now])) {
                    now = i;
                }
            }

            used[now] = true;
            ans += dist[now];

            for (int i = 0; i < n; i++) {
                if (used[i]) {
                    continue;
                }
                int d = cal(points[now][0], points[now][1], points[i][0], points[i][1]);
                if (d < dist[i]) {
                    dist[i] = d;
                }
            }
        }

        return ans;
    }

    private int cal(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}
