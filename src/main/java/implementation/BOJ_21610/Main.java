package implementation.BOJ_21610;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Node {
        int x, y;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N, M;
    static int[][] map;
    static Queue<Node> queue;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N+1][N+1];
        queue = new LinkedList<>();
        visited = new boolean[N+1][N+1];

        for(int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=N;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        queue.add(new Node(N, 1));
        queue.add(new Node(N, 2));
        queue.add(new Node(N-1, 1));
        queue.add(new Node(N-1, 2));

        while(M-- >0) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            moveCloud(d, s);
            copyWater();
            makeCloud();
        }
        
        int ans = 0;

        for(int i=1;i<=N;i++) {
            for(int j=1;j<=N;j++) {
                ans += map[i][j];
            }
        }

        System.out.println(ans);
    }

    static void moveCloud(int d, int s) {
        // ←, ↖, ↑, ↗, →, ↘, ↓, ↙
        int[] dx = {0, 0, -1, -1, -1, 0, 1, 1, 1};
        int[] dy = {0, -1, -1, 0, 1, 1, 1, 0, -1};

        for(Node node : queue) {
            node.x = ((node.x - 1 + dx[d] * (s % N)) % N + N) % N + 1;
            node.y = ((node.y - 1 + dy[d] * (s % N)) % N + N) % N + 1;
            map[node.x][node.y]++;
        }
    }

    static void copyWater() {
        int[] dx = {-1, -1, 1, 1};
        int[] dy = {-1, 1, 1, -1};

        while(!queue.isEmpty()) {
            Node now = queue.poll();
            int cnt = 0;

            visited[now.x][now.y] = true;

            for(int i=0;i<4;i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx > 0 && nx <= N && ny > 0 && ny <= N) {
                    if(map[nx][ny] >= 1) cnt++;
                }
            }
            map[now.x][now.y] += cnt;
        }
    }

    static void makeCloud() {
        for(int i=1;i<=N;i++) {
            for(int j=1;j<=N;j++) {
                if(!visited[i][j] && map[i][j] >= 2) {
                    map[i][j] -=2;
                    queue.add(new Node(i, j));
                }
            }
        }
        visited = new boolean[N+1][N+1];
    }
}
