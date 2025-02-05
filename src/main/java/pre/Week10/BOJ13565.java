package pre.Week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ13565 {
    static int N, M;
    static int[][] map; // 격자
    static boolean[][] visit; // 방문 체크
    static boolean check = false; // 도착 했을 때 전기흐르면 true로 바꿔줌
    static int[][] dir = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}}; // 방향

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visit = new boolean[N][M];

        for(int i = 0; i < N; i++) {
            String str = br.readLine(); // 격자 각 줄 문자열로 읽고, 문자를 숫자로 변환하여 배열에 넣어줌
            for(int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0'; // 도착 했을 때 1인지 아닌지 판단
            }
        }

        for(int i=0;i<M;i++) {
            if(map[0][i] == 0) {
                DFS(0,i); // 바깥 라인 값 0 이면 DFS 돌려줌
            }
        }
        System.out.println(check ? "YES" : "NO");

    }

    public static void DFS(int x, int y) {
        visit[x][y] = true;
        if(x == N - 1 && map[x][y] == 0){ // 가장 내부에 전기가 흐른다면 true 출력되도록
            check = true;
            return;
        }

        for(int i = 0;i<4;i++) { // 4 방향 탐색하면서 이동
            int nx = x + dir[i][0];
            int ny = y + dir[i][1];

            // 범위 안에 있고, 방문하지 않았고, 전기가 흐르지 않은 곳이면 DFS 돌려줌
            if(nx >= 0 && ny >= 0 && nx < N && ny < M && !visit[nx][ny] && map[nx][ny] == 0) {
                DFS(nx, ny); // 재귀
            }
        }
    }
}
