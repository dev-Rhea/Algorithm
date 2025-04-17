package bfs.BOJ_7576;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

    static int N, M;
    static int[][] box;
    static boolean[][] visited;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        box = new int[N][M];
        Queue<int[]> queue = new LinkedList<>();

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                box[i][j] = Integer.parseInt(st.nextToken());

                if(box[i][j] == 1) queue.add(new int[]{i, j});
            }
        }

        int day = bfs(queue);

        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {

                if(box[i][j] == 0) {
                    System.out.println(-1);
                    return;
                }
            }
        }
        System.out.println(day);
    }

    static int bfs(Queue<int[]> queue) {
        int d = -1;

        while(!queue.isEmpty()) {
            int size = queue.size();
            d++;

            for(int i=0;i<size;i++) {
                int[] now = queue.poll();
                
                for(int j=0;j<4;j++) {
                    int nx = now[0] + dx[j];
                    int ny = now[1] + dy[j];

                    if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

                    if(box[nx][ny] == 0) {
                        box[nx][ny] = 1;
                        queue.add(new int[]{nx, ny});
                    }
                }
            }
        }
        return d;
    }
}