package DP.BOJ_2056;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N+1];
        int ans = 0;
        int max;

        for(int i=1;i<=N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int t = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            if(p == 0) dp[i] = t;
            else {
                max = 0;
                for(int j=0;j<p;j++) {
                    int pre = Integer.parseInt(st.nextToken());
                    max = Math.max(dp[pre], max);
                }
                dp[i] = max + t;
            }

            ans = Math.max(ans, dp[i]);
        }

        System.out.println(ans);
    }
}