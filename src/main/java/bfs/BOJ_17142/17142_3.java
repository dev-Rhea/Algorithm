package bfs.BOJ_17142;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

    static int N, M, min, empty;
    static int[][] map;
    static List<int[]> viruses = new ArrayList<>();
    static int[] selected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] == 2) viruses.add(new int[]{i, j});
            }
        }

        empty = 0;
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                if(map[i][j] == 0) empty++;
            }
        }

        min = Integer.MAX_VALUE;
        selected = new int[M];        
        choice(0, 0);

        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    static void choice(int start, int cnt) {
        if(cnt == M) {
            int t = bfs();
            if(t != -1) min = Math.min(min, t);
            return;
        }

        for(int i=start;i<viruses.size();i++) {
            selected[cnt] = i;
            choice(i+1, cnt+1);
        }
    }

    static int bfs() {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        int[][] lab = new int[N][N];
        boolean[][] visited = new boolean[N][N];
        Queue<int[]> queue = new ArrayDeque<>();

        for(int k=0;k<M;k++) {
            int[] v = viruses.get(selected[k]);
            lab[v[0]][v[1]] = 0;
            queue.add(new int[]{v[0], v[1]});
            visited[v[0]][v[1]] = true;
        }

        if(empty == 0) return 0;
        int infected = 0;
        int time = 0;

        while(!queue.isEmpty()) {
            int[] now = queue.poll();

            for(int d=0;d<4;d++) {
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if(map[nx][ny] == 1) continue;
                if(visited[nx][ny]) continue;

                visited[nx][ny] = true;
                lab[nx][ny] = lab[now[0]][now[1]]+1;
                queue.add(new int[]{nx, ny});

                if(map[nx][ny] == 0) {
                    infected++;
                    time = Math.max(time, lab[nx][ny]);
                    if(infected == empty) return time;
                }
            }
        }

        return infected == empty ? time : -1;
    }
}