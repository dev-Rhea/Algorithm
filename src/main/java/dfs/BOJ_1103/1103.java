package dfs.BOJ_1103;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int N, M;
    static boolean isCycle = false;
    static char[][] map;
    static boolean[][] visited;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int cnt = 0;

        map = new char[N][M];
        visited = new boolean[N][M];
        dp = new int[N][M];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        cnt = move(0, 0);

        System.out.println(isCycle ? -1 : cnt);
    }

    static int move(int x, int y) {
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        if(isCycle) return -1;
        if(dp[x][y] != 0) return dp[x][y];

        visited[x][y] = true;

        int n = map[x][y] - '0';
        int max = 0;

        for(int i=0;i<4;i++) {
            int nx = x + (dx[i] * n);
            int ny = y + (dy[i] * n);

            if(nx < 0 || nx >= N || ny < 0 || ny >= M || map[nx][ny] == 'H') {
                max = Math.max(max, 1);
                continue;
            }

            if(visited[nx][ny]) {
                isCycle = true;
                return -1;
            }

            int next = move(nx, ny);
            if(isCycle) return -1;

            max = Math.max(max, next+1);
        }
        visited[x][y] = false;

        return dp[x][y] = max;
    }
}