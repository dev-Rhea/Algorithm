package dfs.BOJ_19236;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static class Fish {
        int x, y, n, d;
        Fish(int x, int y, int n, int d) {
            this.x = x;
            this.y = y;
            this.n = n;
            this.d = d;
        }
    }

    static int ans = 0;
    static int[] dx = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 0, -1, -1, -1, 0, 1, 1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int[][] map = new int[4][4];
        int[][] dir = new int[4][4];

        for(int i=0;i<4;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<4;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dir[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int num = map[0][0];
        map[0][0] = -1; // 상어

        dfs(map, dir, 0, 0, num);
        
        System.out.println(ans);
    }

    static void dfs(int[][] map, int[][] dir, int sx, int sy, int num) {
        ans = Math.max(ans, num);

        int[][] mv = copy(map);
        int[][] md = copy(dir);
        moveFish(mv, md, sx, sy);

        int d = md[sx][sy];

        for(int k=1;k<=3;k++) {
            int nx = sx + dx[d] * k;
            int ny = sy + dy[d] * k;

            if(nx < 0 || nx >= 4 || ny < 0 || ny >= 4) break;
            if(mv[nx][ny] == 0) continue;

            int[][] cm = copy(mv);
            int[][] cd = copy(md);

            int eat = cm[nx][ny];
            int nd = cd[nx][ny];

            cm[sx][sy] = 0;
            cd[sx][sy] = 0;
            cm[nx][ny] = -1;
            cd[nx][ny] = nd;

            dfs(cm, cd, nx, ny, num + eat);
        }

    }

    static void moveFish(int[][] map, int[][] dir, int sx, int sy) {
        for(int n=1;n<=16;n++) {
            int fx = -1, fy = -1;
            outer: 
            for(int i=0;i<4;i++) {
                for(int j=0;j<4;j++) {
                    if(map[i][j] == n) {
                        fx = i;
                        fy = j;
                        break outer;
                    }
                }
            }
            if(fx == -1) continue;

            int d = dir[fx][fy];
            for(int i=0;i<8;i++) {
                int nd = (d + i - 1 + 8) % 8 + 1;
                int nx = fx + dx[nd];
                int ny = fy + dy[nd];

                if(nx < 0 || nx >= 4 || ny < 0 || ny >= 4 || map[nx][ny] == -1) continue;

                dir[fx][fy] = nd;

                int tn = map[fx][fy];
                map[fx][fy] = map[nx][ny];
                map[nx][ny] = tn;

                int td = dir[fx][fy];
                dir[fx][fy] = dir[nx][ny];
                dir[nx][ny] = td;

                break;
            }
        }
    }

    static int[][] copy(int[][] arr) {
        int[][] temp = new int[4][4];
        
        for(int i=0;i<4;i++) {
            temp[i] = arr[i].clone();
        }

        return temp;
    }
}