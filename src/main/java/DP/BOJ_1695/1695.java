package DP.BOJ_1695;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];
        int[] reverse = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            reverse[N-1-i] = nums[i];
        }

        int[][] dp = new int[N+1][N+1];
        
        for(int i=1;i<=N;i++) {
            for(int j=1;j<=N;j++) {
                if(nums[i-1] == reverse[j-1]) dp[i][j] = dp[i-1][j-1] + 1;
                else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
        System.out.println(N - dp[N][N]);
    }
}