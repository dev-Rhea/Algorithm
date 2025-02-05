package pre.Week18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.SimpleTimeZone;
import java.util.StringTokenizer;

public class BOJ_14503 {
    static int N, M;
    static int[][] map;
    static int[] dy = {-1, 0 ,1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;
        int dir = d; // 0: ��, 1: 동, 2: ���, 3: 서

        while (true) {
            if (map[r][c] == 0) {
                map[r][c] = 2;
                cnt++;
            }

            boolean flag = false;
            for (int i = 0; i < 4; i++) {
                dir = (dir + 3) % 4; // 왼쪽 방향으로 회전
                int nr = r + dy[dir]; // 새 위치
                int nc = c + dx[dir];

                if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue; // 경계 내부
                if (map[nr][nc] == 0) { // 청소할 공간이 존재
                    r = nr;
                    c = nc; // 이동
                    flag = true; // 청소 완료
                    break;
                }
            }

            if (!flag) { // 네 방향 모두 청소할 공간이 없는 경우
                int back = (dir + 2) % 4; // 후진
                int nr = r + dy[back];
                int nc = c + dx[back];

                if (nr < 0 || nc < 0 || nr >= N || nc >= M) break;
                if (map[nr][nc] == 1) break;

                r = nr;
                c = nc;
            }
        }

        System.out.println(cnt);
    }
}
