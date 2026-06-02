package bfs.Leetcode_778;

import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {

    public int swimInWater(int[][] grid) {
        int n = grid.length;
        int t = 0;
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        Queue<int[]> queue = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        boolean[][] visited = new boolean[n][n];

        visited[0][0] = true;
        queue.add(new int[]{grid[0][0], 0, 0});

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            t = Math.max(t, now[0]);

            if (now[1] == n - 1 && now[2] == n - 1) {
                return t;
            }

            for (int d = 0; d < 4; d++) {
                int nx = now[1] + dx[d];
                int ny = now[2] + dy[d];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                    continue;
                }
                if (visited[nx][ny]) {
                    continue;
                }

                visited[nx][ny] = true;
                queue.add(new int[]{grid[nx][ny], nx, ny});
            }
        }
        return t;
    }
}
