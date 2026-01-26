package dfs.BOJ_17265;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int N;
    static int min = Integer.MAX_VALUE;
    static int max = Integer.MIN_VALUE;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new char[N][N];

        for(int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                map[i][j] = st.nextToken().charAt(0);
            }
        }

        dfs(0, 0, map[0][0] - '0');

        System.out.println(max+" "+min);
    }

    static void dfs(int x, int y, int now) {
        // 우, 하
        int[] dx = {0, 1};
        int[] dy = {1, 0};

        if(x == N-1 && y == N-1) {
            max = Math.max(max, now);
            min = Math.min(min, now);
            return;
        }


        for(int i=0;i<2;i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= N || ny >= N) continue;
            char op = map[nx][ny];

            for(int j=0;j<2;j++) {
                int nnx = nx + dx[j];
                int nny = ny + dy[j];

                if(nnx >= N || nny >= N) continue;

                int num = map[nnx][nny] - '0';
                int value = cal(now, num, op);

                dfs(nnx, nny, value);
            }
        }
    }

    static int cal(int sum, int n, char c) {
        if(c == '+') return sum + n;
        else if(c == '-') return sum - n;
        else if(c == '*') return sum * n;
        return 0;
    }
}