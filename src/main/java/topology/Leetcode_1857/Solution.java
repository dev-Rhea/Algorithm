import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {
    public int largestPathValue(String colors, int[][] edges) {
        int n = colors.length();
        List<List<Integer>> graph = new ArrayList<>();
        for(int i=0;i<n;i++) {
            graph.add(new ArrayList<>());
        }

        int[] indegree = new int[n];
        for(int i=0;i<edges.length;i++) {
            int a = edges[i][0];
            int b = edges[i][1];

            graph.get(a).add(b);
            indegree[b]++;
        }

        int[][] dp = new int[n][26];
        Queue<Integer> queue = new LinkedList<>();

        for(int i=0;i<n;i++) {
            if(indegree[i] == 0) {
                queue.add(i);
                dp[i][colors.charAt(i) - 'a'] = 1;
            }
        }

        int visited = 0;
        int ans = 0;
        while(!queue.isEmpty()) {
            int now = queue.poll();
            visited++;

            for(int i=0;i<26;i++) ans = Math.max(ans, dp[now][i]);

            for(int next : graph.get(now)) {
                for(int i=0;i<26;i++) {
                    int add = (colors.charAt(next) - 'a' == i) ? 1 : 0;
                    dp[next][i] = Math.max(dp[next][i], dp[now][i]+add);
                }
                if(--indegree[next] == 0) queue.add(next);
            }
        }

        return visited == n ? ans : -1;
}