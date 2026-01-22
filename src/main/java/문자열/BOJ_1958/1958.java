package 문자열.BOJ_1958;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] str1 = br.readLine().toCharArray();
        char[] str2 = br.readLine().toCharArray();
        char[] str3 = br.readLine().toCharArray();

        int n = str1.length;
        int m = str2.length;
        int l = str3.length;

        int[][][] dp = new int[n+1][m+1][l+1];

        for(int i=1;i<=n;i++) {
            for(int j=1;j<=m;j++) {
                for(int k=1;k<=l;k++) {
                    if(str1[i-1] == str2[j-1] && str1[i-1] == str3[k-1]) dp[i][j][k] = dp[i-1][j-1][k-1] + 1;
                    else dp[i][j][k] = Math.max(dp[i-1][j][k], Math.max(dp[i][j-1][k], dp[i][j][k-1]));
                }
            }
        }

        System.out.println(dp[n][m][l]);
    }
}