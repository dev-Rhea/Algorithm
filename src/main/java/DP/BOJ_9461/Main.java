package DP.BOJ_9461;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);

        int T = sc.nextInt();
        long[] dp = new long[101];
        dp[1] = 1;
        dp[2] = 1;

        for(int i=3; i<=100; i++) {
            dp[i] = dp[i-2] + dp[i-3];
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<T;i++) {
            sb.append(dp[sc.nextInt()]).append("\n");
        }
        System.out.println(sb);
    }
}
