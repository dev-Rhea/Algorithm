package DP.BOJ_11054;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {

    public static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int[] dp = new int[N];
        int[] dp2 = new int[N];
        int[] nums = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            dp[i] = 1;
            dp2[i] = 1;
            nums[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
                if (nums[N - 1 - i] > nums[N - 1 - j]) {
                    dp2[N - 1 - i] = Math.max(dp2[N - 1 - i], dp2[N - 1 - j] + 1);
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < N; i++) {
            ans = Math.max(ans, dp[i] + dp2[i] - 1);
        }

        System.out.println(ans);
    }
}