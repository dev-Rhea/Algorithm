package dfs.BOJ_15683;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {
    static class CCTV {
        int x;
        int y;
        int type;

        CCTV(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }
    static int N, M, cnt;
    static int[][] map;
    static boolean[][] visited;
    static int[][] move = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static int[][] cctvDir = {
        {},
        {0},
        {1, 3},
        {0, 1},
        {3, 0, 1},
        {0, 1, 2, 3}
    };
    static List<CCTV> cctv = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cnt = Integer.MAX_VALUE;

        map = new int[N][M];
        visited = new boolean[N][M];

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] > 0 && map[i][j] < 6) cctv.add(new CCTV(i, j, map[i][j]));
            }
        }

        dfs(0, map);

        System.out.println(cnt);
    }

    static void dfs(int idx, int[][] map) {
        if(idx == cctv.size()) {
            cnt = Math.min(cnt, empty(map));
            return;
        }

        int x = cctv.get(idx).x;
        int y = cctv.get(idx).y;
        int num = cctv.get(idx).type;

        for(int i=0;i<4;i++) {
            int[][] cctvMap = copy(map);

            for(int d : cctvDir[num]) {
                int dir = (d + i) % 4;
                int nx = x;
                int ny = y;

                while(true) {
                    nx += move[dir][0];
                    ny += move[dir][1];

                    if(nx < 0 || nx >= N || ny < 0 || ny >= M || map[nx][ny] == 6) break;

                    cctvMap[nx][ny] = -1;
                }
            }
            dfs(idx+1, cctvMap);
        }
    }

    static int empty(int[][] map) {
        int area = 0;
        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                if(map[i][j] == 0) area++;
            }
        }
        return area;
    }

    static int[][] copy(int[][] map) {
        int[][] newMap = new int[N][M];

        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                newMap[i][j] = map[i][j];
            }
        }
        return newMap;
    }
}