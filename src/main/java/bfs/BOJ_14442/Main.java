package BOJ_14442;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Node {
        int x, y, dist;
        Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    static int N, M, K;
    static int INF = Integer.MAX_VALUE;
    static int[][] map, breakWall;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        breakWall = new int[N][M];

        for(int i=0;i<N;i++) {
            String str = br.readLine();
            for(int j=0;j<M;j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        for(int i=0;i<N;i++) Arrays.fill(breakWall[i], INF);
        breakWall[0][0] = 0;

        System.out.println(bfs());
    }

    static int bfs() {
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(0, 0, 1));

        while(!queue.isEmpty()) {
            Node now = queue.poll();
            if(now.x == N - 1 && now.y == M - 1) return now.dist;

            for(int i=0;i<4;i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

                int cntK = breakWall[now.x][now.y] + map[nx][ny];

                if(cntK > K) continue;

                if(breakWall[nx][ny] > cntK) {
                    // 더 효율적인 값으로 갱신 (초기값이든, 기존 값이든 cntK 보다 크다면)
                    breakWall[nx][ny] = cntK;
                    queue.add(new Node(nx, ny, now.dist+1));
                }
            }
        }

        return -1;
    }
}
