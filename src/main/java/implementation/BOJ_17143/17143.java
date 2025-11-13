package implementation.BOJ_17143;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {
    static class Shark {
        int r, c, s, d, z;
        boolean fished = false;
        Shark(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }

    static int R, C, M, cnt;
    static Shark[][] map;
    static List<Shark> sharks;
    static int[] dy = {0, -1, 1, 0, 0};
    static int[] dx = {0, 0, 0, 1, -1}; 

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cnt = 0;
        map = new Shark[R+1][C+1];
        sharks = new ArrayList<>();

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            if(d <= 2) s %= (2*R-2);
            else s %= (2*C-2);

            Shark shark = new Shark(r, c, s, d, z);
            sharks.add(shark);
            map[r][c] = shark;
        }

        for(int i=1;i<=C;i++) {
            fishing(i);
            move();
        }

        System.out.println(cnt);
    }

    static void fishing(int now) {
        for(int i=1;i<=R;i++) {
            if(map[i][now] != null) {
                Shark shark = map[i][now];
                cnt += shark.z;
                shark.fished = true;
                map[i][now] = null;
                break;
            }
        }
    }

    static void move() {
        map = new Shark[R+1][C+1];
        List<Shark> moved = new ArrayList<>();

        for(Shark sh : sharks) {
            if(sh.fished) continue;

            int nr = sh.r;
            int nc = sh.c;
            int nd = sh.d;

            if(nd <= 2) {
                int cycle = 2*R-2;
                int mv = sh.s % cycle;

                for(int i=0;i<mv;i++) {
                    nr += dy[nd];
                    if(nr < 1 || nr > R) {
                        nd = (nd==1) ? 2 : 1;
                        nr += 2 * dy[nd];
                    }
                }
            }
            else {
                int cycle = 2*C-2;
                int mv = sh.s % cycle;
                
                for(int i=0;i<mv;i++) {
                    nc += dx[nd];
                    if(nc < 1 || nc > C) {
                        nd = (nd==3) ? 4 : 3;
                        nc += 2 * dx[nd];
                    }
                }
            }

            sh.r = nr;
            sh.c = nc;
            sh.d = nd;

            moved.add(sh);
        }

        sharks = moved;

        for(Shark sh : sharks) {
            if(map[sh.r][sh.c] != null) {
                if(map[sh.r][sh.c].z < sh.z) {
                    map[sh.r][sh.c].fished = true;
                    map[sh.r][sh.c] = sh;
                }
                else {
                    sh.fished = true;
                }
            }
            else {
                map[sh.r][sh.c] = sh;
            }
        }
            
        moved = new ArrayList<>();
        for(Shark s : sharks) {
            if(!s.fished) moved.add(s);
        }
        sharks = moved;
    }
}