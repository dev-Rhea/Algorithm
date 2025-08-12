package bfs.BOJ_17244;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static class Node{
        int x, y, t, mask;
        Node(int x, int y, int t, int mask) {
            this.x = x;
            this.y = y;
            this.t = t;
            this.mask = mask;
        }
    }

    static int N, M, ans;
    static char[][] map;
    static int cnt;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[M][N];
        cnt = 0;

        int x = 0, y = 0;

        for(int i=0;i<M;i++) {
            String str = br.readLine();
            for(int j=0;j<N;j++) {
                map[i][j] = str.charAt(j);

                if(map[i][j] == 'S') {
                    x = i;
                    y = j;
                    map[i][j] = '.';
                }
                if(map[i][j] == 'X') {
                    map[i][j] = (char) (cnt++ + '0');
                }
            }
        }

        int ans = bfs(x, y);
        System.out.println(ans);
    }

    static int bfs(int i, int j) {
        Queue<Node> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[M][N][1 << cnt];

        int[] dy = {1, 0, -1, 0};
        int[] dx = {0, 1, 0, -1};

        queue.add(new Node(i, j, 0, 0));
        visited[i][j][0] = true;

        while(!queue.isEmpty()) {
            Node now = queue.poll();

            for(int d=0;d<4;d++) {
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];

                if(nx < 0 || nx >= M || ny < 0 || ny >= N) continue;
                if(map[nx][ny] == '#' || visited[nx][ny][now.mask]) continue;

                if(map[nx][ny] == '.') {
                    visited[nx][ny][now.mask] = true;
                    queue.add(new Node(nx, ny, now.t + 1, now.mask));
                }
                else if(map[nx][ny] == 'E') {
                    if(cnt == Integer.bitCount(now.mask)) return now.t+1;
                }
                else {
                    int newMask = now.mask | (1 << (map[nx][ny] - '0'));
                    visited[nx][ny][newMask] = true;
                    queue.add(new Node(nx, ny, now.t + 1, newMask));
                }
            } 
        }
        return 0;
    }
}