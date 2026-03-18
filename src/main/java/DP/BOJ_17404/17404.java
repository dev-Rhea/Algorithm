package DP.BOJ_17404;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] rgb = new int[N][3];
        int[][] dp = new int[N][3];

        for(int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<3;j++) {
                rgb[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        final int INF = Integer.MAX_VALUE / 2;
        int min = INF;

        for(int i=0;i<3;i++) { // 시작 색상 
            for(int j=0;j<3;j++) {
                dp[0][j] = (j == i) ? rgb[0][j] : INF;
            }

            for(int j=1;j<N;j++) {
                dp[j][0] = Math.min(dp[j-1][1], dp[j-1][2]) + rgb[j][0];
                dp[j][1] = Math.min(dp[j-1][0], dp[j-1][2]) + rgb[j][1];
                dp[j][2] = Math.min(dp[j-1][0], dp[j-1][1]) + rgb[j][2];
            }

            for(int j=0;j<3;j++) { // 마지막 집 (첫번째 집과 다른 색만 고려)
                if(j != i) min = Math.min(min, dp[N-1][j]);
            }
        }
        System.out.println(min);
    }
}