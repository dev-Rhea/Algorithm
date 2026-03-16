package DP.BOJ_1648;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int N, M;
    static final int MOD = 9901;
    static int[] temp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        if((long) N * M % 2 != 0) {
            System.out.println(0);
            return;
        }

        if(N > M) {
            int t = N;
            N = M;
            M = t;
        }

        int[] dp = new int[1 << N];
        dp[0] = 1;

        for(int j=0;j<M;j++) {
            temp = new int[1 << N];
            for(int mask = 0;mask<(1<<N);mask++) {
                if(dp[mask] > 0) fill(mask, 0, 0, dp[mask]);
            }
            dp = temp;
        }

        System.out.println(dp[0]);
    }

    static void fill(int mask, int row, int outline, int cnt) {
        if(row == N) {
            temp[outline] = (temp[outline] + cnt) % MOD;
            return;
        }

        if((mask >> row & 1) == 1) fill(mask, row+1, outline, cnt);
        else {
            fill(mask, row+1, outline | (1 << row), cnt);
            if(row + 1 < N && (mask >> (row + 1) & 1) == 0) fill(mask, row+2, outline, cnt);
        }
    }
}

