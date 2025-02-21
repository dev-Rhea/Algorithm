package DP.BOJ_2213;

import com.sun.jdi.connect.Connector.StringArgument;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] w;
    static int[][] dp;
    static boolean[] visited;
    static List<Integer>[] tree;
    static List<Integer> ans = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        w = new int[n+1];
        dp = new int[n+1][2];
        visited = new boolean[n+1];
        tree = new ArrayList[n+1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            w[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0;i<=n;i++) {
            tree[i] = new ArrayList<>();
        }

        for(int i=0;i<n - 1;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            tree[a].add(b);
            tree[b].add(a);
        }

        dfs(1);
        sb.append(Math.max(dp[1][0], dp[1][1])).append("\n");
        Arrays.fill(visited, false);

        if(dp[1][0] > dp[1][1]) {
            find(1, 0);
        }
        else  {
            find(1, 1);
        }
        Collections.sort(ans);
        for(int n : ans) {
            sb.append(n).append(" ");
        }
        System.out.println(sb);
    }

    static void dfs(int node) {
        visited[node] = true;

        dp[node][1] = w[node];

        for(int next : tree[node]) {
            if(visited[next]) continue;

            dfs(next);

            if(dp[next][0] > dp[next][1]) dp[node][0] += dp[next][0];
            else dp[node][0] += dp[next][1];

            dp[node][1] += dp[next][0];
        }
    }

    static void find(int node, int inc) {
        visited[node] = true;

        if(inc == 1) ans.add(node);
        for(int next : tree[node]) {
            if(visited[next]) continue;

            if(inc == 1) find(next, 0);
            else {
                if(dp[next][0] > dp[next][1]) find(next, 0);
                else find(next, 1);
            }
        }
    }
}
