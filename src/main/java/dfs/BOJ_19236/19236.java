package dfs.BOJ_19236;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static class Fish {
        int x, y, num, dir;
        boolean alive;
        
        Fish(int x, int y, int num, int dir, boolean alive) {
            this.x = x;
            this.y = y;
            this.num = num;
            this.dir = dir;
            this.alive = alive;
        }
    }

    static int ans;
    static int[] dx = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 0, -1, -1, -1, 0, 1, 1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ans = 0;

        int[][] map = new int[4][4];
        Fish[] fishes = new Fish[17];

        for(int i=0;i<4;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<4;j++) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                fishes[a] = new Fish(i, j, a, b, true);
                map[i][j] = a;
            }
        }

        int eatSum = map[0][0];
        int sharkDir = fishes[map[0][0]].dir;
        fishes[map[0][0]].alive = false;
        map[0][0] = -1;

        dfs(0, 0, sharkDir, eatSum, map, fishes);

        System.out.println(ans);
    }

    static void dfs(int sx, int sy, int sd, int eatSum, int[][] map, Fish[] fishes) {
        ans = Math.max(ans, eatSum);

        int[][] copyMap = new int[4][4];
        Fish[] copyFishes = new Fish[17];
        
        for(int i=0;i<4;i++) {
            for(int j=0;j<4;j++) {
                copyMap[i][j] = map[i][j];
            }
        }
        
        for(int i=1;i<=16;i++) {
            if(fishes[i] != null) {
                copyFishes[i] = new Fish(fishes[i].x, fishes[i].y, fishes[i].num, 
                                         fishes[i].dir, fishes[i].alive);
            }
        }

        moveFish(copyMap, copyFishes, sx, sy);

        for(int dist=1;dist<=3;dist++) {
            int nx = sx + dx[sd] * dist;
            int ny = sy + dy[sd] * dist;

            if(nx < 0 || nx >= 4 || ny < 0 || ny >= 4) continue;
            if(copyMap[nx][ny] <= 0) continue;

            int fishNum = copyMap[nx][ny];
            
            int[][] nextMap = new int[4][4];
            Fish[] nextFishes = new Fish[17];
            
            for(int i=0;i<4;i++) {
                for(int j=0;j<4;j++) {
                    nextMap[i][j] = copyMap[i][j];
                }
            }
            
            for(int i=1;i<=16;i++) {
                if(copyFishes[i] != null) {
                    nextFishes[i] = new Fish(copyFishes[i].x, copyFishes[i].y, 
                                             copyFishes[i].num, copyFishes[i].dir, 
                                             copyFishes[i].alive);
                }
            }

            // 상어 이동 처리
            nextMap[sx][sy] = 0;
            nextMap[nx][ny] = -1;
            int nextDir = nextFishes[fishNum].dir;
            nextFishes[fishNum].alive = false;

            dfs(nx, ny, nextDir, eatSum + fishNum, nextMap, nextFishes);
        }
    }

    static void moveFish(int[][] map, Fish[] fishes, int sx, int sy) {
        for(int i=1;i<=16;i++) {
            if(fishes[i] == null || !fishes[i].alive) continue;

            int x = fishes[i].x;
            int y = fishes[i].y;
            int dir = fishes[i].dir;
            
            for(int d=0;d<8;d++) {
                int nd = dir + d;
                if(nd > 8) nd -= 8;

                int nx = x + dx[nd];
                int ny = y + dy[nd];

                if(nx < 0 || nx >= 4 || ny < 0 || ny >= 4) continue;
                if(map[nx][ny] == -1) continue;

                if(map[nx][ny] == 0) {
                    // 빈 칸으로 이동
                    map[x][y] = 0;
                    map[nx][ny] = i;
                    fishes[i].x = nx;
                    fishes[i].y = ny;
                    fishes[i].dir = nd;
                } else {
                    // 다른 물고기와 위치 교환
                    int target = map[nx][ny];
                    map[x][y] = target;
                    map[nx][ny] = i;
                    
                    fishes[target].x = x;
                    fishes[target].y = y;
                    fishes[i].x = nx;
                    fishes[i].y = ny;
                    fishes[i].dir = nd;
                }
                break;
            }
        }
    }
}