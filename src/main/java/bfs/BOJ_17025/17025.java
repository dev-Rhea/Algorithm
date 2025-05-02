package bfs.BOJ_17025;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Main {

    static int N;
    static char[][] map;
    static boolean[][] visited;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};
    static int max = 0;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        visited = new boolean[N][N];

        for(int i=0;i<N;i++) {
            String input = br.readLine();
            for(int j=0;j<N;j++) {
                map[i][j] = input.charAt(j);
            }
        }

        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                if(map[i][j] == '#' && !visited[i][j]) {
                    int[] ans = bfs(i, j);
                    
                    if(ans[0] > max || (ans[0] == max && ans[1] < min)) {
                        max = ans[0];
                        min = ans[1];
                    }
                }
            }
        }
        System.out.print(max + " " + min);

    }

    static int[] bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();

        visited[x][y] = true;
        queue.add(new int[]{x, y});

        int cnt = 0;
        int len = 0;

        while(!queue.isEmpty()) {
            int[] now = queue.poll();
            cnt++;

            for(int i=0;i<4;i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if(nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] == '.') {
                    len++;
                    continue;
                }

                if(map[nx][ny] == '#' && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.add(new int[]{nx, ny});
                }
            }
        }

        return new int[]{cnt, len};
    }
}