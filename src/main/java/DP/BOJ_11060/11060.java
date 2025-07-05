package DP.BOJ_11060;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];
        long[] dp = new long[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            dp[i] = Integer.MAX_VALUE;
        }

        dp[0] = 0;
        int cnt = 0;

        for(int i=0;i<N;i++) {
            if(dp[i] >= Integer.MAX_VALUE) continue;

            for(int j=1;j<=nums[i];j++) {
                if(i+j < N) dp[i+j] = Math.min(dp[i+j], dp[i] + 1);
            }
        }

        System.out.println(dp[N-1] >= Integer.MAX_VALUE ? -1 : dp[N-1]);
    }
}