package DP.BOJ_12996;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int[][] CASE = {
        {1, 0, 0},
        {0, 1, 0},
        {0, 0, 1},
        {1, 1, 0},
        {1, 0, 1},
        {0, 1, 1},
        {1, 1, 1}
    };
    static int MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int S = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        long[][][] prev = new long[d+1][k+1][h+1];
        long[][][] curr = new long[d+1][k+1][h+1];
        
        prev[d][k][h] = 1;

        for(int s = 1; s <= S; s++) {
            for(int i = 0; i <= d; i++) {
                for(int j = 0; j <= k; j++) {
                    for(int l = 0; l <= h; l++) {
                        curr[i][j][l] = 0;
                    }
                }
            }
            
            for(int i = 0; i <= d; i++) {
                for(int j = 0; j <= k; j++) {
                    for(int l = 0; l <= h; l++) {
                        for(int[] c : CASE) {
                            int prevD = i + c[0];
                            int prevK = j + c[1];
                            int prevH = l + c[2];
                            
                            if(prevD <= d && prevK <= k && prevH <= h) {
                                curr[i][j][l] = (curr[i][j][l] + prev[prevD][prevK][prevH]) % MOD;
                            }
                        }
                    }
                }
            }
            
            long[][][] temp = prev;
            prev = curr;
            curr = temp;
        }

        System.out.println(prev[0][0][0]);
    }
}