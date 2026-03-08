package implementation.BOJ_23290;
import java.io.*;
import java.util.*;

class Main {
    static class Fish {
        int x, y, d;
        Fish(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }

    static int cnt, sx, sy, max;
    static int[] now = new int[3];
    static int[] best = new int[3];
    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] sdx = {-1, 0, 1, 0};
    static int[] sdy = {0, -1, 0, 1};
    static int[][] smell = new int[4][4];
    static int[][][] fish = new int[4][4][8];
    static List<Integer> move;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) - 1;
            fish[x][y][d]++;
        }

        st = new StringTokenizer(br.readLine());
        sx = Integer.parseInt(st.nextToken()) - 1;
        sy = Integer.parseInt(st.nextToken()) - 1;

        int time = 1;
        while (time <= S) {

            int[][][] snapshot = new int[4][4][8];
            for (int i = 0; i < 4; i++)
                for (int j = 0; j < 4; j++)
                    for (int d = 0; d < 8; d++)
                        snapshot[i][j][d] = fish[i][j][d];

            int[][][] copy = new int[4][4][8];

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    for (int fd = 0; fd < 8; fd++) {
                        int count = fish[i][j][fd];
                        if (count == 0) continue;

                        boolean moved = false;
                        for (int delta = 0; delta < 8; delta++) {
                            int nd = ((fd - delta) + 8) % 8;
                            int nx = i + dx[nd];
                            int ny = j + dy[nd];

                            if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4) continue;
                            if (smell[nx][ny] != 0 && time - smell[nx][ny] <= 2) continue;
                            if (nx == sx && ny == sy) continue;

                            copy[nx][ny][nd] += count;
                            moved = true;
                            break;
                        }
                        if (!moved) copy[i][j][fd] += count;
                    }
                }
            }

            fish = copy;

            max = -1;
            move = new ArrayList<>();
            dfs(0, new int[3]);

            String mv = String.valueOf(move.get(0));
            for (int i = 0; i < 3; i++) {
                int dir = mv.charAt(i) - '1';
                sx += sdx[dir];
                sy += sdy[dir];

                if (hasFish(sx, sy)) {
                    clearFish(sx, sy);
                    smell[sx][sy] = time;
                }
            }


            for (int i = 0; i < 4; i++)
                for (int j = 0; j < 4; j++)
                    if (smell[i][j] != 0 && time - smell[i][j] >= 2)
                        smell[i][j] = 0;

            for (int i = 0; i < 4; i++)
                for (int j = 0; j < 4; j++)
                    for (int d = 0; d < 8; d++)
                        fish[i][j][d] += snapshot[i][j][d];

            time++;
        }

        cnt = 0;
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                for (int d = 0; d < 8; d++)
                    cnt += fish[i][j][d];

        System.out.println(cnt);
    }

    static void dfs(int depth, int[] arr) {
        if (depth == 3) {
            int key = arr[0] * 100 + arr[1] * 10 + arr[2];
            int c = eatCount(arr);
            if (max < c) {
                move.clear();
                move.add(key);
                max = c;
            } else if (max == c) {
                move.add(key);
            }
            return;
        }
        for (int i = 1; i <= 4; i++) {
            arr[depth] = i;
            dfs(depth + 1, arr);
        }
    }

    static int eatCount(int[] dir) {
        int x = sx;
        int y = sy;
        int ec = 0;
        boolean[][] visited = new boolean[4][4];

        for (int i = 0; i < 3; i++) {
            int ed = dir[i] - 1;
            x += sdx[ed];
            y += sdy[ed];
            if (x < 0 || x >= 4 || y < 0 || y >= 4) return -1;
            if (!visited[x][y]) ec += countFish(x, y);
            visited[x][y] = true;
        }
        return ec;
    }

    static boolean hasFish(int x, int y) {
        for (int d = 0; d < 8; d++) if (fish[x][y][d] > 0) return true;
        return false;
    }

    static void clearFish(int x, int y) {
        for (int d = 0; d < 8; d++) fish[x][y][d] = 0;
    }

    static int countFish(int x, int y) {
        int s = 0;
        for (int d = 0; d < 8; d++) s += fish[x][y][d];
        return s;
    }
}