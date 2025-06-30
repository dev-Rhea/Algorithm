package bfs.BOJ_1600;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

    static class Node {
        int x, y, hCnt, mCnt;
        Node(int x, int y, int hCnt, int mCnt) {
            this.x = x;
            this.y = y;
            this.hCnt = hCnt;
            this.mCnt = mCnt;
        }
    }

    static int K, W, H;
    static int[][] map;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};
    static int[] hy = {2, 2, 1, -1, -2, -2, -1, 1};
    static int[] hx = {-1, 1, 2, 2, 1, -1, -2, -2};
    static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][W];
        visited = new boolean[H][W][K+1];

        for(int i=0;i<H;i++) {
            String str = br.readLine();
            if(str != null) {
                st = new StringTokenizer(str);
                for(int j=0;j<W;j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());

                    if(map[i][j] == 1) {
                        for(int k=0;k<=K;k++) {
                            visited[i][j][k] = true;
                        }
                    }
                }
            }
        }

        System.out.println(bfs());
    }

    static int bfs() {
        Queue<Node> queue = new LinkedList<>();

        queue.add(new Node(0, 0, 0, 0));
        visited[0][0][0] = true;

        while(!queue.isEmpty()) {
            Node now = queue.poll();

            if(now.x == H - 1 && now.y == W - 1) return now.mCnt;

            moving(dx, dy, now, now.hCnt, queue);

            if(now.hCnt < K) moving(hx, hy, now, now.hCnt + 1, queue);
        }
        return -1;
    }

    static void moving(int[] mx, int[] my, Node now, int newHCnt, Queue<Node> queue) {
        for(int i=0;i<mx.length;i++) {
            int nx = now.x + mx[i];
            int ny = now.y + my[i];

            if(nx >= 0 && ny >= 0 && nx < H && ny < W && !visited[nx][ny][newHCnt]) {
                visited[nx][ny][newHCnt] = true;
                queue.add(new Node(nx, ny, newHCnt, now.mCnt + 1));
            }
        }
    }
}