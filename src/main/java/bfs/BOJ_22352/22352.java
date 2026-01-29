package bfs.BOJ_22352;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

    static int N, M;
    static int[][] pre, post;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        pre = new int[N][M];
        post = new int[N][M];
        visited = new boolean[N][M];

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                pre[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                post[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int sx = -1;
        int sy = -1;
        boolean flag = true;

        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                if(pre[i][j] != post[i][j]) {
                    sx = i;
                    sy = j;
                    break;
                }
            }
            if(sx != -1) break;
        }

        if(sx == -1) {
            System.out.println("YES");
            return;
        }

        int ov = pre[sx][sy];
        int cv = post[sx][sy];

        bfs(sx, sy, ov, cv);

        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                if(visited[i][j]) {
                    if(post[i][j] != cv) {
                        flag = false;
                        break;
                    }
                }
                else {
                    if(pre[i][j] != post[i][j]) {
                        flag = false;
                        break;
                    }
                }
            }
            if(!flag) break;
        }

        System.out.println(flag ? "YES" : "NO");
    }

    static void bfs(int x, int y, int ov, int cv) {
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        visited[x][y] = true;

        while(!queue.isEmpty()) {
            int[] now = queue.poll();

            for(int i=0;i<4;i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if(nx < 0 || nx  >= N || ny < 0 || ny >= M) continue;
                if(visited[nx][ny]) continue;

                if(pre[nx][ny] == ov) {
                    visited[nx][ny] = true;
                    queue.add(new int[]{nx, ny});
                }
            }
        }
    }
}