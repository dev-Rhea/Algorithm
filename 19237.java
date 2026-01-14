import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static class Shark {
        int n, x, y, d;
        boolean dead;
        Shark(int n, int x, int y, int d) {
            this.n = n;
            this.x = x;
            this.y = y;
            this.d = d;
            this.dead = false;
        }
    }

    static int N, M, k, time;
    static int[][] index;
    static int[][] space;
    static int[][][] sharks;
    static Shark[] list;
    static int[] dx = {0, -1, 1, 0, 0};
    static int[] dy = {0, 0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        time = 0;

        index = new int[N][N];
        space = new int[N][N];
        sharks = new int[M+1][5][5];
        list = new Shark[M+1];

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                int n = Integer.parseInt(st.nextToken());
                if(n != 0) {
                    list[n] = new Shark(n, i, j, 0);
                    index[i][j] = n;
                    space[i][j] = k;
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=M;i++) {
            list[i].d = Integer.parseInt(st.nextToken());
        }

        for(int i=1;i<=M;i++) {
            for(int j=1;j<=4;j++) {
                st = new StringTokenizer(br.readLine());
                for(int l=1;l<=4;l++) {
                    sharks[i][j][l] = Integer.parseInt(st.nextToken());
                }
            }
        }

        while(time <= 1000) {
            if(sharkCheck()) {
                System.out.println(time);
                return;
            }

            if(time == 1000) break;

            move();
            time++;
        }
        System.out.println(-1);
    }

    static boolean sharkCheck() {
        for(int i=2;i<=M;i++) {
            if(!list[i].dead) return false;
        }
        return true;
    }

    static void move() {
        int[][] temp = new int[N][N];

        for(int i=1;i<=M;i++) {
            Shark now = list[i];
            if(now.dead) continue;

            boolean moveCheck = false;

            for(int d=1;d<=4;d++) {
                int dir = sharks[now.n][now.d][d];
                int nx = now.x + dx[dir];
                int ny = now.y + dy[dir];

                if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;

                if(index[nx][ny] == 0) {
                    if(temp[nx][ny] != 0) {
                        now.dead = true;
                        break;
                    }
                    moveCheck = true;
                    now.x = nx;
                    now.y = ny;
                    now.d = dir;
                    temp[nx][ny] = now.n;
                    break;
                }
            }

            if(!moveCheck && !now.dead) {
                for(int d=1;d<=4;d++) {
                    int dir = sharks[now.n][now.d][d];
                    int nx = now.x + dx[dir];
                    int ny = now.y + dy[dir];

                    if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;

                    if(index[nx][ny] == now.n) {
                        now.x = nx;
                        now.y = ny;
                        now.d = dir;
                        break;
                    }
                }
            }
        }

        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                if(space[i][j] > 0) {
                    space[i][j]--;
                    if(space[i][j] == 0) {
                        index[i][j] = 0;
                    }
                }
            }
        }

        for(int i=1;i<=M;i++) {
            Shark now = list[i];
            if(now.dead) continue;
            
            index[now.x][now.y] = now.n;
            space[now.x][now.y] = k;
        }
    }
}