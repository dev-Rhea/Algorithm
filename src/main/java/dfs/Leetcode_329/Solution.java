package dfs.Leetcode_329;

public class Solution {

    static int m, n;
    static int[][] dp;

    public int longestIncreasingPath(int[][] matrix) {
        m = matrix.length;
        n = matrix[0].length;

        dp = new int[m][n];
        int max = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max, dfs(i, j, matrix));
            }
        }

        return max;
    }

    private int dfs(int x, int y, int[][] matrix) {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        if (dp[x][y] != 0) {
            return dp[x][y];
        }

        dp[x][y] = 1;

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (nx < 0 || nx >= m || ny < 0 || ny >= n) {
                continue;
            }
            if (matrix[nx][ny] <= matrix[x][y]) {
                continue;
            }

            dp[x][y] = Math.max(dp[x][y], dfs(nx, ny, matrix) + 1);
        }

        return dp[x][y];
    }
}
