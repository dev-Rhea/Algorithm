package dfs.BOJ_12946;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    
    static int N;
    static char[][] map;
    static int[][] color;
    static int[] dy = {1, 0, -1, 0, 1, -1};
    static int[] dx = {0, 1, 0, -1, -1, 1};
    static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        color = new int[N][N];
        max = 0;

        for(int i=0;i<N;i++) {
            String str = br.readLine();
            for(int j=0;j<N;j++) {
                map[i][j] = str.charAt(j);
            }
        }

        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                if(map[i][j] == 'X' && color[i][j] == 0) dfs(i, j, -1);
            }
        }
        
        System.out.println(max);
    }

    static void dfs(int y, int x, int c) {
        color[y][x] = c;
        max = Math.max(max, 1);

        for(int i=0;i<6;i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            
            if(ny < 0 || ny >= N || nx < 0 || nx >= N || map[ny][nx] == '-') continue;
            
            if(color[ny][nx] == 0) {
                max = 2;
                dfs(ny, nx, -c);
            }
            else if(color[ny][nx] == c) {
                System.out.println(3);
                System.exit(0);
            }
        }
    }
}