package pre.Week22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2638 {
    static int N, M;
    static int[][] map;
    static boolean[][] visit;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visit = new boolean[N][M];

        // 모눈종이 입력
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;

        while (true) {
            // 방문 배열 초기화
            visit = new boolean[N][M];
            // 공기 확산
            Air(0, 0);

            // 치즈 녹이기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 1) {
                        Melt(i, j);
                    }
                }
            }

            // 치즈 상태 확인 및 초기화
            int notCheese = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 1) {
                        notCheese++;
                    }
                    if (map[i][j] == 2) {
                        map[i][j] = 0;
                    }
                }
            }

            time++;

            // 모든 치즈가 녹았으면 종료
            if (notCheese == 0) {
                break;
            }
        }

        System.out.println(time);
    }

    // 치즈 녹이기
    public static void Melt(int x, int y) {
        int cnt = 0;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (isValid(nx, ny) && map[nx][ny] == 3) {
                cnt++;
            }
        }

        if (cnt >= 2) {
            map[x][y] = 2;
        }
    }

    // 유효한 좌표인지 확인
    public static boolean isValid(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    // 공기 확산
    public static void Air(int x, int y) {
        visit[x][y] = true;
        map[x][y] = 3;  // 공기 영역 표시

        for (int k = 0; k < 4; k++) {
            int nx = x + dx[k];  // x에 dx[k] 더하기
            int ny = y + dy[k];  // y에 dy[k] 더하기

            if (!isValid(nx, ny)) continue;
            if (visit[nx][ny] || map[nx][ny] == 1 || map[nx][ny] == 2) continue;

            Air(nx, ny);  // 재귀 호출로 공기 확산
        }
    }
}
