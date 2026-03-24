package bfs.BOJ_18405;
import java.io.*;
import java.util.*;

class Main {
    static class Virus{
        int n, x, y;
        Virus(int n, int x, int y) {
            this.n = n;
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Queue<Virus> pq = new PriorityQueue<>((v1, v2) -> (v1.n - v2.n));
        int[][] map = new int[N+1][N+1];

        for(int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=N;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] != 0) pq.add(new Virus(map[i][j], i, j));
            }
        }

        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        for(int i=0;i<S;i++) {
            int size = pq.size();
            List<Virus> now = new ArrayList<>(size);
            for(int j=0;j<size;j++) {
                now.add(pq.poll());
            }

            for(Virus v : now) {
                for(int d=0;d<4;d++) {
                    int nx = v.x + dx[d];
                    int ny = v.y + dy[d];
    
                    if(nx < 1 || nx > N || ny < 1 || ny > N) continue;
    
                    if(map[nx][ny] == 0) {
                        map[nx][ny] = v.n;
                        pq.add(new Virus(v.n, nx, ny));
                    }
                }
            }
        }

        System.out.println(map[X][Y]);
    }
}