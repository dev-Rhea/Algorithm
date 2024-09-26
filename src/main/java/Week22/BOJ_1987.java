package Week22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1987 {
    static int R, C;
    static int[][] map;
    static boolean[] visited;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j) - 'A';
            }
        }
        visited = new boolean[26];
        DFS(0, 0, 0);

        System.out.println(ans);
    }

    public static void DFS(int x, int y, int cnt) {
        if(visited[map[x][y]]) {
            ans = Math.max(ans, cnt);
            return;
        }

        visited[map[x][y]] = true;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && nx < R && ny < C) {
                DFS(nx, ny, cnt + 1);
            }
        }
        visited[map[x][y]] = false;
    }
}
