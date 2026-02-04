package implementation.BOJ_2933;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Main {
    static class Mnr {
        int x, y;
        Mnr(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int R, C;
    static char[][] cave;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] s = br.readLine().split(" ");
        R = Integer.parseInt(s[0]);
        C = Integer.parseInt(s[1]);

        cave = new char[R][C];
        for(int i=0;i<R;i++) {
            String input = br.readLine();
            for(int j=0;j<C;j++) {
                cave[i][j] = input.charAt(j);
            }
        }

        int N = Integer.parseInt(br.readLine());
        String[] st = br.readLine().split(" ");
        for(int i=0;i<N;i++) {
            int h = Integer.parseInt(st[i]);
            int r = R - h;

            throwStick(r, (i % 2 == 0));

            drop();
        }

        for(int i=0;i<R;i++) {
            for(int j=0;j<C;j++) {
                System.out.print(cave[i][j]);
            }
            System.out.println();
        }
    }

    static void throwStick(int r, boolean l) {
        if(l) {
            for(int j=0;j<C;j++) {
                if(cave[r][j] == 'x'){
                    cave[r][j] = '.';
                    return;
                }
            }
        }
        else {
            for(int j=C-1;j>=0;j--) {
                if(cave[r][j] == 'x') {
                    cave[r][j] = '.';
                    return;
                }
            }
        }
    }

    static void drop() {
        visited = new boolean[R][C];

        for(int j=0;j<C;j++) {
            if(cave[R-1][j] == 'x' && !visited[R-1][j]) bfs(R-1, j);
        }

        List<Mnr> cluster = new ArrayList<>();
        for(int i=0;i<R;i++) {
            for(int j=0;j<C;j++) {
                if(cave[i][j] == 'x' && !visited[i][j]) cluster.add(new Mnr(i, j));
            }
        }

        if(cluster.isEmpty()) return;

        int dist = getDist(cluster);

        for(Mnr m : cluster) {
            cave[m.x][m.y] = '.';
        }

        for(Mnr m : cluster) {
            cave[m.x+dist][m.y] = 'x';
        }
    }

    static void bfs(int x, int y) {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        Queue<Mnr> queue = new LinkedList<>();
        queue.add(new Mnr(x, y));
        visited[x][y] = true;

        while(!queue.isEmpty()) {
            Mnr now = queue.poll();

            for(int d=0;d<4;d++) {
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];

                if(nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
                if(visited[nx][ny] || cave[nx][ny] == '.') continue;

                visited[nx][ny] = true;
                queue.add(new Mnr(nx, ny));
            }
        }
    }

    static int getDist(List<Mnr> cluster) {
        int min = R;

        for(Mnr m : cluster) {
            int d = 0;

            for(int i=m.x+1;i<R;i++) {
                if(cave[i][m.y] == 'x' && !isCluster(cluster, i, m.y)) break;
                d++;
            }
            min = Math.min(min, d);
        }
        return min;
    }

    static boolean isCluster(List<Mnr> cluster, int x, int y) {
        for(Mnr m : cluster) {
            if(m.x == x && m.y == y) return true;
        }
        return false;
    }
}