package DP.BOJ_1520;
import java.io.*;
import java.util.*;

class Main {

    static int M, N;
    static int[][] map;
    static long[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[M][N];

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new long[M][N];
        for(long[] d : dp) {
            Arrays.fill(d, -1);
        }
        
        System.out.println(dfs(0, 0));
    }

    static long dfs(int x, int y) {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        if(x == M -1 && y == N - 1) return 1;
        if(dp[x][y] != -1) return dp[x][y];

        dp[x][y] = 0;
        for(int k=0;k<4;k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];

            if(nx < 0 || nx >= M || ny < 0 || ny >= N) continue;
            if(map[nx][ny] >= map[x][y]) continue;

            dp[x][y] += dfs(nx, ny);
            
        }

        return dp[x][y];
    }
}