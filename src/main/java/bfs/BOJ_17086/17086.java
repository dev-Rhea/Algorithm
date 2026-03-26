package bfs.BOJ_17086;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int max = 0;

        int[][] dist = new int[N][M];
        Queue<int[]> queue = new ArrayDeque<>();

        int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
        int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                int val = Integer.parseInt(st.nextToken());

                if(val == 1) {
                    dist[i][j] = 0;
                    queue.add(new int[]{i, j});
                }
                else dist[i][j] = -1;
            }
        }

        while(!queue.isEmpty()) {
            int[] now = queue.poll();

            for(int i=0;i<8;i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if(nx < 0 || nx >=N || ny < 0 || ny >= M) continue;
                if(dist[nx][ny] != -1) continue;

                dist[nx][ny] = dist[now[0]][now[1]] + 1;
                queue.add(new int[]{nx, ny});
            }
        }


        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                max = Math.max(max, dist[i][j]);
            }
        }
        System.out.println(max);
    }
}