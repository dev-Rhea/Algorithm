package bfs.BOJ_16236;
import java.io.*;
import java.util.*;
import java.util.Queue;

class Main {
    static class Shark implements Comparable<Shark> {
        int x, y, dist;
        Shark(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Shark s) {
            if(this.dist != s.dist) return Integer.compare(this.dist, s.dist);
            else {
                if (this.x != s.x) return Integer.compare(this.x, s.x);
                return Integer.compare(this.y, s.y);
            }
        }
    }

    static int N, sx = 0, sy = 0, mv = 0, cnt = 0, size = 2;
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for(int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] == 9) {
                    sx = i;
                    sy = j;
                    map[i][j] = 0;
                }
            }
        }

        while(true) {
            int temp = bfs();
            if(temp == -1) break;
            mv += temp;
            if(++cnt == size) {
                cnt = 0;
                size++;
            }
        }

        System.out.println(mv);
    }

    static int bfs() {
        Queue<Shark> pq = new PriorityQueue<>();
        boolean[][] visited = new boolean[N][N];

        pq.add(new Shark(sx, sy, 0));
        visited[sx][sy] = true;

        List<Shark> list = new ArrayList<>();
        int min = Integer.MAX_VALUE;

        while(!pq.isEmpty()) {
            Shark now = pq.poll();

            if(now.dist > min) break;

            if(map[now.x][now.y] != 0 && map[now.x][now.y] < size) {
                list.add(new Shark(now.x, now.y, now.dist));
                min = now.dist;
                continue;
            }

            for(int d=0;d<4;d++) {
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];
    
                if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if(visited[nx][ny]) continue;
                if(map[nx][ny] > size) continue;
    
                visited[nx][ny] = true;
                pq.add(new Shark(nx , ny, now.dist+1));
            }
        }
        if(list.isEmpty()) return -1;

        Collections.sort(list);
        Shark shark = list.get(0);

        map[shark.x][shark.y] = 0;
        sx = shark.x;
        sy = shark.y;

        return shark.dist;
    }
}