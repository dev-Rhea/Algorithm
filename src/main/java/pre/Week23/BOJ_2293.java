package pre.Week23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2293 {
    static int n, k;
    static int[] coin;
    static int[] dp;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        coin = new int[n + 1];

        for(int i=1;i<=n;i++) {
            coin[i] = Integer.parseInt(br.readLine().trim());
        }

        dp = new int[k+1];

        dp[0] = 1;

        for(int i=1;i<=n;i++) {
            for(int j=coin[i];j<=k;j++) {
                if(j >= coin[i]) {
                    dp[j] += dp[j-coin[i]];
                }
            }

        }
        System.out.println(dp[k]);
    }
}
