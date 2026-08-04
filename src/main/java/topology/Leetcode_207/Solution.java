package topology.Leetcode_207;

import java.util.*;

class Solution {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < prerequisites.length; i++) {
            int a = prerequisites[i][0];
            int b = prerequisites[i][1];

            graph.get(b).add(a);
            indegree[a]++;
        }

        Queue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int now = queue.poll();

            for (int next : graph.get(now)) {
                if (--indegree[next] == 0) {
                    queue.add(next);
                }
            }
        }

        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] > 0) {
                return false;
            }
        }
        return true;
    }
}