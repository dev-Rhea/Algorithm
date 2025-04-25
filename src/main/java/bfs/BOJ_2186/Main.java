import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    private static final int[] dx = {1, 0, -1, 0};
    private static final int[] dy = {0, 1, 0, -1};

    private static int n, m, k;
    private static char[][] map;
    private static char[] target;
    private static int[][][] dp;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        dp = new int[n][m][81]; // 단어 최대 길이: 80

        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray(); // 문자판 입력
        }

        target = br.readLine().toCharArray(); // 목표 단어 입력
        
        bfs();      // 역방향 BFS 실행

        int answer = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                answer += dp[i][j][0];
            }
        }

        System.out.println(answer);
    }

    // 역방향 BFS 수행
    private static void bfs() {
        Deque<int[]> queue = new ArrayDeque<>();
        int lastIndex = target.length - 1;

        // 마지막 문자 위치를 시작점으로 설정
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == target[lastIndex]) {
                    dp[i][j][lastIndex] = 1;
                    queue.add(new int[]{i, j, lastIndex});
                }
            }
        }
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0], y = cur[1], depth = cur[2];

            if (depth == 0) continue;

            for (int dir = 0; dir < 4; dir++) {
                for (int step = 1; step <= k; step++) {
                    int nx = x + dx[dir] * step;
                    int ny = y + dy[dir] * step;

                    if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                    if (map[nx][ny] != target[depth - 1]) continue;

                    if (dp[nx][ny][depth - 1] == 0) {
                        queue.add(new int[]{nx, ny, depth - 1});
                    }

                    dp[nx][ny][depth - 1] += dp[x][y][depth];
                }
            }
        }
    }
}