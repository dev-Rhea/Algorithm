package DP.BOJ_11049;
import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] row = new int[N];
        int[] col = new int[N];

        for(int i=0;i<N;i++) {
            String[] s = br.readLine().split(" ");
            row[i] = Integer.parseInt(s[0]);
            col[i] = Integer.parseInt(s[1]);
        }

        long[][] dp = new long[N][N];

        for(int len=2;len<=N;len++) {
            for(int i=0;i<=N-len;i++) {
                int j = i + len - 1;
                dp[i][j] = Long.MAX_VALUE;
                for(int k=i;k<j;k++) {
                    long cost = dp[i][k] + dp[k+1][j] + (long) (row[i] * col[k] * col[j]);

                    if(cost<dp[i][j]) dp[i][j] = cost;
                }
            }
        }

        System.out.println(dp[0][N-1]);
    }
}