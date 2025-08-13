package DP.BOJ_2133;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] dp  = new int[N+1];

        System.out.println(N % 2 == 0 ? cal(N, dp) : 0);
    }

    static int cal(int N, int[] dp) {
        dp[0] = 1;
        dp[1] = 0;
        dp[2] = 3;

        for(int i=4;i<=N;i+=2) {
            dp[i] = dp[i-2] * dp[2];
            for(int j=i-4;j>=0;j-=2) {
                dp[i] += dp[j] * 2;
            }
        }
        return dp[N];
    }
}