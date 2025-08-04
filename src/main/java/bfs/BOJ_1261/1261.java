package bfs.BOJ_1261;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

class Main {
    static class Node{
        int x, y, cnt;
        Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    static int N, M;
    static int[][] map;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        map = new int[M][N];

        for(int i=0;i<M;i++) {
            String str = sc.next();
            for(int j=0;j<N;j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }



        System.out.println(bfs());
    }

    static int bfs() {
        boolean[][] visited = new boolean[M][N];
        Deque<Node> deque = new ArrayDeque<>();

        visited[0][0] = true;
        deque.add(new Node(0, 0, 0));

        while(!deque.isEmpty()) {
            Node now = deque.pollFirst();

            if(now.x == M-1 && now.y == N-1) return now.cnt;

            for(int i=0;i<4;i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx < 0 || nx >= M || ny < 0 || ny >= N) continue;

                if(!visited[nx][ny]) {
                    if(map[nx][ny] == 0) {
                        deque.addFirst(new Node(nx, ny, now.cnt));
                    }
                    if(map[nx][ny] == 1) {
                        deque.addLast(new Node(nx, ny, now.cnt+1));
                    }
                    visited[nx][ny] = true;
                }
            }
        }
        return 0;
    }
}