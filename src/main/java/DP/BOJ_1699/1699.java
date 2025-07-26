package DP.BOJ_1699;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N+1];
        int[] dp = new int[100001];

        for(int i=1;i<=N;i++) {
            nums[i] = i*i;
        }

        for(int i=1;i<=N;i++) {
            dp[i] = i;
            for(int j=1;j*j<=i;j++) {
                if(dp[i] > dp[i - j*j] + 1) dp[i] = dp[i - j*j] + 1;
            }
        }
        System.out.println(dp[N]);
    }
}