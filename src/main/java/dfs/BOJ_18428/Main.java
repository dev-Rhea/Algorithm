package dfs.BOJ_18428;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static char[][] map;
    static List<int[]> empty = new ArrayList<>();
    static List<int[]> teacher = new ArrayList<>();
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new char[N][N];

        for(int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                map[i][j] = st.nextToken().charAt(0);
                if(map[i][j] == 'X') empty.add(new int[]{i, j});
                if(map[i][j] == 'T') teacher.add(new int[]{i, j});
            }
        }
        System.out.println(dfs(0,0) ? "YES" : "NO");
    }
    
    static boolean dfs(int idx, int cnt) {
        if(cnt == 3) return bfs();

        for(int i=idx;i<empty.size();i++) {
            int[] e = empty.get(i);
            map[e[0]][e[1]] = 'O';
            if(dfs(i+1, cnt+1)) return true;
            map[e[0]][e[1]] = 'X';
        }
        return false;
    }

    static boolean bfs() {
        for(int[] t : teacher) {
            int tx = t[0];
            int ty = t[1];
            for(int i=0;i<4;i++) {
                int nx = tx;
                int ny = ty;
                while(true) {
                    nx += dx[i];
                    ny += dy[i];

                    if(nx < 0 || nx >= N || ny < 0 || ny >= N) break;

                    if(map[nx][ny] == 'O') break;
                    if(map[nx][ny] == 'S') return false;
                }
            }
        }
        return true;
    }
}