package bruteforce.BOJ_14500;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int max;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];

        // 종이 입력
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                visited[i][j] = true;
                DFS(i, j, map[i][j], 1);
                visited[i][j] = false;
            }
        }

        System.out.println(max);
    }

    private static void DFS(int x, int y, int sum, int depth) {
        if(depth == 4) {
            max = Math.max(sum, max);
            visited[x][y] = false;
            return;
        }

        for(int i=0;i<3;i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(ny >= 0 && nx >= 0 && ny < M && nx < N) {
                if(!visited[nx][ny]) {
                    if(depth == 2) {
                        visited[nx][ny] = true;
                        DFS(x, y, sum + map[nx][ny], depth + 1);
                        visited[nx][ny] = false;
                    }
                    visited[nx][ny] = true;
                    DFS(nx, ny, sum + map[nx][ny], depth + 1);
                    visited[nx][ny] = false;
                }
            }
        }
    }
}
