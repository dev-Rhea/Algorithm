package DP.BOJ_1932;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()) + 1; // 1부터 시작
        dp = new int[n][n];

        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < i + 1; j++) { // i번째 줄에는 i개의 숫자
                dp[i][j] =
                    Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + Integer.parseInt(
                        st.nextToken()); // 대각선 좌, 우 중 더 큰 값 선택
            }
        }

        int max = 0;

        for (int i = 1; i < n; i++) {
            max = Math.max(max, dp[n - 1][i]);
        }
        System.out.println(max);
    }

}
