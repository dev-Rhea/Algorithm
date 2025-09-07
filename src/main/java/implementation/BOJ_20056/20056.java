package implementation.BOJ_20056;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static class Fire{
        int r, c, m, s, d;
        Fire(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }

    static int N, M, K;
    static ArrayList<Fire>[][] map;
    static Queue<Fire> queue;
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new ArrayList[N+1][N+1];
        for(int i=1;i<=N;i++) {
            for(int j=1;j<=N;j++) {
                map[i][j] = new ArrayList<>();
            }
        }
        
        queue = new LinkedList<>();

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int fm = Integer.parseInt(st.nextToken());
            int sp = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());

            queue.add(new Fire(x, y, fm, sp, dir));
        }

        while(K-- > 0) {
            move();
        }

        int sum = 0;
        for(Fire f : queue) {
            sum += f.m;
        }
        System.out.println(sum);
    }

    static void move() {
        for(int i=1;i<=N;i++) {
            for(int j=1;j<=N;j++) {
                map[i][j].clear();
            }
        }

        while(!queue.isEmpty()) {
            Fire fire = queue.poll();

            int nx = fire.r + dy[fire.d] * fire.s;
            int ny = fire.c + dx[fire.d] * fire.s;

            nx = ((nx - 1) % N + N) % N + 1;
            ny = ((ny - 1) % N + N) % N + 1;

            map[nx][ny].add(new Fire(nx, ny, fire.m, fire.s, fire.d));
        }

        for(int i=1;i<=N;i++) {
            for(int j=1;j<=N;j++) {
                if(map[i][j].size() >= 2) {
                    distribute(i, j, map[i][j].size());
                } else if(map[i][j].size() == 1) {
                    queue.add(map[i][j].get(0));
                }
            }
        }
    }

    static void distribute(int x, int y, int cnt) {
        int sumM = 0;
        int sumS = 0;
        boolean odd = true;
        boolean even = true;

        for(Fire fire : map[x][y]) {
            sumM += fire.m;
            sumS += fire.s;

            if(fire.d % 2 == 0) odd = false;
            else even = false;
        }

        int newM = sumM / 5;
        int newS = sumS / cnt;
        
        if(newM > 0) {
            if(even || odd) {
                for(int i=0;i<8;i+=2) {
                    queue.add(new Fire(x, y, newM, newS, i));
                }
            }
            else {
                for(int i=1;i<8;i+=2) {
                    queue.add(new Fire(x, y, newM, newS, i));
                }
            }
        }
    }
}