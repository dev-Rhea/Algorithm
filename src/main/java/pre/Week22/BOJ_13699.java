package pre.Week22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_13699 {
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        long[] dp = new long[n + 2];

        dp[0] = 1;
        dp[1] = 1;

        for(int i=2;i<=n;i++) {
            for(int j=0;j<i;j++) {
                dp[i] += dp[j] * dp[i-j-1];
            }
        }

        System.out.println(dp[n]);
    }
}
