package bfs.BOJ_7562;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static int l;
    static boolean[][] visited;
    static int[][] dist;
    static int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};
    static StringBuilder sb = new StringBuilder();

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int i=0; i<T;i++) {
            l = Integer.parseInt(br.readLine());
            visited = new boolean[l][l];
            dist = new int[l][l];

            st = new StringTokenizer(br.readLine());
            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int endX = Integer.parseInt(st.nextToken());
            int endY = Integer.parseInt(st.nextToken());

            sb.append(bfs(startX, startY, endX, endY)).append("\n");
        }

        System.out.print(sb);
    }

    static int bfs(int sx, int sy, int ex, int ey) {
        Queue<Point> queue = new LinkedList<>();
        visited[sx][sy] = true;
        queue.add(new Point(sx, sy)); // 시작점 추가

        while (!queue.isEmpty()) {
            Point now = queue.poll(); // 현재 위치 

            if (now.x == ex && now.y == ey) {
                return dist[now.x][now.y];
            }

            for (int i = 0; i < 8; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && nx < l && ny >= 0 && ny < l && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    dist[nx][ny] = dist[now.x][now.y] + 1; // 이동 횟수 추가 
                    queue.add(new Point(nx, ny)); // 새로운 위치 갱신 
                }
            }
        }

        // 시작점과 도착점이 동일한 경우 
        return 0;
    }
}
