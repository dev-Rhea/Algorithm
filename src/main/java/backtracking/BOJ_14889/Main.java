package backtracking.BOJ_14889;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] S;
    static boolean[] visited;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        visited = new boolean[N];
        S = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                S[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(0, 0);
        System.out.println(min);
    }

    static void dfs(int depth, int start) {
        if(depth == N/2) {
            min = Math.min(min, result());
            return;
        }

        // 팀 결성
        for(int i=start;i<N;i++) {
            visited[i] = true;
            dfs(depth + 1, i + 1);
            visited[i] = false;
        }
    }

    // 능력치 차이 계산
    static int result() {
        int start = 0;
        int link = 0;

        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                if(i == j) continue;

                if(visited[i] && visited[j]) start += S[i][j];
                if(!visited[i] && !visited[j]) link += S[i][j];
            }
        }
        return Math.abs(start - link);
    }
}
