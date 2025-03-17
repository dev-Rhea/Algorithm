package dijkstra.BOJ_4485;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
    static int N;
    static int[][] map;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int cnt = 0;

        while(true) {
            cnt++;

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            if(N==0) break;

            map = new int[N][N];

            for(int i=0;i<N;i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for(int j=0;j<N;j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
    
            System.out.println("Problem " + cnt + ": " + dijkstra());
        }
    }

    static int  dijkstra() {
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);

        int[][] dist = new int[N][N];

        for(int i=0;i<N;i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        queue.offer(new int[] {0, 0, map[0][0]}); // 시작지점과 해당 위치 비용 저장 
        dist[0][0] = map[0][0]; // 최소 비용 저장 배열 

        while(!queue.isEmpty()) {
            int[] temp = queue.poll();

            // 현재 위치 
            int x = temp[0];
            int y = temp[1];

            for(int i=0;i<4;i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx >= 0 && nx < N && ny >= 0 && ny < N) { // 범위 내에서 계산 
                    if(dist[nx][ny] > dist[x][y] + map[nx][ny]) {
                        dist[nx][ny] = dist[x][y] + map[nx][ny]; // 다음 위치로 이동하고 난 후의 최소 비용
                        queue.offer(new int[] {nx, ny, dist[nx][ny]});
                    }
                }
            }
        }
        return dist[N-1][N-1];
    }
}