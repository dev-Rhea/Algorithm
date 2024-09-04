package Week18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2072 {
    static int SIZE = 19;
    static int[][] board = new int[SIZE][SIZE];
    static int[] dx = {0, 1, 1, 1}; // Directions: right, down, down-right, up-right
    static int[] dy = {1, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int move = 1; move <= N; move++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int color = (move % 2 == 1) ? 1 : 2; // 1 for black, 2 for white

            board[x][y] = color;

            if (checkWin(x, y, color)) {
                System.out.println(move);
                return;
            }
        }

        System.out.println(-1);
    }

    static boolean checkWin(int x, int y, int color) {
        for (int dir = 0; dir < 4; dir++) {
            int count = 1;

            // Check in the positive direction
            count += countStones(x, y, color, dx[dir], dy[dir]);

            // Check in the negative direction
            count += countStones(x, y, color, -dx[dir], -dy[dir]);

            if (count == 5) {
                return true;
            }
        }
        return false;
    }

    static int countStones(int x, int y, int color, int dx, int dy) {
        int count = 0;
        int nx = x + dx;
        int ny = y + dy;

        while (nx >= 0 && nx < SIZE && ny >= 0 && ny < SIZE && board[nx][ny] == color) {
            count++;
            nx += dx;
            ny += dy;
        }

        return count;
    }
}