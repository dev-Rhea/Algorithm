package bfs.BOJ_21610;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static class Cloud {
        int x, y;
        Cloud(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static Queue<Cloud> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        map = new int[N+1][N+1];
        visited = new boolean[N+1][N+1];
        queue = new LinkedList<>();

        for(int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=N;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 초기 구름 생성
        queue.add(new Cloud(N, 1));
        queue.add(new Cloud(N, 2));
        queue.add(new Cloud(N-1, 1));
        queue.add(new Cloud(N-1, 2));

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            for(int i=1;i<=N;i++) {
                for(int j=1;j<=N;j++) {
                    visited[i][j] = false;
                }
            }

            // 구름 이동
            move(d, s);

            // 구름 생성
            find();
        }

        int sum = 0;
        for(int i=1;i<=N;i++) {
            for(int j=1;j<=N;j++) {
                sum += map[i][j];
            }
        }
        System.out.println(sum);
    }

    static void move(int d, int s) {
        int[] dx = {0, 0, -1, -1, -1, 0, 1, 1, 1};
        int[] dy = {0, -1, -1, 0, 1, 1, 1, 0, -1};

        // 1단계: 모든 구름 이동 및 물 증가
        List<Cloud> list = new ArrayList<>();
        while(!queue.isEmpty()) {
            Cloud now = queue.poll();

            int nx = ((now.x - 1 + dx[d] * s) % N + N) % N + 1;
            int ny = ((now.y - 1 + dy[d] * s) % N + N) % N + 1;

            map[nx][ny]++;
            visited[nx][ny] = true;
            list.add(new Cloud(nx, ny));
        }

        // 2단계: 모든 이동 완료 후 대각선 복사
        for(Cloud c : list) {
            int cnt = check(c.x, c.y);
            map[c.x][c.y] += cnt;
        }
    }

    static int check(int x, int y) {
        int[] dx = {1, 1, -1, -1};
        int[] dy = {-1, 1, 1, -1};
        int cnt = 0;

        for(int i=0;i<4;i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 1 || nx > N || ny < 1 || ny > N) continue;
            if(map[nx][ny] != 0) cnt++;
        }

        return cnt;
    }

    static void find() {
        for(int i=1;i<=N;i++) {
            for(int j=1;j<=N;j++) {
                if(map[i][j] >= 2 && !visited[i][j]) {
                    map[i][j] -= 2;
                    queue.add(new Cloud(i, j));
                }
            }
        }
    }
}