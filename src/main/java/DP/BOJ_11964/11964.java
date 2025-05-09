package DP.BOJ_11964;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        boolean[][] dp = new boolean[T + 1][2]; //[포만도][물마심 여부]
        dp[0][0] = true;

        for (int j = 0; j <= 1; j++) {
            for (int i = 0; i <= T; i++) {
                if (!dp[i][j]) continue;

                if (i + A <= T) dp[i + A][j] = true;

                if (i + B <= T) dp[i + B][j] = true;

                if (j == 0) dp[i / 2][1] = true;
            }
        }

        int ans = 0;
        for(int i=0;i<=T;i++) {
            if(dp[i][0] || dp[i][1]) ans = i;
        }
        
        System.out.println(ans);
    }
}