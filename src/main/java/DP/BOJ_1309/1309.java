package DP.BOJ_1309;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        
        int[] dp = new int[N+1];
        dp[1] = 3;
        if (N >= 2) {
            dp[2] = 7;
        }

        for(int i = 3; i <= N; i++){
            dp[i] = (dp[i-2] + dp[i-1] * 2) % 9901;
        }

        System.out.println(dp[N]);
    }
}