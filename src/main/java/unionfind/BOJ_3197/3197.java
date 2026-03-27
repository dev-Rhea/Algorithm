package unionfind.BOJ_3197;
import java.io.*;
import java.util.*;

class Main {

    static int R, C;
    static char[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static int[] parent;

    static int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    static void union(int a, int b) {
        a = find(a); b = find(b);
        if (a != b) parent[b] = a;
    }

    static int idx(int r, int c) {
        return r * C + c;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        parent = new int[R * C];
        for (int i = 0; i < R * C; i++) parent[i] = i;

        int[][] swan = new int[2][2];
        int si = 0;

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'L') {
                    swan[si++] = new int[]{i, j};
                    map[i][j] = '.';
                }
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == '.') {
                    for (int d = 0; d < 4; d++) {
                        int ni = i + dx[d];
                        int nj = j + dy[d];
                        if (ni >= 0 && ni < R && nj >= 0 && nj < C && map[ni][nj] == '.') {
                            union(idx(i, j), idx(ni, nj));
                        }
                    }
                }
            }
        }

        Queue<int[]> ice = new ArrayDeque<>();
        boolean[][] isIce = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'X') {
                    for (int d = 0; d < 4; d++) {
                        int ni = i + dx[d];
                        int nj = j + dy[d];
                        if (ni >= 0 && ni < R && nj >= 0 && nj < C && map[ni][nj] != 'X') {
                            ice.add(new int[]{i, j});
                            isIce[i][j] = true;
                            break;
                        }
                    }
                }
            }
        }

        Queue<int[]> sque0 = new ArrayDeque<>(), nque0 = new ArrayDeque<>();
        Queue<int[]> sque1 = new ArrayDeque<>(), nque1 = new ArrayDeque<>();
        boolean[][] v0 = new boolean[R][C];
        boolean[][] v1 = new boolean[R][C];
        boolean[][] nv0 = new boolean[R][C];
        boolean[][] nv1 = new boolean[R][C];

        sque0.add(swan[0]); v0[swan[0][0]][swan[0][1]] = true;
        sque1.add(swan[1]); v1[swan[1][0]][swan[1][1]] = true;

        int day = 0;
        while (true) {
            // 각 백조 BFS: 물 칸은 계속 탐색, 얼음 칸은 nque에 보관
            bfs(sque0, nque0, v0, nv0);
            bfs(sque1, nque1, v1, nv1);

            // 만남 판별
            if (find(idx(swan[0][0], swan[0][1])) == find(idx(swan[1][0], swan[1][1]))) break;

            day++;

            int size = ice.size();
            for (int i = 0; i < size; i++) {
                int[] now = ice.poll();
                map[now[0]][now[1]] = '.';

                for (int d = 0; d < 4; d++) {
                    int nr = now[0] + dx[d];
                    int nc = now[1] + dy[d];
                    if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;

                    if (map[nr][nc] == '.') {
                        union(idx(now[0], now[1]), idx(nr, nc));
                    } else if (map[nr][nc] == 'X' && !isIce[nr][nc]) {
                        ice.add(new int[]{nr, nc});
                        isIce[nr][nc] = true;
                    }
                }
            }

            filtered(nque0, sque0, v0);
            filtered(nque1, sque1, v1);
        }

        System.out.println(day);
    }

    static void bfs(Queue<int[]> queue, Queue<int[]> next, boolean[][] v, boolean[][] nv) {
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = now[0] + dx[d];
                int nc = now[1] + dy[d];
                if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;

                if (map[nr][nc] == 'X') {
                    if (!nv[nr][nc]) {
                        nv[nr][nc] = true;
                        next.add(new int[]{nr, nc});
                    }
                } else {
                    if (!v[nr][nc]) {
                        v[nr][nc] = true;
                        queue.add(new int[]{nr, nc});
                    }
                }
            }
        }
    }

    static void filtered(Queue<int[]> next, Queue<int[]> prev, boolean[][] v) {
        Queue<int[]> temp = new ArrayDeque<>();
        while (!next.isEmpty()) {
            int[] now = next.poll();
            if (map[now[0]][now[1]] == '.') {
                v[now[0]][now[1]] = true;
                prev.add(now);
            } else {
                temp.add(now);
            }
        }
        next.addAll(temp);
    }
}