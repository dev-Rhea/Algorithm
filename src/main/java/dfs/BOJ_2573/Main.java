package dfs.BOJ_2573;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static class Ice {
        int x, y, h;
        Ice(int x, int y, int h) {
            this.x = x;
            this.y = y;
            this.h = h;
        }
    }

    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static List<Ice> ice;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];
        ice = new ArrayList<>();

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] > 0) ice.add(new Ice(i, j, map[i][j]));
                visited[i][j] = true;
            }
        }

        int year = 0;

        while(!ice.isEmpty()) {
            year++;
            melting();
            reMap();

            if(!isValid()) {
                sb.append(year).append("\n");
                System.out.println(sb);
                return;
            }
        }
        sb.append(0).append("\n");
        System.out.println(sb);
    }

    static void melting() {
        for(Ice i : ice) {
            for(int j=0;j<4;j++) {
                int nx = i.x + dx[j];
                int ny = i.y + dy[j];

                if(map[nx][ny] == 0) i.h--;
            }
        }
    }

    static void reMap() {
        // 빙산이 녹은 좌표들을 먼저 0으로 처리
        for (Ice getIce : ice) {
            if (getIce.h <= 0) {
                map[getIce.x][getIce.y] = 0;
            } else {
                visited[getIce.x][getIce.y] = false;
            }
        }
        // 높이 0 이하인 빙산 객체 제거
        ice.removeIf(i -> i.h <= 0);
    }

    static int dfs(int x, int y) {
        visited[x][y] = true;
        int cnt = 1;
        for(int i=0;i<4;i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(visited[nx][ny]) continue;
            cnt += dfs(nx, ny);
        }
        return cnt;
    }

    static boolean isValid() {
        if(!ice.isEmpty() && dfs(ice.get(0).x, ice.get(0).y) != ice.size()) return false;
        return true;
    }
}
