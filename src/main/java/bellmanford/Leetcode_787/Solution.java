package bellmanford.Leetcode_787;

import java.util.Arrays;

public class Solution {

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[src] = 0;

        for (int i = 0; i <= k; i++) {
            int[] temp = Arrays.copyOf(dist, n);

            for (int[] f : flights) {
                if (dist[f[0]] == Integer.MAX_VALUE) {
                    continue;
                }
                temp[f[1]] = Math.min(temp[f[1]], dist[f[0]] + f[2]);
            }
            dist = temp;
        }

        return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
    }
}
