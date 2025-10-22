package DP.BOJ_18427;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        List<Integer>[] block = new List[N+1];
        int[][] dp = new int[N+1][H+1];

        for(int i=1;i<=N;i++) {
            block[i] = new ArrayList<>();
        }

        for(int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            while(st.hasMoreTokens()){
                block[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        for(int i=0;i<=N;i++) {
            dp[i][0] = 1; // 높이 0을 만드는 경우의 수 1
        }

        for(int i=1;i<=N;i++) {
            for(int j=1;j<=H;j++) {
                dp[i][j] = dp[i-1][j];

                for(int n : block[i]) {
                    if(n <= j) dp[i][j] = (dp[i][j] + dp[i-1][j-n]) % 10007;
                }
            }
        }

        System.out.println(dp[N][H]);
    }
}