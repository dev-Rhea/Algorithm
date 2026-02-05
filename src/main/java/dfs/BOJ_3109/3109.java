package dfs.BOJ_3109;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static class Node {
        int x, y;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int R, C, max;
    static char[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        max = 0;

        map = new char[R][C];
        visited = new boolean[R][C];

        for(int i=0;i<R;i++) {
            String input = br.readLine();
            for(int j=0;j<C;j++) {
                map[i][j] = input.charAt(j);
            }
        }

        for(int i=0;i<R;i++) {
            if(dfs(i, 0)) max++;
        }

        System.out.println(max);
    }

    static boolean dfs(int x, int y) {
        if(y == C-1) return true;

        int[] dx = {-1, 0, 1};
        int[] dy = {1, 1, 1};

        for(int d=0;d<3;d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if(nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
            if(visited[nx][ny]) continue;
            if(map[nx][ny] == 'x') continue;

            visited[nx][ny] = true;
            if(dfs(nx, ny)) return true;
        }

        return false;
    }
}