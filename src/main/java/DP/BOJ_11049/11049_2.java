package DP.BOJ_11049;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] r = new int[N];
        int[] c = new int[N];
        
        for(int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            r[i] = Integer.parseInt(st.nextToken());
            c[i] = Integer.parseInt(st.nextToken());
        }

        long[][] dp = new long[N][N];
        for(int len = 2;len<=N;len++) { // 구간
            for(int i=0;i<=N-len;i++) { // 구간 사이 행렬 
                int j = i+len-1;
                dp[i][j] = Long.MAX_VALUE;

                for(int k=i;k<j;k++) { // 구간 별 곱 수 
                    long cost = dp[i][k] + dp[k+1][j] + (long) (r[i] * c[k] * c[j]);
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }

        System.out.println(dp[0][N-1]);
    }
}