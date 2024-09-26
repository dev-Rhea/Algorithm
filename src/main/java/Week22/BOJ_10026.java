package Week22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_10026 {
    static int N;
    static char[][] map;
    static boolean[][] visit;
    static boolean[][] colorVisit;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        visit = new boolean[N][N];
        colorVisit = new boolean[N][N];

        // 구역 입력
        for (int i = 0; i < N; i++) {
            String line = br.readLine();  // 한 줄 입력받음
            for (int j = 0; j < N; j++) {
                map[i][j] = line.charAt(j);  // 각 문자를 map에 저장
            }
        }

        int cnt = 0;
        int colorCnt = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visit[i][j]) {
                    bfs(i, j);
                    cnt++;
                }
            }
        }

        // 색맹 기준 영역 카운트
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!colorVisit[i][j]) {
                    colorBfs(i, j);
                    colorCnt++;
                }
            }
        }

        System.out.println(cnt + " " + colorCnt);
    }

    static void bfs(int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, j});
        visit[i][j] = true;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int x = now[0];
            int y = now[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

                if (!visit[nx][ny] && map[nx][ny] == map[x][y]) {
                    visit[nx][ny] = true;
                    queue.add(new int[]{nx, ny});
                }
            }
        }
    }

    // 색맹 기준
    static void colorBfs(int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, j});
        colorVisit[i][j] = true;
        char color = map[i][j];

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int x = now[0];
            int y = now[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N || colorVisit[nx][ny]) continue;

                // R과 G는 동일한 영역으로 처리 (색맹인 경우)
                if (color == 'R' || color == 'G') {
                    if (map[nx][ny] == 'R' || map[nx][ny] == 'G') {
                        colorVisit[nx][ny] = true;
                        queue.add(new int[]{nx, ny});
                    }
                } else if (map[nx][ny] == color) {
                    colorVisit[nx][ny] = true;
                    queue.add(new int[]{nx, ny});
                }
            }
        }
    }
}
