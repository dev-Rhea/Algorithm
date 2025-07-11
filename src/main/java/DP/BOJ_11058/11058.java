package DP.BOJ_11058;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        long[] dp = new long[N+1];

        for(int i=1;i<=N;i++) {
            dp[i] = dp[i-1] + 1;
            if(i > 6) {
                for(int j=3;j<=5;j++) {
                    dp[i] = Math.max(dp[i], dp[i-j] * dp[j-1]);
                }
            }
        }

        System.out.println(dp[N]);
    }
}