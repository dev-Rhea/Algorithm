package DP.BOJ_2133;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {

    static int n;
    static int[] cost;
    static int[][] dp;
    static List<List<Integer>> tree;
    static boolean[] selected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        cost = new int[n+1];
        tree = new ArrayList<>();
        dp = new int[n+1][2];
        selected = new boolean[n+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1;i<=n;i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0;i<=n;i++) {
            tree.add(new ArrayList<>());
        }

        String line;
        while ((line = br.readLine()) != null && !line.isBlank()) {
            st = new StringTokenizer(line);
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree.get(u).add(v);
            tree.get(v).add(u);
        }

        dfs(1, 0);

        int max = Math.max(dp[1][0], dp[1][1]);
        System.out.println(max);

        List<Integer> ans = new ArrayList<>();
        trace(1, 0, dp[1][1] >= dp[1][0]);
        for(int i=1;i<=n;i++) {
            if(selected[i]) ans.add(i);
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<ans.size();i++) {
            if(i > 0) sb.append(' ');
            sb.append(ans.get(i));
        }

        System.out.println(sb);
    }

    static void dfs(int v, int parent) {
        dp[v][1] = cost[v];
        dp[v][0] = 0;

        for(int next : tree.get(v)) {
            if(next == parent) continue;

            dfs(next, v);
            dp[v][1] += dp[next][0];
            dp[v][0] += Math.max(dp[next][0], dp[next][1]);
        }
    }

    static void trace(int v, int parent, boolean select) {
        selected[v] = select;

        for(int next : tree.get(v)) {
            if(next == parent) continue;
            if(select) {
                trace(next, v, false);
            }
            else trace(next, v, dp[next][1] >= dp[next][0]);
        }
    }
}