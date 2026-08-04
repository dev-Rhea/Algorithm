import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {

    public long minimumFuelCost(int[][] roads, int seats) {
        int n = roads.length + 1;
        if (n == 1) {
            return 0;
        }

        int[] indegree = new int[n];

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] r : roads) {
            int a = r[0];
            int b = r[1];

            graph.get(a).add(b);
            graph.get(b).add(a);

            indegree[b]++;
            indegree[a]++;
        }

        long fuel = 0;
        long[] people = new long[n];
        Arrays.fill(people, 1);

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i < n; i++) {
            if (indegree[i] == 1) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int now = queue.poll();

            for (int next : graph.get(now)) {
                if (indegree[next] > 0) {
                    fuel += (people[now] + seats - 1) / seats;
                    people[next] += people[now];
                    indegree[next]--;
                    if (next != 0 && indegree[next] == 1) {
                        queue.add(next);
                    }
                    break;
                }
            }
            indegree[now] = 0;
        }

        return fuel;
    }
}