package pre.Week17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1577 {
    static int N, M, K;
    static long[][] dp;
    static boolean[][] vertical, horizontal;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());

        dp = new long[N + 1][M + 1];
        vertical = new boolean[N + 1][M + 1];
        horizontal = new boolean[N + 1][M + 1];

        // 공사중인 도로 정보 입력
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            // 세로, 가로 도로 정보 저장
            if (a == c) {
                vertical[a][Math.min(b, d)] = true;
            } else {
                horizontal[Math.min(a, c)][b] = true;
            }
        }

        // 출발 지점
        dp[0][0] = 1;

        // 도로 정보를 통해 dp 배열 채우기
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= M; j++) {
                // 세로 도로가 없다면
                if (i > 0 && !horizontal[i - 1][j]) {
                    dp[i][j] += dp[i - 1][j];
                }
                // 가로 도로가 없다면
                if (j > 0 && !vertical[i][j - 1]) {
                    dp[i][j] += dp[i][j - 1];
                }
            }
        }

        System.out.println(dp[N][M]);
    }
}