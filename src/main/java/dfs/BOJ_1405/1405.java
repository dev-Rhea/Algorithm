package dfs.BOJ_1405;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.StringTokenizer; // 추가

class Main {
    static int N;
    static double ans = 0.0;
    static double[] per = new double[4];
    static boolean[][] visited = new boolean[30][30];
    
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < 4; i++) {
            per[i] = Double.parseDouble(st.nextToken()) * 0.01;
        }

        visited[15][15] = true;
        dfs(15, 15, 0, 1.0);

        System.out.println(BigDecimal.valueOf(ans).toPlainString());
    }

    static void dfs(int x, int y, int cnt, double sum) {
        if (cnt == N) {
            ans += sum;
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (per[i] == 0) continue;

            int nx = x + dx[i];
            int ny = y + dy[i];

            if (!visited[nx][ny]) {
                visited[nx][ny] = true;
                dfs(nx, ny, cnt + 1, sum * per[i]);
                visited[nx][ny] = false;
            }
        }
    }
}