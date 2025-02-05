package pre.Week28;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1010 {

    static int T, N, M;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        dp = new int[30][30];

        T = Integer.parseInt(st.nextToken());

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            System.out.println(factorial(M, N));
        }
    }

    static int factorial(int n, int r) {
        if(dp[n][r] > 0) {
            return dp[n][r];
        }
        if(n == r || r == 0) {
            return dp[n][r] = 1;
        }

        return dp[n][r] = factorial(n - 1, r - 1) + factorial(n - 1, r);
    }

}
