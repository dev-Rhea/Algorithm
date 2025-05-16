package bfs.BOJ_14466;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static class Bridge {
        int x, y;
        Bridge(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N, K, R;
    static int[][] cow;
    static ArrayList<Bridge>[][] bridge; 
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        cow = new int[K+1][2];
        bridge = new ArrayList[N+1][N+1];

        for(int i=1;i<N+1;i++) {
            for(int j=1;j<N+1;j++) {
                bridge[i][j] = new ArrayList<>();
            }
        }

        for(int i=1;i<=R;i++) {
            st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());

            bridge[r1][c1].add(new Bridge(r2, c2));
            bridge[r2][c2].add(new Bridge(r1, c1));
        }

        for(int i=1;i<=K;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            cow[i][0] = a;
            cow[i][1] = b;
        }

        for(int i=1;i<=K;i++) {
            bfs(i, cow[i][0], cow[i][1]);
        }

        System.out.println(cnt);
    }

    static void bfs(int idx, int x, int y) {
        boolean[][] visited = new boolean[N+1][N+1];
        Queue<Bridge> queue = new ArrayDeque<>();

        visited[x][y] = true;
        queue.add(new Bridge(x, y));

        while(!queue.isEmpty()) {
            Bridge now = queue.poll();

            for(int i=0;i<4;i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                
                if(nx < 1 || nx > N || ny < 1 || ny > N) continue;

                boolean cross = true;

                for(Bridge next : bridge[now.x][now.y]) {
                    if(next.x == nx && next.y == ny) {
                        cross = false;
                        break;
                    }
                }

                if (cross && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.add(new Bridge(nx, ny));
                }
            }
        }

        for(int i=idx+1;i<=K;i++) {
            if (!visited[cow[i][0]][cow[i][1]]) cnt++;
        }
    }
}