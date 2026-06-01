package bfs.Leetcode_1368;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Solution {

    static int m, n;

    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[] dx = {0, 0, 0, 1, -1};
        int[] dy = {0, 1, -1, 0, 0};

        int[][] cost = new int[m][n];
        for (int[] row : cost) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        cost[0][0] = 0;

        Deque<int[]> deque = new ArrayDeque<>();
        deque.addFirst(new int[]{0, 0});

        while (!deque.isEmpty()) {
            int[] now = deque.pollFirst();

            for (int d = 1; d <= 4; d++) {
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if (nx < 0 || nx >= m || ny < 0 || ny >= n) {
                    continue;
                }

                int dir = grid[now[0]][now[1]];
                int sd = (dir == d) ? 0 : 1;
                int nc = cost[now[0]][now[1]] + sd;

                if (nc < cost[nx][ny]) {
                    cost[nx][ny] = nc;
                    if (sd == 0) {
                        deque.addFirst(new int[]{nx, ny});
                    } else {
                        deque.addLast(new int[]{nx, ny});
                    }
                }
            }
        }
        return cost[m - 1][n - 1];
    }
}
