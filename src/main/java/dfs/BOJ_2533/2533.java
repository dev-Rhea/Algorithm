package dfs.BOJ_2533;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {
    
    static ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
    static boolean[] visited;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        visited = new boolean[N + 1];
        dp = new int[N+1][2];
        
        for(int i=0;i<=N;i++) {
            tree.add(new ArrayList<>());
        }

        for(int i=0;i<N-1;i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            tree.get(u).add(v);
            tree.get(v).add(u);
        }

        dfs(1);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    static void dfs(int node) {
        visited[node] = true;
        dp[node][0] = 0; // 얼리어답터가 아닐때
        dp[node][1] = 1; // 얼리어답터 일때

        for(int n : tree.get(node)) {
            if(!visited[n]){
                dfs(n);
                dp[node][0] += dp[n][1]; // 부모가 얼리어답터가 아니면, 자식은 얼리어 답터여야 함.
                dp[node][1] += Math.min(dp[n][0], dp[n][1]); // 부모가 얼리어답터면, 자식은 상관없음.
            }
        }
    }
}