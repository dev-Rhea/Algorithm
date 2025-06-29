package BOJ_14442;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

    static class Node{
        int x, y, wall, move;
        Node(int x, int y, int wall, int move) {
            this.x = x;
            this.y = y;
            this.wall = wall;
            this.move = move;
        }
    }

    static int N, M, K;
    static int[][] map;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for(int i=0;i<N;i++) {
            String str = br.readLine();
            for(int j=0;j<M;j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        System.out.println(bfs());
    }

    static int bfs() {
        boolean[][][] visited = new boolean[N][M][K+1];
        Queue<Node> queue = new LinkedList<>();

        visited[0][0][0] = true;
        queue.add(new Node(0, 0, 0, 1));

        while(!queue.isEmpty()) {
            Node now = queue.poll();
            int cnt = now.move;
            int w = now.wall;
            if(now.x == N - 1 && now.y == M - 1) return cnt;

            for(int i=0;i<4;i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

                if(map[nx][ny] == 0) {
                    if(visited[nx][ny][w]) continue;
                    visited[nx][ny][w] = true;
                    queue.add(new Node(nx, ny, w, cnt+1));
                }
                if(map[nx][ny] == 1) {
                    if(w >= K) continue;
                    if(visited[nx][ny][w+1]) continue;
                    visited[nx][ny][w+1] = true;
                    queue.add(new Node(nx, ny, w+1, cnt+1));
                }
            }
        }

        return -1;
    }
}