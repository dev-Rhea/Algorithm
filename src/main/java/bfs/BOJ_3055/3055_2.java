package bfs.BOJ_3055;
import java.io.*;
import java.util.*;

class Main {

    static int R, C;
    static char[][] map;
    static Queue<int[]> wq;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        int sx = 0, sy = 0;
        wq = new LinkedList<>();

        for(int i=0;i<R;i++) {
            String str = br.readLine();
            for(int j=0;j<C;j++) {
                map[i][j] = str.charAt(j);

                if(map[i][j] == 'S') {
                    sx = i;
                    sy = j;
                }
                if(map[i][j] == '*') wq.add(new int[]{i, j});
            }
        }

        bfs(sx, sy);
    }

    static void bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[]{x, y, 0});
        map[x][y] = 'V';

        while(!queue.isEmpty()) {
            int wSize = wq.size();
            for(int i=0;i<wSize;i++) {
                int[] now = wq.poll();
                for(int d=0;d<4;d++) {
                    int nx = now[0] + dx[d];
                    int ny = now[1] + dy[d];

                    if(nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
                    if(map[nx][ny] != '.') continue;
                    map[nx][ny] = '*';
                    wq.add(new int[]{nx, ny});
                }
            }

            int qSize = queue.size();
            for(int i=0;i<qSize;i++) {
                int[] now = queue.poll();

                for(int d=0;d<4;d++) {
                    int nx = now[0] + dx[d];
                    int ny = now[1] + dy[d];

                    if(nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
                    if(map[nx][ny] == 'D') {
                        System.out.println(now[2] + 1);
                        return;
                    }

                    if(map[nx][ny] == '.') {
                        map[nx][ny] = 'V';
                        queue.add(new int[]{nx, ny, now[2]+1});
                    }
                }
            }
        }

        System.out.println("KAKTUS");
    }
}