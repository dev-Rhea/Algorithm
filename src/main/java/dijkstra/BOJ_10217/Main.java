package dijkstra.BOJ_10217;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class Main {
    static final int INF = Integer.MAX_VALUE;

    static class Edge {
        int to, cost, time;
        Edge(int to, int cost, int time) {
            this.to = to;
            this.cost = cost;
            this.time = time;
        }
    }

    static int N, M, K;
    static List<Edge>[] graph;
    static int[][] dp; // dp[city][cost] = 최소 시간

    public static void main(String[] args) throws IOException {
        StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        StringBuilder sb = new StringBuilder();

        in.nextToken(); int T = (int) in.nval;

        for (int test_case = 1; test_case <= T; test_case++) {
            in.nextToken(); N = (int) in.nval;
            in.nextToken(); M = (int) in.nval;
            in.nextToken(); K = (int) in.nval;

            graph = new ArrayList[N + 1];
            for (int i = 1; i <= N; i++) {
                graph[i] = new ArrayList<>();
            }

            dp = new int[N + 1][M + 1];
            for (int[] row : dp) {
                Arrays.fill(row, INF);
            }

            for (int i = 0; i < K; i++) {
                in.nextToken(); int u = (int) in.nval;
                in.nextToken(); int v = (int) in.nval;
                in.nextToken(); int c = (int) in.nval;
                in.nextToken(); int d = (int) in.nval;
                graph[u].add(new Edge(v, c, d));
            }

            for (int i = 1; i <= N; i++) {
                graph[i].sort((a, b) -> a.cost - b.cost);
            }

            sb.append(bfs()).append("\n");
        }
        System.out.print(sb);
    }

    private static String bfs() {
        Deque<int[]> deque = new ArrayDeque<>();
        dp[1][0] = 0;
        deque.addLast(new int[]{1, 0, 0}); // city, cost, time

        int ans = INF;

        while (!deque.isEmpty()) {
            int[] cur = deque.pollFirst();
            int curCity = cur[0];
            int curCost = cur[1];
            int curTime = cur[2];

            if (dp[curCity][curCost] < curTime) continue;

            for (Edge next : graph[curCity]) {
                int cost = curCost + next.cost;
                int time = curTime + next.time;

                if (cost > M) break;

                if (dp[next.to][cost] <= time) continue;

                dp[next.to][cost] = time;

                if (next.to == N) {
                    ans = Math.min(ans, time);
                    continue; 
                }

                deque.addLast(new int[]{next.to, cost, time});
            }
        }

        return ans == INF ? "Poor KCM" : String.valueOf(ans);
    }
}