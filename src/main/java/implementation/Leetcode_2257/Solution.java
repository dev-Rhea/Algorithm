package implementation.Leetcode_2257;

public class Solution {

    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        int[][] map = new int[m][n];

        for (int[] g : guards) {
            map[g[0]][g[1]] = 2;
        }

        for (int[] w : walls) {
            map[w[0]][w[1]] = 3;
        }

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        for (int[] g : guards) {
            int r = g[0];
            int c = g[1];

            for (int d = 0; d < 4; d++) {
                int nr = r + dx[d];
                int nc = c + dy[d];

                while (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                    if (map[nr][nc] == 2 || map[nr][nc] == 3) {
                        break;
                    }
                    map[nr][nc] = 1;
                    nr += dx[d];
                    nc += dy[d];
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 0) {
                    ans++;
                }
            }
        }

        return ans;
    }
}
