package dfs.BOJ_18428;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

    static int N;
    static char[][] map;
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
            }
        }

        dfs(0);
        System.out.println("NO");
    }

    static void dfs(int cnt) {
        if(cnt == 3) {
            if(bfs()) {
                System.out.println("YES");
                System.exit(0);
            }
            return;
        }

        // 장애물
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                if(map[i][j] == 'X') {
                    map[i][j] = 'O';
                    dfs(cnt + 1);
                    map[i][j] = 'X';
                }
            }
        }
        
    }

    static boolean bfs() {
        Queue<int[]> queue = new LinkedList<>();
        char[][] copy = new char[N][N];

        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                copy[i][j] = map[i][j];

                if(copy[i][j] == 'T') queue.add(new int[]{i, j});
            }
        }

        while(!queue.isEmpty()) {
            int[] now = queue.poll();

            for(int i=0;i<4;i++) {
                int nx = now[0];
                int ny = now[1];

                while(true) {
                    nx += dx[i];
                    ny += dy[i];

                    if(nx < 0 || nx >= N || ny < 0 || ny >= N) break;

                    if(copy[nx][ny] == 'O') break;

                    if(copy[nx][ny] == 'S') return false;
                }
            }
        }
        return true;
    }
}