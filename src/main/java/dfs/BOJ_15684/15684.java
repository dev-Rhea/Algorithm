package dfs.BOJ_15684;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    static int[][] ladder;
    static int N, M, H;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");

        N = Integer.parseInt(s[0]);
        M = Integer.parseInt(s[1]);
        H = Integer.parseInt(s[2]);

        ladder = new int[H + 1][N + 1]; 
        
        for(int i = 0; i < M; i++) {
            String[] str = br.readLine().split(" ");
            int a = Integer.parseInt(str[0]);
            int b = Integer.parseInt(str[1]);

            ladder[a][b] = 1;   // 오른쪽으로 이동
            ladder[a][b + 1] = -1; // 왼쪽으로 이동
        }

        // 이미 조건을 만족하는 경우
        if(check()) {
            System.out.println("0");
            return;
        }

        // 최대 가로선 3개 추가 가능
        for(int i = 1; i <= 3; i++) {
            if(dfs(1, 1, 0, i)) return; 
        }

        System.out.println("-1");
    }

    static boolean dfs(int x, int y, int cnt, int size) {
        // 추가된 가로선의 수에 도달한 경우 
        if(cnt == size) {
            if(check()) {
                System.out.println(size);
                return true;
            }
            return false;
        }

        // i 는 가로선, j 는 세로선 
        for(int i = x; i <= H; i++) {
            for(int j = y; j < N; j++) {
                // 가로선 추가할 수 있는 경우 찾기 
                if(ladder[i][j] == 0 && ladder[i][j + 1] == 0) {
                    ladder[i][j] = 1;
                    ladder[i][j + 1] = -1;

                    if(dfs(i, j, cnt + 1, size)) return true;

                    ladder[i][j] = 0;
                    ladder[i][j + 1] = 0;
                }
            }
            y = 1; // 다음 줄의 가장 왼쪽부터 다시 확인
        }
        return false;
    }

    static boolean check() {
        for(int i = 1; i <= N; i++) {  // 모든 세로선 확인
            int nx = 1; 
            int ny = i;

            while(nx <= H) {
                if(ny < N && ladder[nx][ny] == 1) ny++; // 오른 쪽 이동 
                else if(ny > 1 && ladder[nx][ny] == -1) ny--; // 왼쪽 이동 
                nx++; 
            }
            if(ny != i) return false;
        }
        return true; // 도착점과 출발점 동일하다면 true 반환
    }
}
