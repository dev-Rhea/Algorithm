package Week19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15990 {
    static int T;
    static long[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        int[] arr = new int[T];
        dp = new long[100001][4];

        for (int i = 0; i < T; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        // Base Cases
        dp[1][1] = 1;
        dp[2][2] = 1;
        dp[3][1] = 1;
        dp[3][2] = 1;
        dp[3][3] = 1;

        // Fill the DP array up to the maximum needed value
        for (int j = 4; j <= 100000; j++) {
            dp[j][1] = (dp[j-1][2] + dp[j-1][3]) % 1000000009;
            dp[j][2] = (dp[j-2][1] + dp[j-2][3]) % 1000000009;
            dp[j][3] = (dp[j-3][1] + dp[j-3][2]) % 1000000009;
        }

        // Output the results for each test case
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            int n = arr[i];
            long result = (dp[n][1] + dp[n][2] + dp[n][3]) % 1000000009;
            sb.append(result).append("\n");
        }
        System.out.print(sb);
    }
}
