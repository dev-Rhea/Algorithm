package implementation.BOJ_14499;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int[] dx = {0, 0, 0, -1, 1};
    static int[] dy = {0, 1, -1, 0, 0};
    static int N, M, K;
    static int[][] map;
    static int[] cmd;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        cmd = new int[K];

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<K;i++) {
            cmd[i] = Integer.parseInt(st.nextToken());
        }

        move(x, y);
    }

    static void move(int x, int y) {
        int[] dice = new int[6];

        for(int c : cmd) {
            int nx = x + dx[c];
            int ny = y + dy[c];

            if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

            x = nx;
            y = ny;

            int temp;
            switch(c) {
                case 1:
                    temp = dice[0];
                    dice[0] = dice[3]; // 위 = 서
                    dice[3] = dice[5]; // 서 = 아래
                    dice[5] = dice[2]; // 아래 = 동
                    dice[2] = temp;    // 동 = 위
                    break;
                case 2:
                    temp = dice[0];
                    dice[0] = dice[2]; // 위 = 동
                    dice[2] = dice[5]; // 동 = 아래
                    dice[5] = dice[3]; // 아래 = 서
                    dice[3] = temp;    // 서 = 위
                    break;
                case 3:
                    temp = dice[0];
                    dice[0] = dice[4]; // 위 = 남
                    dice[4] = dice[5]; // 남 = 아래
                    dice[5] = dice[1]; // 아래 = 북
                    dice[1] = temp;    // 북 = 위
                    break;
                case 4:
                    temp = dice[0];
                    dice[0] = dice[1]; // 위 = 북
                    dice[1] = dice[5]; // 북 = 아래
                    dice[5] = dice[4]; // 아래 = 남
                    dice[4] = temp;    // 남 = 위
                    break;
            }

            if(map[x][y] == 0) {
                map[x][y] = dice[5]; // 주사위 아래면을 칸에 복사
            } else {
                dice[5] = map[x][y]; // 칸의 값을 주사위 아래면에 복사
                map[x][y] = 0;       // 칸을 0으로 변경
            }
            
            System.out.println(dice[0]);
        }
    }
}