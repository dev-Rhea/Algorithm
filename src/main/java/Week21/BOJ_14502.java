package Week21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14502 {
    static int N, M, ans;
    static int[][] map;
    static int[][] virus;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ans = Integer.MIN_VALUE;

        dfs(0);
        System.out.println(ans);
    }

    public static void dfs(int wall) {
        if(wall == 3) {
            bfs();
            return;
        }
        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                if(map[i][j] == 0) {
                    map[i][j] = 1;
                    dfs(wall + 1);
                    map[i][j] = 0;
                }
            }
        }
    }

    public static void bfs() {
        Queue<int[]> queue = new LinkedList<>();
        virus = new int[N][M];

        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                virus[i][j] = map[i][j];

                if(virus[i][j] == 2) {
                    queue.add(new int[]{i, j});
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] tmp = queue.poll();
            int x = tmp[0];
            int y = tmp[1];

            for(int i=0;i<4;i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx >= 0 && ny >= 0 && nx < N && ny <M && virus[nx][ny] == 0) {
                    virus[nx][ny] = 2;
                    queue.add(new int[]{nx, ny});
                }
            }
        }

        int cnt = 0;
        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                if(virus[i][j] == 0) {
                    cnt++;
                }
            }
        }

        ans = Math.max(ans, cnt);
    }
}
