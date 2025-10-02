package DP.BOJ_2616;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int[] sum = new int[N+1];
        int[][] dp = new int[4][N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++) {
            sum[i] = sum[i-1] + Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());

        for(int i=1;i<4;i++) {
            for(int j=i*m;j<=N;j++) {
                dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j-m] + sum[j] - sum[j-m]);
            }
        }

        System.out.println(dp[3][N]);
    }
}