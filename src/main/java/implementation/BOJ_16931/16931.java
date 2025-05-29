package implementation.BOJ_16931;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int N, M;
    static int[][] map;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int sum = 2 * N * M;

        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                sum += surface(i, j);
            }
        }
        System.out.println(sum);
    }

    static int surface(int x, int y) {
        int cnt = 0;

        for(int i=0;i<4;i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || nx >= N || ny < 0 || ny >= M) {
                cnt += map[x][y]; // 격자 경계 밖이면, 현재 칸의 높이 만큼 표면적 추가 
                continue;
            }
            cnt += Math.max(0, map[x][y] - map[nx][ny]); // 인접 칸과 높이 차 계산 
        }
        return cnt;
    }
}