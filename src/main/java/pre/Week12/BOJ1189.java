package pre.Week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1189 {
    static int R, C, K, ans;
    static char[][] map;
    static int[][] visit; // 방문 체크
    static int[] dx = {0, 1, 0, -1}; // 방향 북 동 남 서
    static int[] dy = {1, 0 , -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 지도
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken()); // 거리
        map = new char[R][C];
        visit = new int[R][C];
        // 맵 정보
        for(int i=0;i<R;i++) {
            String T = br.readLine();
            for(int j=0;j<C;j++) {
                map[i][j] = T.charAt(j);
            }
        }
        // 현재 위치 왼쪽 아래, 집 위치 오른쪽 위
        visit[R - 1][0] = 1; // 현재 위치가 왼쪽 아래이므로 방문 체크
        dfs(R - 1, 0, 1);
        System.out.println(ans);
    }
    static void dfs(int r, int c, int mv) {
        if(r == 0 && c == C - 1) {
            if(mv == K) { // 거리가 K면, 정답 증가
                ans++;
            }
            return;
        }
        // 방향 4가지에서 길 탐색
        for(int i=0;i<4;i++) {
            // 새로운 위치
            int nr = r + dx[i];
            int nc = c + dy[i];
            // 범위 안에 있고, 방문하지 않았다면 재귀
            if(nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
            // 이미 방문한 곳이거나, 벽이라면 continue
            if(visit[nr][nc] == 1 || map[nr][nc] == 'T') continue;
            // 새로운 위치 방문 체크
            visit[nr][nc] = 1;
            // 방문하지 않은 곳 탐색
            dfs(nr, nc, mv + 1);
            visit[nr][nc] = 0;
        }
    }
}
