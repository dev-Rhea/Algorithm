package DP.BOJ_1943;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] coins;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i=0;i<3;i++) {
            N = Integer.parseInt(br.readLine());
            int sum = 0;
            coins = new int[N][2];

            for(int j=0;j<N;j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                coins[j][0] = Integer.parseInt(st.nextToken());
                coins[j][1] = Integer.parseInt(st.nextToken());

                sum += coins[j][0] * coins[j][1];
            }

            if(sum % 2 != 0) System.out.println(0);
            else System.out.println(calculate(sum / 2));
        }
    }
    
    static int calculate(int sum) {
        boolean[] dp = new boolean[sum + 1];

        dp[0] = true;

        for(int i=0;i<N;i++) {
            int coin = coins[i][0];
            int cnt = coins[i][1];

            for (int j = sum; j >= 0; j--) {
                if (!dp[j]) continue;

                for (int k = 1; k <= cnt; k++) {
                    int next = j + coin * k;
                    if (next > sum) break;
                    dp[next] = true;
                }
            }
        }
        return dp[sum] ? 1 : 0;
    }
}
