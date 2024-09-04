package Week19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2156 {
    static int n;
    static int[] wine;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        wine = new int[n+1];

        // 포도주 양 입력
        for(int i=1;i<=n;i++) {
            st = new StringTokenizer(br.readLine());
            wine[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[n+1];

        // dp[1] = wine[1] (첫번째 포도주 잔을 마실 경우)
        dp[1] = wine[1];

        // 첫번째, 두번째 포도주 잔을 마실 경우
        if(n>1) dp[2] = wine[1] + wine[2];

        // 첫번째, 두번째, 세번째 포도주 잔을 마실 경우
        if(n>2) dp[3] = Math.max(wine[1] + wine[3], Math.max(wine[2] + wine[3], dp[2]));

        // i번째 포도주 잔을 마실 경우
        for(int i=4;i<=n;i++) {
            dp[i] = Math.max(dp[i-3] + wine[i-1] + wine[i], Math.max(dp[i-2] + wine[i], dp[i-1]));
        }

        System.out.println(dp[n]);

    }
}
