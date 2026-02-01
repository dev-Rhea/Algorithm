package bfs.BOJ_1194;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Main {
    static class Move{
        int x, y, mv, k;
        Move(int x, int y, int mv, int k) {
            this.x = x;
            this.y = y;
            this.mv = mv;
            this.k = k;
        }
    }

    static int N, M;
    static char[][] map;
    static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        visited = new boolean[N][M][64];
        int sx = 0, sy = 0;

        map = new char[N][M];
        for(int i=0;i<N;i++) {
            String str = br.readLine();
            for(int j=0;j<M;j++) {
                map[i][j] = str.charAt(j);

                if(map[i][j] == '0') {
                    sx = i;
                    sy = j;
                }
            }
        }

        System.out.println(bfs(sx, sy));
    }

    static int bfs(int x, int y) {
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        Queue<Move> queue = new LinkedList<>();
        queue.add(new Move(x, y, 0, 0));
        visited[x][y][0] = true;

        while(!queue.isEmpty()) {
            Move now = queue.poll();

            if(map[now.x][now.y] == '1') return now.mv;

            for(int d=0;d<4;d++) {
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];
                int key = now.k;

                if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if(map[nx][ny] == '#') continue; 

                if(map[nx][ny] >= 'a' && map[nx][ny] <= 'f') {
                    int n = map[nx][ny] - 'a';
                    key |= (1 << n);
                }
                else if(map[nx][ny] >= 'A' && map[nx][ny] <= 'F') {
                    int n = map[nx][ny] - 'A';
                    if((key & (1 << n)) == 0) continue;
                }

                if(visited[nx][ny][key]) continue;
                visited[nx][ny][key] = true;
                queue.add(new Move(nx, ny, now.mv+1, key));
                
            }
        }

        return -1;
    }
}