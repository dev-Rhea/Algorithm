package pre.Week18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11052 {
    static int N;
    static int[] prices;
    static int[] dp;
    static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        prices = new int[N+1];
        dp = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++) {
            prices[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=1;i<=N;i++) { {
            for(int j=1;j<=N;j++) {
                if(i>=j) {
                    dp[i] = Math.max(dp[i], dp[i-j]+prices[j]);
                    }
                max = Math.max(max, dp[i]);
                }
            }
        }
        System.out.println(max);
    }
}
