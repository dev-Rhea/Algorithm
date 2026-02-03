package implementation.BOJ_1938;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

class Main {
    static class Tree {
        int x, y, d, cnt;
        Tree(int x, int y, int d, int cnt) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.cnt = cnt;
        }
    }

    static int N;
    static char[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        List<int[]> init = new ArrayList<>();

        for(int i=0;i<N;i++) {
            String input = br.readLine();
            for(int j=0;j<N;j++) {
                map[i][j] = input.charAt(j);

                if(map[i][j] == 'B') init.add(new int[]{i, j});
            }
        }

        int sx = init.get(1)[0];
        int sy = init.get(1)[1];
        int sd = (init.get(0)[0] == init.get(1)[0]) ? 0 : 1;

        System.out.println(bfs(sx, sy, sd));
    }

    static int bfs(int x, int y, int d) {
        Deque<Tree> move = new ArrayDeque<>();
        boolean[][][] visited = new boolean[2][N][N];

        move.add(new Tree(x, y, d, 0));
        visited[d][x][y] = true;

        while(!move.isEmpty()) {
            Tree now = move.poll();

            if(isEnd(now.x, now.y, now.d)) return now.cnt;

            for(int i=0;i<4;i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(canMove(nx, ny, now.d) && !visited[now.d][nx][ny]) {
                    visited[now.d][nx][ny] = true;
                    move.add(new Tree(nx, ny, now.d, now.cnt+1));
                }
            }

            int nd = (now.d == 0) ? 1 : 0;
            if(rotation(now.x, now.y) && !visited[nd][now.x][now.y]) {
                visited[nd][now.x][now.y] = true;
                move.add(new Tree(now.x, now.y, nd, now.cnt+1));
            }
        }

        return 0;
    }

    static boolean canMove(int x, int y, int d) {
        if(d == 0) {
            if(!isValid(x, y-1) || !isValid(x, y) || !isValid(x, y+1)) return false;
            if(map[x][y-1] == '1' || map[x][y] == '1' || map[x][y+1] == '1') return false;
        }
        else {
            if(!isValid(x-1, y) || !isValid(x, y) || !isValid(x+1, y)) return false;
            if(map[x-1][y] == '1' || map[x][y] == '1' || map[x+1][y] == '1') return false;
        }
        return true;
    }

    static boolean rotation(int x, int y) {
        for(int i=x-1;i<=x+1;i++) {
            for(int j=y-1;j<=y+1;j++) {
                if(!isValid(i, j) || map[i][j] == '1') return false;
            }
        }

        return true;
    }

    static boolean isEnd(int x, int y, int d) {
        if(d == 0) return map[x][y] == 'E' && isValid(x, y-1) && map[x][y-1] == 'E' && isValid(x, y+1) && map[x][y+1] == 'E';
        else return map[x][y] == 'E' && isValid(x-1, y) && map[x-1][y] == 'E' && isValid(x+1, y) && map[x+1][y] == 'E';
    }

    static boolean isValid(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}