package pre.Week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2749 {
    static long n;
    static long[] dp;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Long.parseLong(br.readLine());
        long idx = n % 1500000; // 피보나치 수열 주기 마다 나눠서 구하기

        dp = new long[(int)idx + 1];

        dp[0] = 0;
        dp[1] = 1;
        // 주기에 맞춰서 반복문
        for(int i=2;i<=idx;i++) {
            dp[i] = (dp[i-1] + dp[i-2]) % 1000000;
        }
        System.out.println(dp[(int)idx]);
    }
}
