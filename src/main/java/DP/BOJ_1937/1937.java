package DP.BOJ_1937;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int n;
    static int[][] bamboo;
    static int[][] dp;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        bamboo = new int[n][n];
        dp = new int[n][n];

        for(int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++) {
                bamboo[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int max = Integer.MIN_VALUE;
        
        // 시작점 부터 탐색
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                int cnt = dfs(i, j);
                max = Math.max(max, cnt);
            }
        }

        System.out.println(max);
    }

    private static int dfs(int x, int y) {
        if(dp[x][y] != 0) return dp[x][y]; // 메모이제이션 

        dp[x][y] = 1; // 아직 이동하지 않은 경우

        for(int i=0;i<4;i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx >= 0 && nx < n && ny >= 0 && ny < n) {
                if(bamboo[nx][ny] > bamboo[x][y]) dp[x][y] = Math.max(dp[x][y], dfs(nx, ny) + 1);
            }
        }
        return dp[x][y];
    }
}