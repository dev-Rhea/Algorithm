package dfs.BOJ_18430;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int N, M, max = 0;
    static int[][] map;
    static boolean[][] visited;
    static int[][][] dirs = {
        {{0,-1},{1,0}},  // 왼쪽 + 아래
        {{-1,0},{0,-1}}, // 위 + 왼쪽
        {{-1,0},{0,1}},  // 위 + 오른쪽
        {{0,1},{1,0}}    // 오른쪽 + 아래
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0);
        System.out.println(max);
    }

    static void dfs(int now, int sum) {
        if (now == N * M) {
            max = Math.max(max, sum);
            return;
        }

        int r = now / M;
        int c = now % M;

        if (!visited[r][c]) {
            for (int[][] d : dirs) {
                int r1 = r + d[0][0], c1 = c + d[0][1];
                int r2 = r + d[1][0], c2 = c + d[1][1];
                if (0 <= r1 && r1 < N && 0 <= c1 && c1 < M
                 && 0 <= r2 && r2 < N && 0 <= c2 && c2 < M
                 && !visited[r1][c1] && !visited[r2][c2]) {
                    visited[r][c] = visited[r1][c1] = visited[r2][c2] = true;
                    int add = map[r][c] * 2 + map[r1][c1] + map[r2][c2];
                    dfs(now + 1, sum + add);
                    visited[r][c] = visited[r1][c1] = visited[r2][c2] = false;
                }
            }
        }

        dfs(now + 1, sum);
    }
}
