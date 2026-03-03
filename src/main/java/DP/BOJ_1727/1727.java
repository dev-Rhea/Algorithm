package DP.BOJ_1727;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] man = new int[n];
        int[] woman = new int[m];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) man[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) woman[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(man);
        Arrays.sort(woman);

        int[] a = (n >= m) ? man : woman;
        int[] b = (n < m) ? man : woman;
        int larger = Math.max(n, m);
        int len = Math.min(n, m);

        long[][] dp = new long[larger + 1][len + 1];
        final long INF = Long.MAX_VALUE / 2;

        for (long[] row : dp) Arrays.fill(row, INF);
        dp[0][0] = 0;

        for (int i = 0; i < larger; i++) {
            for (int j = 0; j <= len; j++) {
                if (dp[i][j] == INF) continue;
                dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j]);
                if (j < len) {
                    dp[i + 1][j + 1] = Math.min(dp[i + 1][j + 1],
                            dp[i][j] + Math.abs(a[i] - b[j]));
                }
            }
        }

        System.out.println(dp[larger][len]);
    }
}