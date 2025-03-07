package DP.BOJ_11399;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] P = new int[N];
        int[] dp = new int[N]; // 누적 시간 저장

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            P[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(P);

        dp[0] = P[0];
        int sum = dp[0];

        for(int i=1;i<N;i++) {
            dp[i] = dp[i-1] + P[i];
            sum += dp[i];
        }

        System.out.println(sum);
    }
}