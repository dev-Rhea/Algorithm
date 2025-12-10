package bfs.BOJ_11559;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Main {
    static class Bbuyo {
        int x, y;
        Bbuyo(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static char[][] map = new char[12][6];
    static boolean[][] visited;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        cnt = 0;

        for(int i=0;i<12;i++) {
            map[i] = br.readLine().toCharArray();
        }

        while(true) {
            boolean popped = false;
            visited = new boolean[12][6];
            boolean[][] removed = new boolean[12][6];

            for(int i = 0; i < 12; i++) {
                for(int j = 0; j < 6; j++) {
                    if(map[i][j] != '.' && !visited[i][j]) {
                        Queue<Bbuyo> group = bfs(i, j, map[i][j]);
                        
                        if(group.size() >= 4) {
                            popped = true;
                            for(Bbuyo b : group) {
                                removed[b.x][b.y] = true;
                            }
                        }
                    }
                }
            }

            if(!popped) break;

            for(int i = 0; i < 12; i++) {
                for(int j = 0; j < 6; j++) {
                    if(removed[i][j]) {
                        map[i][j] = '.';
                    }
                }
            }

            gravity();
            cnt++;

        }

        System.out.println(cnt);
    }

    static Queue<Bbuyo> bfs(int x, int y, char color) {
        Queue<Bbuyo> queue = new LinkedList<>();
        Queue<Bbuyo> group = new LinkedList<>();
        
        queue.add(new Bbuyo(x, y));
        visited[x][y] = true;

        while(!queue.isEmpty()) {
            Bbuyo now = queue.poll();
            group.add(now);

            for(int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx < 0 || nx >= 12 || ny < 0 || ny >= 6) continue;
                if(visited[nx][ny] || map[nx][ny] != color) continue;

                visited[nx][ny] = true;
                queue.add(new Bbuyo(nx, ny));
            }
        }

        return group;
    }

    static void gravity() {
        for(int j = 0; j < 6; j++) {
            int bottom = 11;
            
            for(int i = 11; i >= 0; i--) {
                if(map[i][j] != '.') {
                    if(i != bottom) {
                        map[bottom][j] = map[i][j];
                        map[i][j] = '.';
                    }
                    bottom--;
                }
            }
        }
    }
}