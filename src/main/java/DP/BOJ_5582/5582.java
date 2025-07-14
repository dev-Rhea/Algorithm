package DP.BOJ_5582;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        char[] str1 = br.readLine().toCharArray();
        char[] str2 = br.readLine().toCharArray();
        int max = 0;
        int[][] dp = new int[str1.length + 2][str2.length + 2];

        for(int i=0;i<str1.length;i++) {
            for(int j=0;j<str2.length;j++) {
                if(str1[i] == str2[j]) {
                    dp[i+1][j+1] = dp[i][j] + 1;
                    max = Math.max(max, dp[i+1][j+1]);
                }
            } 
        }

        System.out.println(max);
    }
}