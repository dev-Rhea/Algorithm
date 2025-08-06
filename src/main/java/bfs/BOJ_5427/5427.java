package bfs.BOJ_5427;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static class Node{
        int x, y, t;
        Node(int x, int y, int t) {
            this.x = x;
            this.y = y;
            this.t = t;
        }
    }

    static int w, h;
    static char[][] map;
    static int[][] burn;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());;
            
            map = new char[h][w];
            burn = new int[h][w];
            int x = -1, y = -1;
            Queue<Node> fireQueue = new LinkedList<>();

            for(int i=0;i<h;i++) {
                Arrays.fill(burn[i], Integer.MAX_VALUE);
            }

            for(int i=0;i<h;i++) {
                String str = br.readLine();
                for(int j=0;j<w;j++) {
                    map[i][j] = str.charAt(j);

                    if(map[i][j] == '@') {
                        x = i;
                        y = j;
                        map[i][j] = '.';
                    }
                    if(map[i][j] == '*') {
                        fireQueue.add(new Node(i, j, 0));
                        burn[i][j] = 0;
                    }
                } 
            }

            fireTime(fireQueue);

            int ans = bfs(x, y);

            System.out.println(ans == -1 ? "IMPOSSIBLE" : ans);

        }
    }

    static int bfs(int x, int y) {
        Queue<Node> queue = new LinkedList<>();
        boolean[][] visited = new boolean[h][w];

        queue.add(new Node(x, y, 0));
        visited[0][0] = true;

        while(!queue.isEmpty()) {
            Node now = queue.poll();

            if(now.x == 0 || now.x == h-1 || now.y == 0 || now.y == w-1) return now.t + 1;

            for(int i=0;i<4;i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx < 0 || nx >= h|| ny < 0 || ny >= w) continue;
                if(map[nx][ny] == '#' || visited[nx][ny]) continue;

                if(now.t + 1 < burn[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.add(new Node(nx, ny, now.t + 1));
                }
            }
        }

        return -1;
    }

    static void fireTime(Queue<Node> fireQueue) {
        while(!fireQueue.isEmpty()) {
            Node now = fireQueue.poll();

            for(int i=0;i<4;i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx < 0 || nx >= h || ny < 0 || ny >= w || map[nx][ny] == '#') continue;

                if(burn[nx][ny] > now.t + 1) {
                    burn[nx][ny] = now.t + 1;
                    fireQueue.add(new Node(nx, ny, now.t + 1));
                }
            }
        }
    }
}