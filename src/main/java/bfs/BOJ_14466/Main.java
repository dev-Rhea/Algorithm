package bfs.BOJ_14466;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static class Bridge {
        int x, y;
        Bridge(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N, K, R;
    static int[][] cow;
    static int[][] road;        // 길 정보 비트마스크 저장
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = { 0, 0,-1, 1}; 
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        cow = new int[K+1][2];
        road = new int[N+1][N+1];

        // 길 정보를 비트마스크로 저장
        for(int i=1; i<=R; i++) {
            st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());

            int dir = getDir(r1, c1, r2, c2);
            road[r1][c1] |= (1 << dir);
            road[r2][c2] |= (1 << opposite(dir));
        }

        for(int i=1; i<=K; i++) {
            st = new StringTokenizer(br.readLine());
            cow[i][0] = Integer.parseInt(st.nextToken());
            cow[i][1] = Integer.parseInt(st.nextToken());
        }

        for(int i=1; i<=K; i++) {
            bfs(i, cow[i][0], cow[i][1]);
        }

        System.out.println(cnt);
    }

    static void bfs(int idx, int x, int y) {
        boolean[][] visited = new boolean[N+1][N+1];
        Queue<Bridge> queue = new ArrayDeque<>();

        visited[x][y] = true;
        queue.add(new Bridge(x, y));

        while(!queue.isEmpty()) {
            Bridge now = queue.poll();

            for(int i=0; i<4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx < 1 || nx > N || ny < 1 || ny > N) continue;

                if((road[now.x][now.y] & (1 << i)) != 0) continue;

                if(!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.add(new Bridge(nx, ny));
                }
            }
        }

        for(int i=idx+1; i<=K; i++) {
            if(!visited[cow[i][0]][cow[i][1]]) cnt++;
        }
    }

    static int getDir(int r1, int c1, int r2, int c2) {
        if(r2 == r1-1 && c2 == c1) return 0;
        if(r2 == r1+1 && c2 == c1) return 1;
        if(r2 == r1   && c2 == c1-1) return 2;
        return 3;
    }
    static int opposite(int d) {
        if(d == 0) return 1;
        if(d == 1) return 0;
        if(d == 2) return 3;
        return 2;
    }
}
