package DP.BOJ_1398;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        int[] dp = new int[100];
        Arrays.fill(dp, Integer.MAX_VALUE / 2);
        dp[0] = 0;
        dp[1] = 1;
        dp[10] = 1;
        dp[25] = 1;

        for (int i = 1; i < 100; i++) {
            dp[i] = Math.min(dp[i], dp[i - 1] + 1);           // 1짜리 동전
            if (i >= 10) dp[i] = Math.min(dp[i], dp[i - 10] + 1); // 10짜리 동전
            if (i >= 25) dp[i] = Math.min(dp[i], dp[i - 25] + 1); // 25짜리 동전
        }

        while(T-- > 0) {
            long price = Long.parseLong(br.readLine());
            int ans = 0;

            while (price > 0) {
                int block = (int)(price % 100);
                ans += dp[block];
                price /= 100;
            }

            sb.append(ans).append("\n");
        }

        System.out.println(sb);
    }
}