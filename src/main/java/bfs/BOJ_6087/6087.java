package bfs.BOJ_6087;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static class Node implements Comparable<Node> {
        int x, y, dir, cnt;
        Node(int x, int y, int dir, int cnt) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node o) {
            return this.cnt - o.cnt;
        }
    }

    static int H, W;
    static char[][] map;
    static int[][] dist;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};
    static List<int[]> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new char[H][W];
        dist = new int[H][W];

        for(int i=0;i<H;i++) {
            map[i] = br.readLine().toCharArray();
            for(int j=0;j<W;j++) {
                if(map[i][j] == 'C') list.add(new int[]{i, j});
            }
        }

        bfs();
    }

    static void bfs() {
        int[] start = list.get(0);
        int[] end = list.get(1);
        Queue<Node> pq = new PriorityQueue<>();
        int[][][] visited = new int[H][W][4]; // 어느 방향으로 갔는지 함께 저장 

        // 각 행 초기화
        for(int[][] d : visited) {
            for(int[] row : d) {
                Arrays.fill(row, Integer.MAX_VALUE);
            }
        }

        // 
        for(int i=0;i<4;i++) {
            visited[start[0]][start[1]][i] = 0; // 시작점 거울갯수 초기화
            pq.add(new Node(start[0], start[1], i, 0));
        }

        while(!pq.isEmpty()) {
            Node now = pq.poll();

            if(now.x == end[0] && now.y == end[1]) {
                System.out.println(now.cnt);
                return;
            }

            for(int i=0;i<4;i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                int nc = now.cnt;

                if(now.dir != i) nc++;

                while(nx >= 0 && ny >= 0 && nx < H && ny < W && map[nx][ny] != '*') {
                    if(visited[nx][ny][i] > nc) {
                        visited[nx][ny][i] = nc;
                        pq.add(new Node(nx, ny, i, nc));
                    }
                    else break;

                    nx += dx[i];
                    ny += dy[i];
                }
            }
        }
    }
}