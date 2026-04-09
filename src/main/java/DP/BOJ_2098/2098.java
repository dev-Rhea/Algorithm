package DP.BOJ_2098;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

    static int N;
    static int[][] W, dp;
    static final int INF = Integer.MAX_VALUE/2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        W = new int[N][N];

        for(int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[N][(1<<N)];
        for (int[] row : dp) Arrays.fill(row, -1);

        System.out.print(travel(0, 1));
    }

    static int travel(int now, int visited) {
        if(visited == (1<<N) - 1) {
            return W[now][0] == 0 ? INF : W[now][0];
        }

        if(dp[now][visited] != -1) return dp[now][visited];
        dp[now][visited] = INF;

        for(int i=0;i<N;i++) {
            if((visited & (1 << i)) != 0) continue;
            if(W[now][i] == 0) continue;

            int next = visited | (1<<i);
            int cost = travel(i, next);
            if(cost < INF) dp[now][visited] = Math.min(dp[now][visited], W[now][i] + cost);
        }

        return dp[now][visited];
    }
}