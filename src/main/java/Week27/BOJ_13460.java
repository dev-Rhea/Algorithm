package Week27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BOJ_13460 {

    static int N, M;
    static char[][] map;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static boolean[][][][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visit = new boolean[N][M][N][M];

        int rx = 0, ry = 0, bx = 0, by = 0;

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'R') {
                    rx = i;
                    ry = j;
                } else if (map[i][j] == 'B') {
                    bx = i;
                    by = j;
                }
            }
        }
        System.out.println(bfs(rx, ry, bx, by));
    }

    static int bfs(int rx, int ry, int bx, int by) {
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        visit[rx][ry][bx][by] = true;
        queue.add(new int[]{rx, ry, bx, by, 0});

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            if (now[4] > 10) {
                break;
            }

            if (map[now[0]][now[1]] == 'O') {
                return now[4];
            }

            for (int i = 0; i < 4; i++) {
                int nrx = now[0];
                int nry = now[1];
                int nbx = now[2];
                int nby = now[3];

                int rCnt = 0, bCnt = 0;

                while (true) {
                    if (map[nrx + dx[i]][nry + dy[i]] == '#') {
                        break;
                    }
                    nrx += dx[i];
                    nry += dy[i];
                    ++rCnt;

                    if (map[nrx][nry] == 'O') {
                        break;
                    }
                }

                while (true) {
                    if (map[nbx + dx[i]][nby + dy[i]] == '#') {
                        break;
                    }
                    nbx += dx[i];
                    nby += dy[i];
                    ++bCnt;

                    if (map[nbx][nby] == 'O') {
                        break;
                    }
                }
                if(map[nbx][nby] == 'O') {
                    continue;
                }

                if (nrx == nbx && nry == nby) {
                    if (rCnt > bCnt) {
                        nrx -= dx[i];
                        nry -= dy[i];
                    } else {
                        nbx -= dx[i];
                        nby -= dy[i];
                    }
                }
                if (!visit[nrx][nry][nbx][nby]) {
                    visit[nrx][nry][nbx][nby] = true;
                    queue.add(new int[]{nrx, nry, nbx, nby, now[4] + 1});
                }
            }
        }
        return -1;
    }

}
