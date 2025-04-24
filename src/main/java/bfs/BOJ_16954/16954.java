package bfs.BOJ_16954;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Main {

    static class Node {
        int x, y, t;
        Node(int x, int y, int t) {
            this.x = x;
            this.y = y;
            this.t = t;
        }
    }

    static char[][] map = new char[8][8];
    static int[] dx = {-1, -1, -1, 0, 0, 0, 1, 1, 1}; // 8방향 + 제자리
    static int[] dy = {-1, 0, 1, -1, 0, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 0; i < 8; i++) {
            String input = br.readLine();
            for(int j = 0; j < 8; j++) {
                map[i][j] = input.charAt(j);
            }
        }

        System.out.println(bfs());
    }

    static int bfs() {
        boolean[][][] visited = new boolean[8][8][9];
        Queue<Node> queue = new LinkedList<>();

        queue.add(new Node(7, 0, 0));
        visited[7][0][0] = true;

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            if (now.x == 0 && now.y == 7) return 1;

            for (int i = 0; i < 9; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                int nt = Math.min(8, now.t + 1); // 시간 최대 8로 고정

                if (nx < 0 || nx >= 8 || ny < 0 || ny >= 8) continue;
                if (visited[nx][ny][nt]) continue;

                if (moveWall(nx, ny, now.t) == '.' && moveWall(nx, ny, nt) == '.') {
                    visited[nx][ny][nt] = true;
                    queue.add(new Node(nx, ny, nt));
                }
            }
        }

        return 0;
    }

    static char moveWall(int x, int y, int t) {
        int wallX = x - t;
        if (wallX < 0) return '.';
        return map[wallX][y];
    }
}
