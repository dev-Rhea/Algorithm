package bruteforce.BOJ_17406;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int N, M, K;
    static int[][] map, op;
    static int min = Integer.MAX_VALUE;
    static boolean [] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N+1][M+1];
        visited = new boolean[K];
        
        op = new int[K][3];
        for(int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=M;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0;i<K;i++) {
            st = new StringTokenizer(br.readLine());
            op[i][0] = Integer.parseInt(st.nextToken());
            op[i][1] = Integer.parseInt(st.nextToken());
            op[i][2] = Integer.parseInt(st.nextToken());
        }

        permute(0);

        System.out.println(min);
    }

    static void permute(int depth) {
        if(depth == K) {
            int sum = cal();
            min = Math.min(min, sum);
            return;
        }

        for(int i=0;i<K;i++) {
            if(!visited[i]) {
                visited[i] = true;
                
                int r = op[i][0];
                int c = op[i][1];
                int s = op[i][2];
                
                // 시계방향 회전
                turnMap(r - s, c - s, r + s, c + s, true);
                permute(depth + 1);
                // 반시계방향 회전으로 복구
                turnMap(r - s, c - s, r + s, c + s, false);
                
                visited[i] = false;
            }
        }
    }

    static void turnMap(int r1, int c1, int r2, int c2, boolean clockwise) {
        if(r1 >= r2 || c1 >= c2) return;

        if(clockwise) {
            // 시계방향 회전
            int temp = map[r1][c1];

            // 왼쪽 변: 아래에서 위로
            for(int i = r1; i < r2; i++) {
                map[i][c1] = map[i + 1][c1];
            }
            
            // 아래쪽 변: 오른쪽에서 왼쪽으로
            for(int j = c1; j < c2; j++) {
                map[r2][j] = map[r2][j + 1];
            }
            
            // 오른쪽 변: 위에서 아래로
            for(int i = r2; i > r1; i--) {
                map[i][c2] = map[i - 1][c2];
            }
            
            // 위쪽 변: 왼쪽에서 오른쪽으로
            for(int j = c2; j > c1 + 1; j--) {
                map[r1][j] = map[r1][j - 1];
            }

            map[r1][c1+1] = temp;
        } else {
            // 반시계방향 회전 (복구용)
            int temp = map[r1][c1];

            // 위쪽 변: 오른쪽에서 왼쪽으로
            for(int j = c1; j < c2; j++) {
                map[r1][j] = map[r1][j + 1];
            }
            
            // 오른쪽 변: 아래에서 위로
            for(int i = r1; i < r2; i++) {
                map[i][c2] = map[i + 1][c2];
            }
            
            // 아래쪽 변: 왼쪽에서 오른쪽으로
            for(int j = c2; j > c1; j--) {
                map[r2][j] = map[r2][j - 1];
            }
            
            // 왼쪽 변: 위에서 아래로
            for(int i = r2; i > r1 + 1; i--) {
                map[i][c1] = map[i - 1][c1];
            }

            map[r1 + 1][c1] = temp;
        }

        turnMap(r1+1, c1+1, r2-1, c2-1, clockwise);
    }

    static int cal() {
        int sum = Integer.MAX_VALUE;

        for(int i=1;i<=N;i++) {
            int rowSum = 0;
            for(int j=1;j<=M;j++) {
                rowSum += map[i][j];
            }
            sum = Math.min(sum, rowSum);
        }
        return sum;
    }
}