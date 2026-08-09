package graph.Leetcode_1466;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Solution {

    public int minReorder(int n, int[][] connections) {
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] c : connections) {
            graph.get(c[0]).add(new int[]{c[1], 1});
            graph.get(c[1]).add(new int[]{c[0], 0});
        }

        boolean[] visited = new boolean[n];
        Deque<Integer> deque = new ArrayDeque<>();
        visited[0] = true;
        deque.add(0);

        int ans = 0;
        while (!deque.isEmpty()) {
            int now = deque.poll();

            for (int[] e : graph.get(now)) {
                if (visited[e[0]]) {
                    continue;
                }
                visited[e[0]] = true;
                ans += e[1];
                deque.add(e[0]);
            }
        }

        return ans;
    }
}
