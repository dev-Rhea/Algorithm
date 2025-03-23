import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

    static int R, C;
    static char[][] map;
    static int[][] jihunTime;
    static Queue<int[]> queue = new LinkedList<>();

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        jihunTime = new int[R][C];

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
                jihunTime[i][j] = -1;  // 지훈이의 방문 여부를 표시

                if (map[i][j] == 'F') { // 불이면 큐에 삽입
                    queue.offer(new int[]{i, j, 0}); // (x, y, type) - type 0: 불
                }
                if (map[i][j] == 'J') { // 지훈이면 큐에 삽입
                    queue.offer(new int[]{i, j, 1}); // (x, y, type) - type 1: 지훈
                    jihunTime[i][j] = 0; // 지훈이의 시작점은 0으로 설정
                }
            }
        }

        int result = bfs();
        
        if (result == -1) {
            System.out.println("IMPOSSIBLE");
        } else {
            System.out.println(result);
        }
    }

    static int bfs() {
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            int type = current[2]; // 0: 불, 1: 지훈

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 범위를 벗어난 경우 (지훈이가 탈출한 경우)
                if (type == 1 && (nx < 0 || nx >= R || ny < 0 || ny >= C)) {
                    return jihunTime[x][y] + 1; // 탈출 성공
                }

                if (nx >= 0 && nx < R && ny >= 0 && ny < C) {
                    // 불의 확산 처리
                    if (type == 0 && map[nx][ny] == '.') { // 벽이 아니고 불이 퍼질 수 있는 곳이면
                        map[nx][ny] = 'F'; // 불로 표시
                        queue.offer(new int[]{nx, ny, 0});
                    }

                    // 지훈이의 이동 처리
                    if (type == 1 && map[nx][ny] == '.' && jihunTime[nx][ny] == -1) {
                        jihunTime[nx][ny] = jihunTime[x][y] + 1;
                        queue.offer(new int[]{nx, ny, 1});
                    }
                }
            }
        }
        return -1; // 지훈이가 탈출하지 못한 경우
    }
}
