package pre.Week24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_9655 {
    static int N;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        dp = new int[1001];

        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 1;

        for(int i=4; i<=N; i++) {
            dp[i] = Math.min(dp[i-1], dp[i-3]) + 1;
        }

        if(dp[N] % 2 == 0) {
            System.out.println("CY");
        }
        else {
            System.out.println("SK");
        }
    }
}
