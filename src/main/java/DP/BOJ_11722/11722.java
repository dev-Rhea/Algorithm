package DP.BOJ_11722;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] nums = new int[N];
        int[] dp = new int[N]; // 수열 길이 저장 
        int ans = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0;i<N;i++) {
            dp[i] = 1;
            for(int j=0;j<i;j++) {
                if(nums[i] < nums[j]) dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            ans = Math.max(dp[i], ans);
        }

        System.out.println(ans);
    }
}