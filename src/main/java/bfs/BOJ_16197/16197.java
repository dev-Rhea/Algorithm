package bfs.BOJ_16197;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

    static class Node {
        int x1, y1, x2, y2, depth;

        Node(int x1, int y1, int x2, int y2, int depth) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.depth = depth;
        }
    }

    static int N, M;
    static char[][] map;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        int r1 = -1, c1 = -1, r2 = -1, c2 = -1;

        for(int i=0;i<N;i++) {
            String input = br.readLine();
            for(int j=0;j<M;j++) {
                map[i][j] = input.charAt(j);

                if(map[i][j] == 'o') {
                    if(r1 == -1) {
                        r1 = i;
                        c1 = j;
                    }
                    else {
                        r2 = i;
                        c2 = j;
                    }
                }
            }
        }

        int ans = button(r1, c1, r2, c2);

        
        System.out.println(ans);
    }

    static int button(int r1, int c1, int r2, int c2) {
        Queue<Node> queue = new LinkedList<>();
        boolean[][][][] visited = new boolean[N][M][N][M];

        queue.add(new Node(r1, c1, r2, c2, 0));
        visited[r1][c1][r2][c2] = true;

        while(!queue.isEmpty()) {
            Node now = queue.poll();

            if(now.depth >= 10) return -1;

            for(int i=0;i<4;i++) {
                int nx1 = now.x1 + dx[i];
                int ny1 = now.y1 + dy[i];
                int nx2 = now.x2 + dx[i];
                int ny2 = now.y2 + dy[i];

                boolean range1 = (nx1 < 0 || nx1 >= N || ny1 < 0 || ny1 >= M);
                boolean range2 = (nx2 < 0 || nx2 >= N || ny2 < 0 || ny2 >= M);

                if(range1 && range2) continue;

                if(range1 || range2) return now.depth+1;

                if(map[nx1][ny1] == '#') {
                    nx1 = now.x1;
                    ny1 = now.y1;
                }
                if(map[nx2][ny2] == '#') {
                    nx2 = now.x2;
                    ny2 = now.y2;
                }

                if(visited[nx1][ny1][nx2][ny2]) continue;
                visited[nx1][ny1][nx2][ny2] = true;
                queue.add(new Node(nx1, ny1, nx2, ny2, now.depth+1));
            }
        }

        return -1;
    }
}