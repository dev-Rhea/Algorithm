package DP.BOJ_9252;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class Main {
    
    static StringBuilder sb = new StringBuilder();
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str1 = br.readLine();
        String str2 = br.readLine();
        dp = new int[str1.length()+1][str2.length()+1];

        for(int i=1;i<str1.length()+1;i++) {
            for(int j=1;j<str2.length()+1;j++) {
                if(str1.charAt(i-1) == str2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
                else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        getString(str1, str1.length(), str2.length());

        System.out.println(dp[str1.length()][str2.length()]);
        System.out.println(sb);

    }

    static void getString(String s, int x, int y) {
        Stack<Character> stack = new Stack<>();

        while(x > 0 && y > 0) {
            // if(x == 0 || y == 0) break;

            if(dp[x][y] == dp[x-1][y]) x--;
            else if(dp[x][y] == dp[x][y-1]) y--;
            else {
                stack.push(s.charAt(x-1));
                x--;
                y--;
            }
        }

        while(!stack.isEmpty()) sb.append(stack.pop());
    }
}