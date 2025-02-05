package pre.Week22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1756 {
    static int N, D;
    static int[] oven;
    static int[] pizza;
    static int[][] dp;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        D = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        oven = new int[D];
        pizza = new int[N];

        // 오븐 지름 입력
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < D; i++) {
            oven[i] = Integer.parseInt(st.nextToken());
            if (i > 0 && oven[i - 1] <= oven[i]) {
                oven[i] = oven[i - 1];
            }
        }

        // 피자 지름 입력
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            pizza[i] = Integer.parseInt(st.nextToken());
        }

        ans = 0;
        int cnt = 0;

        for (int i = D - 1; i >= 0; i--) {
            if (oven[i] >= pizza[cnt]) {
                cnt++;
            }

            if (cnt == N) {
                ans = i + 1;
                break;
            }
        }
        System.out.println(ans);
    }
}