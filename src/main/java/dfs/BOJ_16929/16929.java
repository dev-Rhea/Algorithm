package dfs.BOJ_16929;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int N, M;
    static char[][] map;
    static int[] cnt = new int[26];
    static boolean[][] visited;

    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        visited = new boolean[N][M];

        for(int i=0;i<N;i++) {
            String input = br.readLine();
            for(int j=0;j<M;j++) {
                map[i][j] = input.charAt(j);
            }
        }

        boolean cycle = false;

        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                if(!visited[i][j]) {
                    if(dfs(i, j, -1, -1, map[i][j])) {
                        cycle = true;
                        break;
                    }
                }
            }
            if(cycle) break;
        }

        System.out.println(cycle ? "Yes" : "No");
    }

    static boolean dfs(int x, int y, int sx, int sy, char color) {

        visited[x][y] = true;

        for(int i=0;i<4;i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

            if(map[nx][ny] != color) continue;

            if(nx == sx && ny == sy) continue;

            if(visited[nx][ny]) return true;

            if(dfs(nx, ny, x, y, color)) return true;
        }

        return false;
    }
}