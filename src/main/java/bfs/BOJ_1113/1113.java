package bfs.BOJ_1113;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static class Ground{
        int x, y, h;
        Ground(int x, int y, int h) {
            this.x = x;
            this.y = y;
            this.h = h;
        }
    }

    static int N, M;
    static int[][] map;
    static Queue<Ground> queue;
    static boolean[][] visited;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for(int i=0;i<N;i++) {
            String input = br.readLine();
            for(int j=0;j<M;j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }

        System.out.println(find());
    }

    static int find() {
        Queue<Ground> pq = new PriorityQueue<>((o1, o2) -> o1.h - o2.h);

        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                if(i == 0 || i == N-1 || j == 0 || j == M-1) {
                    pq.add(new Ground(i, j, map[i][j]));
                    visited[i][j] = true;
                }
            }
        }

        int ans = 0;

        while(!pq.isEmpty()) {
            Ground now = pq.poll();

            for(int i=0;i<4;i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if(visited[nx][ny]) continue;

                visited[nx][ny] = true;

                if(now.h > map[nx][ny]) {
                    ans += now.h - map[nx][ny];
                    pq.add(new Ground(nx, ny, now.h));
                }
                else pq.add(new Ground(nx, ny, map[nx][ny]));
            }
        }
        return ans;
    }
}