package bfs.Leetcode_815;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Solution {

    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }

        int n = routes.length;
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int i = 0; i < n; i++) {
            for (int stop : routes[i]) {
                graph.computeIfAbsent(stop, k -> new ArrayList<>()).add(i);
            }
        }

        if (!graph.containsKey(source) || !graph.containsKey(target)) {
            return -1;
        }

        boolean[] visited = new boolean[n];
        Set<Integer> bus = new HashSet<>();
        Queue<Integer> queue = new ArrayDeque<>();

        for (int b : graph.get(source)) {
            visited[b] = true;
            queue.add(b);
        }
        bus.add(source);

        int cnt = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int now = queue.poll();

                for (int next : routes[now]) {
                    if (next == target) {
                        return cnt;
                    }

                    if (!bus.add(next)) {
                        continue;
                    }

                    for (int nn : graph.get(next)) {
                        if (visited[nn]) {
                            continue;
                        }
                        visited[nn] = true;
                        queue.add(nn);
                    }
                }
            }
            cnt++;
        }
        return -1;
    }
}
