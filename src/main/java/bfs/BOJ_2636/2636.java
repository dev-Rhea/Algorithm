package bfs.BOJ_2636;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static class Cheese {
        int x, y;
        Cheese(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N, M, time;
    static int[][] map;
    static int[] cnt = new int[10000];
    static List<Cheese> list;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        time = 0;

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(true) {
            outer();

            list = new ArrayList<>();
            find();

            if(list.size() == 0) break;
            cnt[time] = list.size();
            melting();
        }

        System.out.println(time);
        System.out.println(cnt[time-1]);
    }

    static void find() {
        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                if(map[i][j] == 1) {
                    for(int d=0;d<4;d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];

                        if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                        if(map[nx][ny] == 2) {
                            list.add(new Cheese(i, j));
                            break;
                        }
                    }
                }
            }
        }
    }

    static void outer() {
        boolean[][] visited = new boolean[N][M];
        Queue<Cheese> queue = new LinkedList<>();
        queue.add(new Cheese(0, 0));
        visited[0][0] = true;
        map[0][0] = 2;

        while(!queue.isEmpty()) {
            Cheese now = queue.poll();

            for(int d=0;d<4;d++) {
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];

                if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if(visited[nx][ny] || map[nx][ny] == 1) continue;

                visited[nx][ny] = true;
                map[nx][ny] = 2;
                queue.add(new Cheese(nx, ny));
            }
        }
    }

    static void melting() {
        for(Cheese c : list) {
            map[c.x][c.y] = 0;
        }
        time++;
    }
}