package bfs.BOJ_1726;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static class Move {
        int x, y, d, cnt;
        Move(int x, int y, int d, int cnt) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.cnt = cnt;
        }
    }

    static int M, N, cnt;
    static int[][] map;
    static int[] dx = {0, 0, 0, 1, -1}; 
    static int[] dy = {0, 1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[M+1][N+1];
        for(int i=1;i<=M;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=N;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        int sx = Integer.parseInt(st.nextToken());
        int sy = Integer.parseInt(st.nextToken());
        int sd = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int ex = Integer.parseInt(st.nextToken());
        int ey = Integer.parseInt(st.nextToken());
        int ed = Integer.parseInt(st.nextToken());

        System.out.println(bfs(sx, sy, sd, ex, ey, ed));
    }

    static int bfs(int sx, int sy, int sd, int ex, int ey, int ed) {
        Queue<Move> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[M+1][N+1][5];

        queue.add(new Move(sx, sy, sd, 0));
        visited[sx][sy][sd] = true;

        while(!queue.isEmpty()) {
            Move now = queue.poll();

            if(now.x == ex && now.y == ey && now.d == ed) return now.cnt;

            for(int k=1;k<=3;k++) {
                int nx = now.x + dx[now.d] * k;
                int ny = now.y + dy[now.d] * k;

                if(nx < 1 || nx > M || ny < 1 || ny > N) break;
                if(map[nx][ny] == 1) break;
                if(visited[nx][ny][now.d]) continue;

                visited[nx][ny][now.d] = true;
                queue.add(new Move(nx, ny, now.d, now.cnt+1));
            }

            int left = turn(now.d, true);
            if(!visited[now.x][now.y][left]) {
                visited[now.x][now.y][left] = true;
                queue.add(new Move(now.x, now.y, left, now.cnt+1));
            }

            int right = turn(now.d, false);
            if(!visited[now.x][now.y][right]) {
                visited[now.x][now.y][right] = true;
                queue.add(new Move(now.x, now.y, right, now.cnt+1));
            }
        }
        return -1;
    }

    static int turn(int d, boolean r) {
        if(r) {  // 왼쪽 회전
            if(d == 1) return 4;  
            if(d == 2) return 3;
            if(d == 3) return 1;
            return 2;
        }
        // 오른쪽 회전 
        if(d == 1) return 3;
        if(d == 2) return 4; 
        if(d == 3) return 2;
        return 1;
    }
}
