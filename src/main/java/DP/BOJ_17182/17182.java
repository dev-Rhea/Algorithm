package DP.BOJ_17182;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] T = new int[N][N];
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                T[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int k=0;k<N;k++) {
            for(int i=0;i<N;i++) {
                for(int j=0;j<N;j++) {
                    T[i][j] = Math.min(T[i][j], T[i][k] + T[k][j]);
                }
            }
        }

        int[][] dp = new int[1<<N][N]; // [방문한 행성 집합][현재 위치]
        for(int[] row : dp) Arrays.fill(row, Integer.MAX_VALUE);
        dp[1<<K][K] = 0;

        for(int visited=0;visited<(1<<N);visited++) {
            for(int now=0;now<N;now++) {
                if(dp[visited][now] == Integer.MAX_VALUE) continue;
                if((visited & (1<<now)) == 0) continue;
                
                for(int next=0;next<N;next++) {
                    if((visited & (1<<next)) != 0) continue;

                    int nv = visited | (1<<next);
                    dp[nv][next] = Math.min(dp[nv][next], dp[visited][now] + T[now][next]);
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        int full = (1<<N)-1;
        for(int i=0;i<N;i++) {
            ans = Math.min(ans, dp[full][i]);
        }

        System.out.println(ans);
    }
}