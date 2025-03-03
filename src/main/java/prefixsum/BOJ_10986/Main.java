package prefixsum.BOJ_10986;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        long answer = 0;
        long[] dp = new long[N + 1];
        long[] cnt = new long[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            dp[i] = (dp[i - 1] + Integer.parseInt(st.nextToken())) % M; // 누적합
            if (dp[i] == 0) {
                answer++;
            }
            cnt[(int) dp[i]]++;
        }

        for (int i = 0; i < M; i++) {
            if (cnt[i] > 1) {
                answer += (cnt[i] * (cnt[i] - 1)) / 2; // 조합을 활용해서 경우의 수 계산
            }
        }
        System.out.println(answer);
    }
}
