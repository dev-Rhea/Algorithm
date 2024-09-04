package Week17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2606 {
    static int N, M;
    static int[][] map;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        map = new int[N + 1][N + 1];
        visit = new boolean[N + 1];

        // 컴퓨터와 네트워크 상의 연결 관계 입력
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            map[a][b] = 1;
            map[b][a] = 1;
        }

        // 1번 컴퓨터는 제외하고 DFS 탐색
        System.out.println(DFS(1) - 1);

        br.close();
    }

    static int DFS(int start) {
        // 방문 체크
        visit[start] = true;
        // 1번 컴퓨터도 포함해야 하므로 1로 초기화
        int cnt = 1;

        // 연결된 컴퓨터 탐색
        for (int i = 1; i <= N; i++) {
            // 연결되어 있고, 방문하지 않은 컴퓨터라면
            if (map[start][i] == 1 && !visit[i]) {
                cnt += DFS(i);
            }
        }

        return cnt;
    }
}