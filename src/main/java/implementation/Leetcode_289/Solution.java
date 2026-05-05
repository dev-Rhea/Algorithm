package implementation.Leetcode_289;

public class Solution {

    static int m, n;
    static int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
    static int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};

    public void gameOfLife(int[][] board) {
        m = board.length;
        n = board[0].length;

        int[][] next = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                next[i][j] = search(board, i, j);
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = next[i][j];
            }
        }
    }

    static int search(int[][] map, int sx, int sy) {
        int cnt = 0;

        for (int d = 0; d < 8; d++) {
            int nx = sx + dx[d];
            int ny = sy + dy[d];

            if (nx < 0 || nx >= m || ny < 0 || ny >= n) {
                continue;
            }
            if (map[nx][ny] == 1) {
                cnt++;
            }
        }

        if (map[sx][sy] == 1) {
            if (cnt < 2 || cnt > 3) {
                return 0;
            }
            return 1;
        } else {
            if (cnt == 3) {
                return 1;
            }
            return 0;
        }
    }
}
