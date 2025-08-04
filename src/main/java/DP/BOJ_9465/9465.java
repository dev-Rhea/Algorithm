package DP.BOJ_9465;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int[][] sticker = new int[2][n];

            for(int i=0;i<2;i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j=0;j<n;j++) {
                    sticker[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int[][] dp = new int[2][n];
            dp[0][0] = sticker[0][0];
            dp[1][0] = sticker[1][0];

            for(int j = 1; j < n; j++) {
                dp[0][j] = Math.max(dp[1][j-1] + sticker[0][j], dp[0][j-1]);
                dp[1][j] = Math.max(dp[0][j-1] + sticker[1][j], dp[1][j-1]);
            }

            System.out.println(Math.max(dp[0][n-1], dp[1][n-1]));
        }
    }
}