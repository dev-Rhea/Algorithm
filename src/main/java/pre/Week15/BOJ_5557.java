package pre.Week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_5557 {
    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());  // 첫 번째 줄에서 N을 읽음

        arr = new int[N-1];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        for (int i = 0; i < N - 1; i++) {
            arr[i] = Integer.parseInt(st.nextToken());  // arr 배열 채움
        }

        int lastN = Integer.parseInt(st.nextToken());  // 마지막 숫자 읽음

        long ans = 0;

        long[][] dp = new long[N-1][21];
        dp[0][arr[0]] = 1;

        for (int i = 0; i < N - 2; i++) {
            for (int j = 0; j <= 20; j++) {
                if (dp[i][j] != 0) {
                    int plus = j + arr[i + 1];
                    int minus = j - arr[i + 1];
                    if (plus >= 0 && plus <= 20) {
                        dp[i + 1][plus] += dp[i][j];
                    }
                    if (minus >= 0 && minus <= 20) {
                        dp[i + 1][minus] += dp[i][j];
                    }
                }
            }
        }

        ans = dp[N - 2][lastN];  // 마지막 단계에서 찾는 수를 만들 수 있는 경우의 수
        System.out.println(ans);
    }
}
