package DP.BOJ_13398;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] nums = new int[n+1];
        int[][] dp = new int[n+1][2];


        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1;i<=n;i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        dp[1][0] = nums[1];
        dp[1][1] = nums[1];
        int ans = nums[1];

        for(int i=2;i<=n;i++) {
            dp[i][0] = Math.max(nums[i], dp[i-1][0] + nums[i]);
            dp[i][1] = Math.max(dp[i-2][0] + nums[i], dp[i-1][1]+ nums[i]); // i번째 수 제거 
        }

        for(int i=1;i<=n;i++) {
            ans = Math.max(ans, Math.max(dp[i][0], dp[i][1]));
        }

        System.out.println(ans);

    }
}