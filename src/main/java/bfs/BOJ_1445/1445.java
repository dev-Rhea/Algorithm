package bfs.BOJ_1445;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

    static int N, M;
    static char[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        int sx = 0, sy = 0;

        for(int i=0;i<N;i++) {
            String str = br.readLine();
            for(int j=0;j<M;j++) {
                map[i][j] = str.charAt(j);

                if(map[i][j] == 'S') {
                    sx = i;
                    sy = j;
                }
            }
        }

        int[][] cnt = new int[N][M];
        int[][] mv = new int[N][M];

        for(int[] row : cnt) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        for(int[] row : mv) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        Queue<int[]> queue = new PriorityQueue<>((a, b) -> a[0] != b[0] ? a[0]-b[0] : a[1]-b[1]);

        cnt[sx][sy] = 0;
        mv[sx][sy] = 0;
        queue.add(new int[]{0, 0, sx, sy});

        while(!queue.isEmpty()) {
            int[] now = queue.poll();

            if(now[0] > cnt[now[2]][now[3]] || (now[0] == cnt[now[2]][now[3]] && now[1] > mv[now[2]][now[3]])) continue;

            for(int d=0;d<4;d++) {
                int nx = now[2] + dx[d];
                int ny = now[3] + dy[d];
                int nt, nm;

                if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if(map[nx][ny] == 'g') {
                    nt = now[0] + 1;
                    nm = now[1];
                }
                else {
                    nt = now[0];
                    nm = now[1] + (map[nx][ny] == '.' && check(nx, ny) ? 1 : 0);
                }

                if(nt < cnt[nx][ny] || (nt == cnt[nx][ny] && nm < mv[nx][ny])) {
                    cnt[nx][ny] = nt;
                    mv[nx][ny] = nm;
                    queue.add(new int[]{nt, nm, nx, ny});
                }
                
            }
        }

        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                if(map[i][j] == 'F') {
                    System.out.println(cnt[i][j] + " " + mv[i][j]);
                    return;
                }
            }
        }
    }

    static boolean check(int x, int y) {
        for(int d=0;d<4;d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
            if(map[nx][ny] == 'g') return true;
        }

        return false;
    }
}