package bfs.BOJ_16918;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static class Bomb {
        int x, y;
        Bomb(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        char[][] map = new char[R][C];
        Queue<Bomb> queue = new LinkedList<>();
        int[] dy = {1, 0, -1, 0};
        int[] dx = {0, 1, 0, -1};

        for(int i=0;i<R;i++) {
            String str = br.readLine();
            for(int j=0;j<C;j++) {
                map[i][j] = str.charAt(j);
                
                if(map[i][j] == 'O') queue.add(new Bomb(i, j));
            }
        }

        for(int t=2;t<=N;t++) {
            if(t % 2 == 0) {
                for(int i=0;i<R;i++) {
                    for(int j=0;j<C;j++) {
                        if(map[i][j] == 'O') queue.add(new Bomb(i, j));
                    }
                }

                for(int i=0;i<R;i++) {
                    Arrays.fill(map[i], 'O');
                }
            }

            if(t % 2 == 1) {
                while(!queue.isEmpty()) {
                    Bomb now = queue.poll();

                    for(int i=0;i<4;i++) {
                        int nx = now.x + dx[i];
                        int ny = now.y + dy[i];

                        if(nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
                        map[now.x][now.y] = '.';
                        map[nx][ny] = '.';
                    }
                }

                for(int i=0;i<R;i++) {
                    for(int j=0;j<C;j++) {
                        if(map[i][j] == 'O') queue.add(new Bomb(i, j));
                    }
                }
            }
        }

        for(int i=0;i<R;i++) {
            for(int j=0;j<C;j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }
}