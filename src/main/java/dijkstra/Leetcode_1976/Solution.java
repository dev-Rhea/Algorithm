package dijkstra.Leetcode_1976;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {

    public class Road {

        long road, time;

        Road(long road, long time) {
            this.road = road;
            this.time = time;
        }
    }

    public int countPaths(int n, int[][] roads) {
        final int MOD = 1_000_000_007;
        List<List<Road>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] r : roads) {
            int u = r[0];
            int v = r[1];
            int t = r[2];

            graph.get(u).add(new Road(v, t));
            graph.get(v).add(new Road(u, t));
        }

        long[] dist = new long[n];
        long[] ways = new long[n];

        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;
        ways[0] = 1;

        Queue<Road> queue = new PriorityQueue<>(Comparator.comparingLong(o -> o.time));
        queue.add(new Road(0, 0));

        while (!queue.isEmpty()) {
            Road now = queue.poll();
            int u = (int) now.road;

            if (now.time > dist[u]) {
                continue;
            }

            for (Road next : graph.get(u)) {
                int v = (int) next.road;
                if (dist[v] > dist[u] + next.time) {
                    dist[v] = dist[u] + next.time;
                    ways[v] = ways[u];
                    queue.add(new Road(v, dist[v]));
                } else if (dist[u] + next.time == dist[v]) {
                    ways[v] = (ways[v] + ways[u]) % MOD;
                }
            }
        }

        return (int) ways[n - 1];
    }
}
