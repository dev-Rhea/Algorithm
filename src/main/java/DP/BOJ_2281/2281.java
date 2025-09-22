package DP.BOJ_2281;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        int[] len = new int[n];
        int[] dp = new int[n];

        for(int i=0;i<n;i++) {
            len[i] = Integer.parseInt(br.readLine());
        }

        dp[n-1] = 0;
        for(int i=n-2;i>=0;i--) {
            int l = len[i];
            int min = (m - l) * (m - l) + dp[i+1];

            for(int j=i+1;j<n;j++) {
                l += len[j] + 1;
                if(l > m) break;
                if(j == n-1) min = 0;
                else min = Math.min(min, (m - l) * (m - l) + dp[j+1]);
            }
            dp[i] = min;
        }

        System.out.println(dp[0]);
    }
}