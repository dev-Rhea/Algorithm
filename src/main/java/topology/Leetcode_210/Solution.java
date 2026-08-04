import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> tree = new ArrayList<>();
        int[] indegree = new int[numCourses];
        ArrayList<Integer> order = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < prerequisites.length; i++) {
            int a = prerequisites[i][0];
            int b = prerequisites[i][1];

            tree.get(b).add(a);
            indegree[a]++;
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
                order.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int now = queue.poll();

            for (int next : tree.get(now)) {
                if (--indegree[next] == 0) {
                    queue.add(next);
                    order.add(next);
                }
            }
        }

        if (order.size() < numCourses) {
            return new int[]{};
        }
        return order.stream().mapToInt(Integer::intValue).toArray();
    }
}