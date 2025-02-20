package DP.BOJ_9184;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][][] dp = new int[21][21][21]; // memoization

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        while (!(a == -1 && b == -1 && c == -1)) {
            sb.append("w(" + a +", " + b + ", " + c + ") = ");
            sb.append(w(a, b, c)).append("\n");

            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
        }

        System.out.println(sb);
    }

    static int w(int a, int b, int c) {

        if(validation(a, b, c) && dp[a][b][c] != 0) {
            return dp[a][b][c];
        }
        else if(a<=0 || b <= 0 || c <=0) {
            return 1;
        }
        else if(a>20 || b > 20 || c>20) {
            return dp[20][20][20] = w(20, 20, 20);
        }
        else if(a<b && b<c) {
            return dp[a][b][c] = w(a, b, c-1) + w(a, b-1, c-1) - w(a, b-1, c);
        }
        return dp[a][b][c] = w(a-1, b, c) + w(a-1, b-1, c) + w(a-1, b, c-1) - w(a-1, b-1, c-1);
    }

    static boolean validation(int a, int b, int c) {
        return 0 <= a && a <= 20 && 0 <= b && b <= 20 && 0 <= c && c <= 20;
    }
}
