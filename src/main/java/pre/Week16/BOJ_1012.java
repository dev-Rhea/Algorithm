package pre.Week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1012 {
    static int T, M, N, K;
    static int[][] map;
    static boolean[][] visit;
    static int[] dy = {1, 0 , -1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            String[] input = br.readLine().split(" ");
            M = Integer.parseInt(input[0]);
            N = Integer.parseInt(input[1]);
            K = Integer.parseInt(input[2]);

            map = new int[M][N];
            visit = new boolean[M][N];

            for (int i = 0; i < K; i++) {
                String[] xy = br.readLine().split(" ");
                int x = Integer.parseInt(xy[0]);
                int y = Integer.parseInt(xy[1]);
                map[x][y] = 1;
            }

            int cnt = 0;
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == 1 && !visit[i][j]) {
                        DFS(i, j);
                        cnt++;
                    }
                }
            }
            System.out.println(cnt);
        }


    }
    static void DFS(int y, int x) {
        visit[y][x] = true;

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (ny < 0 || nx < 0 || ny >= M || nx >= N) continue;
            if (map[ny][nx] == 1 && !visit[ny][nx]) {
                DFS(ny, nx);
            }
        }
    }
}
