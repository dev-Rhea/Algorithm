package Week22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1106 {
    static int C, N;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 최소 확보해야 하는 고객 수와 도시 개수 입력
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        // dp 배열 선언, C + 101 크기로 선언해서 충분한 크기로 만듬
        dp = new int[C + 101];

        // dp 배열을 큰 값으로 초기화 (무한대처럼 사용)
        for (int i = 0; i <= C + 100; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        dp[0] = 0; // 고객 0명을 얻기 위한 비용은 0원

        // 각 도시의 홍보 비용과 증가하는 고객 수
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken()); // 홍보 비용
            int customer = Integer.parseInt(st.nextToken()); // 그로 인해 얻는 고객 수

            // 배낭 문제와 유사한 방식으로 dp 배열 갱신
            for (int j = customer; j <= C + 100; j++) {
                if (dp[j - customer] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j], dp[j - customer] + cost);
                }
            }
        }

        // 적어도 C명의 고객을 확보하는 최소 비용 찾기
        int answer = Integer.MAX_VALUE;
        for (int i = C; i <= C + 100; i++) {
            answer = Math.min(answer, dp[i]);
        }

        System.out.println(answer);
    }
}
