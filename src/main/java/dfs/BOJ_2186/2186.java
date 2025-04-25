package dfs.BOJ_2186;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

    static int N, M, K;
    static String input;
    static char[][] map;
    static int[][][] dp;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new char[N][M];

        for(int i=0;i<N;i++) {
            map[i] = br.readLine().toCharArray();
        }

        input = br.readLine();
        dp = new int[N][M][input.length()];

        for (int[][] arr : dp) {
            for (int[] row : arr) Arrays.fill(row, -1);
        }

        int ans = 0;
        for (int i=0; i<N;i++) {
            for (int j=0;j<M;j++) {
                if(map[i][j] == input.charAt(0)) ans += dfs(i, j, 0);
            }
        }

        System.out.println(ans);
    }

    static int dfs(int x, int y, int depth) {
        if(depth == input.length() - 1) return 1;
        if(dp[x][y][depth] != -1) return dp[x][y][depth];

        int cnt = 0;
        for(int i=0;i<4;i++) {
            for (int j=1;j<=K;j++) {
                int nx = x + dx[i] * j;
                int ny = y + dy[i] * j;

                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

                if(map[nx][ny] == input.charAt(depth + 1)) cnt += dfs(nx, ny, depth + 1);
            }
        }
        return dp[x][y][depth] = cnt;
    }
}