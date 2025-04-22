package bfs.BOJ_7569;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static int M, N, H;
    static int[][][] box;
    static int[][][] dist;
    static int[] dy = {1, 0, -1, 0, 0, 0};
    static int[] dx = {0, 1, 0, -1, 0, 0};
    static int[] dz = {0, 0, 0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        box = new int[H][N][M];
        dist = new int[H][N][M];
        Queue<int[]> queue = new LinkedList<>();


        for(int i=0;i<H;i++) {
            for(int j=0;j<N;j++) {
                st = new StringTokenizer(br.readLine());
                for(int k=0;k<M;k++) {
                    box[i][j][k] = Integer.parseInt(st.nextToken());

                    if(box[i][j][k] == 1) {
                        queue.add(new int[]{i, j, k});
                    }
                    if(box[i][j][k] == 0) {
                        dist[i][j][k] = -1;
                    }
                }
            }
        }

        while(!queue.isEmpty()) {
            int[] now = queue.poll();
            
            for(int i=0;i<6;i++) {
                int nz = now[0] + dz[i];
                int ny = now[1] + dy[i];
                int nx = now[2] + dx[i];

                if(nx < 0 || ny < 0 || nz < 0 || nx >= M || ny >= N || nz >= H) continue;

                if(dist[nz][ny][nx] != -1) continue;

                dist[nz][ny][nx] = dist[now[0]][now[1]][now[2]] + 1;
                queue.add(new int[]{nz, ny, nx});
            }
        }

        int ans = 0;

        for(int i=0;i<H;i++) {
            for(int j=0;j<N;j++) {
                for(int k=0;k<M;k++) {
                    if(dist[i][j][k] == -1 && box[i][j][k] == 0) {
                        System.out.println(-1);
                        return;
                    }
                    ans = Math.max(ans, dist[i][j][k]);
                }
            }
        }

        System.out.println(ans);
    }
}
