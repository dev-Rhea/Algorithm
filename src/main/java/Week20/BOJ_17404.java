package Week20;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17404 {
    static int N;
    static int[][] cost;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        cost = new int[N][3];

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<3;j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = Integer.MAX_VALUE;

        for(int i=0;i<3;i++) {
            dp = new int[N][3];

            for(int j=0;j<3;j++) {
                if(i == j) dp[0][j] = cost[0][j];
                else dp[0][j] = cost[0][j] + 1000*1000;
            }

            for(int j=1;j<N;j++) {
                dp[j][0] = Math.min(dp[j-1][1], dp[j-1][2]) + cost[j][0];
                dp[j][1] = Math.min(dp[j-1][0], dp[j-1][2]) + cost[j][1];
                dp[j][2] = Math.min(dp[j-1][0], dp[j-1][1]) + cost[j][2];
            }

            for(int j=0;j<3;j++) {
                if(i == j) continue;
                ans = Math.min(ans, dp[N-1][j]);
            }
        }

        System.out.println(ans);
    }
}
