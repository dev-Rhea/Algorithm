package DP.BOJ_4991;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

    static int w, h, cnt;
    static char[][] map;
    static int[][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            if(w == 0 && h == 0) break;

            cnt = 0;
            int[] rx, ry;
            map = new char[h][w];

            String[] str = new String[h];
            for(int i=0;i<h;i++) str[i] = br.readLine();

            for(int i=0;i<h;i++) {
                for(int j=0;j<w;j++) {
                    if(str[i].charAt(j) == '*') cnt++;
                }
            }

            rx = new int[cnt+1];
            ry = new int[cnt+1];
            int idx = 1;
            for(int i=0;i<h;i++) {
                for(int j=0;j<w;j++) {
                    map[i][j] = str[i].charAt(j);

                    if(map[i][j] == 'o') {
                        rx[0] = i;
                        ry[0] = j;
                    }
                    if(map[i][j] == '*') {
                        rx[idx] = i;
                        ry[idx] = j;
                        idx++;
                    }
                }
            }

            dist = new int[cnt+1][cnt+1];

            for(int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE);
            for(int i=0;i<=cnt;i++) dist[i][i] = 0;

            for(int i=0;i<=cnt;i++) {
                bfs(i, rx, ry);
            }

            boolean impossible = false;
            for(int i=1;i<=cnt;i++) {
                if(dist[0][i] == Integer.MAX_VALUE) {
                    impossible = true;
                    break;
                }
            }

            if(impossible) {
                sb.append(-1).append('\n');
                continue;
            }

            int[][] dp = new int[1 << cnt][cnt+1];
            for(int[] row : dp) Arrays.fill(row, Integer.MAX_VALUE);
            dp[0][0] = 0;

            int ans = Integer.MAX_VALUE;
            for(int visited = 0;visited<(1<<cnt);visited++) {
                for(int now=0;now<=cnt;now++) {
                    if(dp[visited][now] == Integer.MAX_VALUE) continue;
                    if(visited == (1 << cnt) - 1) {
                        ans = Math.min(ans, dp[visited][now]);
                        continue;
                    }

                    for(int next=1;next<=cnt;next++) {
                        if((visited & (1 << (next-1))) != 0) continue;
                        if(dist[now][next] == Integer.MAX_VALUE) continue;

                        int nv = visited | (1 << (next-1));
                        int nc = dp[visited][now] + dist[now][next];
                        if(dp[nv][next] > nc) dp[nv][next] = nc;
                    }
                }
            }
            sb.append(ans == Integer.MAX_VALUE ? -1 : ans).append('\n');
        }

        System.out.print(sb);
    }

    static void bfs(int idx, int[] rx, int[] ry) {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        int sx = rx[idx];
        int sy = ry[idx];
        
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{sx, sy});

        int[][] visited = new int[h][w];
        for(int[] row : visited) Arrays.fill(row, -1);
        visited[sx][sy] = 0;

        while(!queue.isEmpty()) {
            int[] now = queue.poll();
            int x = now[0];
            int y = now[1];

            for(int d=0;d<4;d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if(nx < 0 || nx >= h || ny < 0 || ny >= w) continue;
                if(map[nx][ny] == 'x') continue;
                if(visited[nx][ny] != -1) continue;
                
                visited[nx][ny] = visited[x][y] + 1;
                queue.add(new int[]{nx, ny});
            }
        }

        for(int i=1;i<=cnt;i++) {
            int tx = rx[i];
            int ty = ry[i];
            if(visited[tx][ty] != -1) dist[idx][i] = dist[i][idx] = visited[tx][ty];
        }
    }
}