package Week24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_15686 {
    static int N, M;
    static int[][] map;
    static List<int[]> home;
    static List<int[]> chicken;
    static List<int[]> check;
    static int minDistance = Integer.MAX_VALUE;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        home = new ArrayList<>();
        chicken = new ArrayList<>();
        check = new ArrayList<>();

        // 도시 정보 입력
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) {
                    home.add(new int[]{i, j});
                }

                if(map[i][j] == 2) {
                    chicken.add(new int[]{i, j});
                }
            }
        }

        visited = new boolean[chicken.size()];
        backtracking(0, 0);
        System.out.println(minDistance);
    }
    static void backtracking(int depth, int start) {
        if(depth == M) {
            int sum = 0;
            for(int[] h : home) {
                int min = Integer.MAX_VALUE;
                for(int[] c : check) {
                    int dist = Math.abs(h[0] - c[0]) + Math.abs(h[1] - c[1]);
                    min = Math.min(dist, min);
                }
                sum += min;
            }
            minDistance = Math.min(minDistance, sum);
            return;
        }

        for(int i=start;i<chicken.size();i++) {
            if(!visited[i]) {
                visited[i] = true;
                check.add(chicken.get(i));
                backtracking(depth + 1, i + 1);
                check.remove(check.size() - 1);
                visited[i] = false;
            }
        }
    }
}
