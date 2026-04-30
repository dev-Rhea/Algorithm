package dijkstra.Leetcode_743;

//You are given a network of n nodes, labeled from 1 to n. You are also given
//times, a list of travel times as directed edges times[i] = (ui, vi, wi), where ui
//is the source node, vi is the target node, and wi is the time it takes for a
//signal to travel from source to target.
//
// We will send a signal from a given node k. Return the minimum time it takes
//for all the n nodes to receive the signal. If it is impossible for all the n
//nodes to receive the signal, return -1.
//
//
// Example 1:
//
//
//Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
//Output: 2
//
//
// Example 2:
//
//
//Input: times = [[1,2,1]], n = 2, k = 1
//Output: 1
//
//
// Example 3:
//
//
//Input: times = [[1,2,1]], n = 2, k = 2
//Output: -1
//
//
//
// Constraints:
//
//
// 1 <= k <= n <= 100
// 1 <= times.length <= 6000
// times[i].length == 3
// 1 <= ui, vi <= n
// ui != vi
// 0 <= wi <= 100
// All the pairs (ui, vi) are unique. (i.e., no multiple edges.)
//
//
// Related Topics Depth-First Search Breadth-First Search Graph Theory Heap (
//Priority Queue) Shortest Path 👍 8417 👎 392


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    static class Node {

        int node, time;

        Node(int node, int time) {
            this.node = node;
            this.time = time;
        }
    }

    static int N;
    static List<List<Node>> graph;

    public int networkDelayTime(int[][] times, int n, int k) {
        N = n;

        graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] t : times) {
            graph.get(t[0]).add(new Node(t[1], t[2]));
        }

        return dijkstra(k);
    }

    private int dijkstra(int start) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Queue<Node> queue = new PriorityQueue<>((o1, o2) -> (o1.time - o2.time));

        queue.add(new Node(start, 0));
        dist[start] = 0;

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            if (now.time > dist[now.node]) {
                continue;
            }

            for (Node next : graph.get(now.node)) {
                if (dist[next.node] > dist[now.node] + next.time) {
                    dist[next.node] = dist[now.node] + next.time;
                    queue.add(new Node(next.node, dist[next.node]));
                }
            }
        }

        int ans = 0;
        for (int i = 1; i <= N; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                return -1;
            }
            ans = Math.max(ans, dist[i]);
        }

        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

