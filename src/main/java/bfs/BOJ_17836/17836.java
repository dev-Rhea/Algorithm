package bfs.BOJ_17836;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static class Node{
        int x, y, t;
        boolean gram;
        Node(int x, int y, int t, boolean gram) {
            this.x = x;
            this.y = y;
            this.t = t;
            this.gram = gram;
        }
    }

    static int N, M, T;
    static int[][] map;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int min = bfs();

        System.out.println(min == -1 ? "Fail" : min);
    }

    static int bfs() {
        boolean[][][] visited = new boolean[N][M][2];
        Queue<Node> queue = new LinkedList<>();

        visited[0][0][0] = true;
        queue.add(new Node(0, 0, 0, false));

        while(!queue.isEmpty()) {
            Node now = queue.poll();

            if(now.x == N - 1 && now.y == M - 1) return now.t;
            if(now.t > T) return -1;

            for(int i=0;i<4;i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

                if(now.gram) {
                    if(visited[nx][ny][1]) continue;

                    queue.add(new Node(nx, ny, now.t + 1, true));
                    visited[nx][ny][1] = true;
                }
                else if(!now.gram) {
                    if(map[nx][ny] == 1) continue;

                    if(visited[nx][ny][0]) continue;

                    if(map[nx][ny] == 0) {
                        visited[nx][ny][0] = true;
                        queue.add(new Node(nx, ny, now.t + 1, false));
                    }
                    else if(map[nx][ny] == 2) {
                        visited[nx][ny][0] = true;
                        queue.add(new Node(nx, ny, now.t + 1, true));
                    }
                }
            }

        }

        return -1;
    }
}