package implementation.BOJ_23288;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

    static class Dice {
        int x, y, d;
        int[] num;
        Dice(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.num = new int[]{1, 2, 3, 4, 5, 6};
        }

        void east() {
            int temp = num[0];
            num[0] = num[3];
            num[3] = num[5];
            num[5] = num[2];
            num[2] = temp;
        }

        void west() {
            int temp = num[0];
            num[0] = num[2];
            num[2] = num[5];
            num[5] = num[3];
            num[3] = temp;
        }

        void south() {
            int temp = num[0];
            num[0] = num[1];
            num[1] = num[5];
            num[5] = num[4];
            num[4] = temp;
        }

        void north() {
            int temp = num[0];
            num[0] = num[4];
            num[4] = num[5];
            num[5] = num[1];
            num[1] = temp;
        }

        void roll(int dir) {
            switch(dir) {
                case 0: east(); break;
                case 1: south(); break;
                case 2: west(); break;
                case 3: north(); break;
            }
        }

        int getBottom() {
            return num[5];
        }
    }

    static int N, M, K;
    static int[][] map;
    static int[][] group;
    static int[] cnt = new int[401];
    static int g = 1;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N+1][M+1];
        group = new int[N+1][M+1];

        for(int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=M;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= M; j++) {
                if(group[i][j] == 0) {
                    cal(i, j);
                    g++;
                }
            }
        }

        Dice dice = new Dice(1, 1, 0);
        int total = 0;

        for(int i=0;i<K;i++) {
            int nx = dice.x + dx[dice.d];
            int ny = dice.y + dy[dice.d];

            if(nx < 1 || nx > N || ny < 1 || ny > M) {
                dice.d = (dice.d + 2) % 4;
                nx = dice.x + dx[dice.d];
                ny = dice.y + dy[dice.d];
            }

            dice.x = nx;
            dice.y = ny;
            dice.roll(dice.d);

            int score = map[nx][ny] * cnt[group[nx][ny]];
            total += score;

            int A = dice.getBottom();
            int B = map[nx][ny];

            if(A > B) dice.d = (dice.d + 1) % 4;
            else if(A < B) dice.d = (dice.d + 3) % 4; 
        }
        System.out.println(total);
    }

    static void cal(int x, int y) {
        Queue<int[]> move = new LinkedList<>();
        group[x][y] = g;
        cnt[g]++;
        move.add(new int[]{x, y});
        int temp = map[x][y];

        while(!move.isEmpty()) {
            int[] now = move.poll();
            
            for(int i=0;i<4;i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if(nx >= 1 && nx <= N && ny >= 1 && ny <= M && 
                   group[nx][ny] == 0 && map[nx][ny] == temp) {
                    group[nx][ny] = g;
                    cnt[g]++;
                    move.add(new int[]{nx, ny});
                } 
            } 
        }
    }
}