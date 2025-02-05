package pre.Week28;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BOJ_3197 {
    public static class Pair {
        int x;
        int y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int R, C;
    static char[][] map;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        boolean[][] swanVisited = new boolean[R][C];
        boolean[][] visited = new boolean[R][C];

        ArrayDeque<Pair> swans = new ArrayDeque<>();
        ArrayDeque<Pair> melt = new ArrayDeque<>();
        Pair end = null;

        for(int i=0;i<R;i++) {
            map[i] = br.readLine().toCharArray();
            for(int j=0;j<C;j++) {
                if(map[i][j] == 'L') {
                    map[i][j] = '.';
                    if (swans.isEmpty()) {
                        swans.add(new Pair(i, j));
                    }
                    else {
                        end = new Pair(i, j);
                    }
                }
                if(map[i][j] == '.') {
                    melt.add(new Pair(i, j));
                    swanVisited[i][j] = true;
                }
            }
        }
        int ans = 1;
        while(true) {
            melt = bfs(melt, swanVisited, true);
            swans = bfs(swans, visited, false);
            if(visited[end.x][end.y]) {
                break;
            }
            ans++;
        }
        System.out.println(ans);
    }

    private static ArrayDeque<Pair> bfs(ArrayDeque<Pair> queue, boolean[][] visited, boolean flag) {
        ArrayDeque<Pair> next = new ArrayDeque<>();
        while(!queue.isEmpty()) {
            Pair now  = queue.poll();
            for(int i=0;i<4;i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(0 <= nx && 0 <= ny && nx < R && ny < C && ! visited[nx][ny]) {
                    visited[nx][ny] = true;
                    if(map[nx][ny] == 'X') {
                        next.add(new Pair(nx, ny));
                        if(flag) {
                            map[nx][ny] = '.';
                        }
                    }
                    else {
                        queue.add(new Pair(nx, ny));
                    }
                }
            }
        }
        return next;
    }
}
