package implementation.BOJ_21610;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N+1][N+1];
        visited = new boolean[N+1][N+1];

        for(int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=N;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited[N][1] = true;
        visited[N][2] = true;
        visited[N-1][1] = true;
        visited[N-1][2] = true;

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            boolean[][] isRain = moveCloud(d, s);
            copyWater(isRain);
            makeCloud(isRain);
        }
        
        int ans = 0;

        for(int i=1;i<=N;i++) {
            for(int j=1;j<=N;j++) {
                ans += map[i][j];
            }
        }

        System.out.println(ans);
    }

    static boolean[][] moveCloud(int d, int s) {
        boolean[][] isRain = new boolean[N+1][N+1];
        // ←, ↖, ↑, ↗, →, ↘, ↓, ↙
        int[] dx = {0, 0, -1, -1, -1, 0, 1, 1, 1};
        int[] dy = {0, -1, -1, 0, 1, 1, 1, 0, -1};

        for(int i=1;i<=N;i++) {
            for(int j=1;j<=N;j++) {
                if(!visited[i][j]) continue;

                int nx = ((i - 1 + dx[d] * s) % N + N) % N + 1;
                int ny = ((j - 1 + dy[d] * s) % N + N) % N + 1;
                map[nx][ny]++;
                isRain[nx][ny] = true;
            }
        }
        return isRain;
    }

    static void copyWater(boolean[][] isRain) {
        int[] dx = {-1, -1, 1, 1};
        int[] dy = {-1, 1, 1, -1};

        for(int i=1;i<=N;i++) {
            for(int j=1;j<=N;j++) {
                if(!isRain[i][j]) continue;

                int cnt = 0;

                for(int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if(nx > 0 && nx <= N && ny > 0 && ny <= N && map[nx][ny] >= 1) {
                        cnt++;
                    }
                }
                map[i][j] += cnt;
            }
        }
    }

    static void makeCloud(boolean[][] isRain) {
        visited = new boolean[N+1][N+1];
        
        for(int i=1;i<=N;i++) {
            for(int j=1;j<=N;j++) {
                if(!isRain[i][j] && map[i][j] >= 2) {
                    map[i][j] -= 2;
                    visited[i][j] = true;
                }
            }
        }
    }
}