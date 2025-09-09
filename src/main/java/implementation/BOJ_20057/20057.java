package implementation.BOJ_20057;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int N;
    static int[][] A;
    static int sum = 0;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    
    // 각 방향별 모래 분산 패턴 (왼쪽 기준으로 작성 후 회전)
    static int[][][] per = {
        // 왼쪽으로 이동할 때의 패턴
        {{0, 0, 2, 0, 0},
         {0, 10, 7, 1, 0},
         {5, 0, 0, 0, 0}, // 가운데가 α (나머지)
         {0, 10, 7, 1, 0},
         {0, 0, 2, 0, 0}},
        
        // 아래로 이동할 때의 패턴 (왼쪽 패턴을 시계방향 90도 회전)
        {{0, 0, 0, 0, 0},
         {0, 1, 0, 1, 0},
         {2, 7, 0, 7, 2},
         {0, 10, 0, 10, 0},
         {0, 0, 5, 0, 0}},
        
        // 오른쪽으로 이동할 때의 패턴 (왼쪽 패턴을 180도 회전)
        {{0, 0, 2, 0, 0},
         {0, 1, 7, 10, 0},
         {0, 0, 0, 0, 5},
         {0, 1, 7, 10, 0},
         {0, 0, 2, 0, 0}},
        
        // 위로 이동할 때의 패턴 (왼쪽 패턴을 반시계방향 90도 회전)
        {{0, 0, 5, 0, 0},
         {0, 10, 0, 10, 0},
         {2, 7, 0, 7, 2},
         {0, 1, 0, 1, 0},
         {0, 0, 0, 0, 0}}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        tornado();
        System.out.println(sum);
    }

    static void tornado() {
        int r = N / 2, c = N / 2; 
        int d = 0; // 왼쪽에서 시작
        int steps = 1; // 칸 수

        while (true) {
            for (int i = 0; i < 2; i++) { 
                for (int j = 0; j < steps; j++) {
                    r += dx[d];
                    c += dy[d];

                    if (r < 0 || r >= N || c < 0 || c >= N) return; 

                    cal(r, c, d);
                }
                d = (d + 1) % 4; 
                if (r == 0 && c == 0) return;
            }
            steps++;
        }
    }

    static void cal(int r, int c, int d) {
        int n = A[r][c];
        if (n == 0) return;

        A[r][c] = 0;
        int totalSpread = 0;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                int percent = per[d][i][j];
                if (percent == 0) continue;

                int temp = (n * percent) / 100;
                totalSpread += temp;


                // 실제 격자에 비율 위치 적용
                int nx = r + (i - 2);
                int ny = c + (j - 2);

                if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                    A[nx][ny] += temp; 
                } else {
                    sum += temp;
                }
            }
        }

        int remainingSand = n - totalSpread;
        int nx = r + dx[d];
        int ny = c + dy[d];

        if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
            A[nx][ny] += remainingSand;
        } else {
            sum += remainingSand;
        }
    }
}