package DP.BOJ_9177;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static boolean[][] dp;
    static String str1, str2, str3;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());

        for(int i=1;i<=N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            str1 = st.nextToken();
            str2 = st.nextToken();
            str3 = st.nextToken();

            if(check()) System.out.println("Data set " + i+": "+ "yes");
            else System.out.println("Data set " + i+": "+ "no");
        }
    }

    static boolean check() {
        if(str1.length() + str2.length() != str3.length()) return false;

        dp = new boolean[str1.length() + 1][str2.length() + 1];
        dp[0][0] = true;

        for(int i=0;i<=str1.length();i++) {
            for(int j=0;j<=str2.length();j++) {
                // str1의 i-1번째 글자가 str3 (i+j-1)번째 글자와 같음
                if(i > 0 && dp[i-1][j] && str1.charAt(i - 1) == str3.charAt(i + j - 1)) dp[i][j] = true;
                // str2의 j-1번째 글자가 str3 (i+j-1)번째 글자와 같음
                if(j > 0 && dp[i][j-1] && str2.charAt(j - 1) == str3.charAt(i + j - 1)) dp[i][j] = true;
            }
        }
        return dp[str1.length()][str2.length()];
    }
}