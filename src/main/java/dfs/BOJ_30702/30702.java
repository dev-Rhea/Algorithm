package dfs.BOJ_30702;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Main {

    static int N, M;
    static char[][] before;
    static char[][] after;
    static char[][] change;
    static boolean[][] visited;
    static boolean possible = true;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] s = br.readLine().split(" ");
        N = Integer.parseInt(s[0]);
        M = Integer.parseInt(s[1]);

        before  = new char[N][M];
        after   = new char[N][M];
        change  = new char[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String st = br.readLine();
            for (int j = 0; j < M; j++) {
                before[i][j] = st.charAt(j);
                change[i][j] = before[i][j];
            }
        }

        for (int i = 0; i < N; i++) {
            String st = br.readLine();
            for (int j = 0; j < M; j++) {
                after[i][j] = st.charAt(j);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (before[i][j] != after[i][j] && !visited[i][j]) {
                    visited[i][j] = true;
                    char beforeColor = before[i][j];
                    char afterColor  = after[i][j];
                    change[i][j] = afterColor;
                    dfs(i, j, beforeColor, afterColor);
                }
            }
        }

        String result = "YES";
        loop:
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (change[i][j] != after[i][j]) {
                    result = "NO";
                    break loop;
                }
            }
        }
        System.out.println(result);
    }

    static void dfs(int x, int y, char beforeColor, char afterColor) {
        
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
            if (visited[nx][ny]) continue;
            if (before[nx][ny] == beforeColor) {
                change[nx][ny] = afterColor;
                visited[nx][ny] = true;
                dfs(nx, ny, beforeColor, afterColor);
            }
        }
    }
}