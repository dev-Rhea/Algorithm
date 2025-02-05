package pre.Week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ10844 {
    static int N;
    static long mod = 1000000000;

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        long[][] dp = new long[N+1][10];

        // N이 1인 경우의 수 1개
        for(int i=1;i<10;i++){
            dp[1][i] = 1;
        }

        // N >= 2인 경우의 수
        for(int i=2;i<=N;i++){
            // 0-9 탐색
            for(int j=0;j<10;j++){
                if(j == 9){
                    dp[i][j] = dp[i-1][8] % mod;
                }
                else if(j == 0){
                    dp[i][j] = dp[i-1][1] % mod;
                }
                else{
                    dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1]) % mod;
                }
            }
        }
        System.out.println(Arrays.stream(dp[N]).sum() % mod);

    }
}
