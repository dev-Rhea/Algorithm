package DP.BOJ_1234;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int g = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        long[][][][] dp = new long[N+1][r+1][g+1][b+1];

        for(int i=0;i<=N;i++) {
            for(int j=0;j<=r;j++) {
                for(int k=0;k<=g;k++) {
                    for(int l=0;l<=b;l++) {
                        if(i == 0) {
                            dp[i][j][k][l] = 1;
                            continue;
                        }

                        // 단일 색상 
                        if(j-i>=0) dp[i][j][k][l] += dp[i-1][j-i][k][l];
                        if(k-i>=0) dp[i][j][k][l] += dp[i-1][j][k-i][l];
                        if(l-i>=0) dp[i][j][k][l] += dp[i-1][j][k][l-i];

                        // 두가지 색 조합 
                        if(i % 2 == 0) {
                            int div = i/2;

                            if(k-div>=0 && l-div>=0) dp[i][j][k][l] += dp[i-1][j][k-div][l-div] * comb(i, div);
                            if(j-div>=0 && l-div>=0) dp[i][j][k][l] += dp[i-1][j-div][k][l-div] * comb(i, div);
                            if(j-div>=0 && k-div>=0) dp[i][j][k][l] += dp[i-1][j-div][k-div][l] * comb(i, div);
                        }
                        // 세가지 색 모두 사용 
                        if(i % 3 == 0) {
                            int div = i/3;
                            if(j-div>=0 && k-div>=0 && l-div>=0) dp[i][j][k][l] += dp[i-1][j-div][k-div][l-div]  * comb(i, div) * comb(i-div, div);
                        }
                    } 
                }
            }
        }

        System.out.println(dp[N][r][g][b]);
    }

    static long comb(int n, int r) {
        return factorial(n) / (factorial(r) * factorial(n-r));
    }
    static long factorial(int a) {
        if(a == 1) return 1;
        return a * factorial(a-1);
    }
}