package dfs.BOJ_15683;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M, cnt, cctvCnt, ans;
    static int[][] map;
    static int[][] move = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}}; 
    static List<int[]> cctv = new ArrayList<>();

    public static void main(String[] args) throws IOException { 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cnt = 0;
        map = new int[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] == 0) cnt++;
                else if(map[i][j] != 6) cctv.add(new int[]{i, j});
            }
        }

        cctvCnt = cctv.size();
        ans = cnt;

        dfs(0, 0L);

        System.out.println(ans);
    }

    static void dfs(int idx, long visit) {
        if(idx >= cctvCnt) {
            ans = Math.min(ans, cnt - Long.bitCount(visit));
            return;
        }

        int x = cctv.get(idx)[0];
        int y = cctv.get(idx)[1];
        long newVisit;

        if(map[x][y] == 1) {
            for(int i = 0; i < 4; i++) {
                newVisit = check(x, y, i, visit);
                dfs(idx + 1, newVisit);
            }
        }
        else if(map[x][y] == 2) {
            newVisit = check(x, y, 1, visit);
            newVisit = check(x, y, 3, newVisit);
            dfs(idx + 1, newVisit);

            newVisit = check(x, y, 0, visit);
            newVisit = check(x, y, 2, newVisit);
            dfs(idx + 1, newVisit);
        }
        else if(map[x][y] == 3) {
            for(int i = 0; i < 4; i++) {
                newVisit = check(x, y, i, visit);
                newVisit = check(x, y, (i + 1) % 4, newVisit);
                dfs(idx + 1, newVisit);
            }
        }
        else if(map[x][y] == 4) {
            for(int i = 0; i < 4; i++) {
                newVisit = check(x, y, i, visit);
                newVisit = check(x, y, (i + 1) % 4, newVisit);
                newVisit = check(x, y, (i + 2) % 4, newVisit);
                dfs(idx + 1, newVisit);
            }
        }
        else if(map[x][y] == 5) {
            newVisit = visit;
            for(int i = 0; i < 4; i++) {
                newVisit = check(x, y, i, newVisit);
            }
            dfs(idx + 1, newVisit);
        }
    }

    static long check(int x, int y, int dir, long visit) {
        int nx = x;
        int ny = y;

        while(true) {
            nx += move[dir][0];
            ny += move[dir][1];

            if(nx < 0 || nx >= N || ny < 0 || ny >= M || map[nx][ny] == 6) break;

            if(map[nx][ny] == 0 && (visit & (1L << (nx * M + ny))) == 0) {
                visit |= 1L << (nx * M + ny); 
            }
        }
        return visit;
    }
}