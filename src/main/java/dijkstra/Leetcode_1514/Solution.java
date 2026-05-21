package dijkstra.Leetcode_1514;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {

    public double maxProbability(int n, int[][] edges, double[] succProb, int start_node,
        int end_node) {
        List<List<double[]>> graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < edges.length; i++) {
            int a = edges[i][0];
            int b = edges[i][1];
            double p = succProb[i];

            graph.get(a).add(new double[]{b, p});
            graph.get(b).add(new double[]{a, p});
        }

        double[] dist = new double[n];
        dist[start_node] = 1.0;

        Queue<double[]> queue = new PriorityQueue<>((o1, o2) -> Double.compare(o2[1], o1[1]));
        queue.add(new double[]{start_node, 1.0});

        while (!queue.isEmpty()) {
            double[] now = queue.poll();
            int node = (int) now[0];
            double p = now[1];

            if (node == end_node) {
                return dist[node];
            }
            if (dist[node] > p) {
                continue;
            }

            for (double[] next : graph.get(node)) {
                int nn = (int) next[0];
                if (dist[nn] < p * next[1]) {
                    dist[nn] = p * next[1];
                    queue.add(new double[]{nn, dist[nn]});
                }
            }
        }
        return 0.0;
    }
}
