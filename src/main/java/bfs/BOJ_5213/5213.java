package bfs.BOJ_5213;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Main {
    static class Tile{
        int num, v;
        Tile(int num, int v) {
            this.num = num;
            this.v = v;
        }
    }

    static int N, max;
    static int[] path;
    static Tile[][] tiles;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        tiles = new Tile[N+1][N*2+2];
        path = new int[N*N-N/2+1];
        int idx = 1;
        max = 0;

        for(int i=1;i<=N;i++) {
            if(i % 2 == 1) {
                for(int j=1;j<=2*N;j+=2) {
                    String[] input = br.readLine().split(" ");
                    int a = Integer.parseInt(input[0]);
                    int b = Integer.parseInt(input[1]);

                    tiles[i][j] = new Tile(idx, a);
                    tiles[i][j+1] = new Tile(idx++, b);
                }
            }
            else {
                for(int j=2;j<=2*N-1;j+=2) {
                    String[] input = br.readLine().split(" ");
                    int a = Integer.parseInt(input[0]);
                    int b = Integer.parseInt(input[1]);

                    tiles[i][j] = new Tile(idx, a);
                    tiles[i][j+1] = new Tile(idx++, b);
                }
            }
        }

        bfs();

        findPath();
    }

    static void bfs() {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N+1][N*2+1];

        queue.add(new int[]{1, 1});
        queue.add(new int[]{1, 2});
        visited[1][1] = true;
        visited[1][2] = true;
        path[1] = 1;

        while(!queue.isEmpty()) {
            int x  = queue.peek()[0];
            int y = queue.peek()[1];
            queue.poll();

            for(int d=0;d<4;d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if(nx <= 0 || nx > N || ny <= 0 || ny > 2*N) continue;
                if(tiles[nx][ny] == null || tiles[nx][ny].v == 0 || visited[nx][ny]) continue;

                if(tiles[x][y].v == tiles[nx][ny].v) {
                    if(path[tiles[nx][ny].num] != 0) continue;

                    path[tiles[nx][ny].num] = tiles[x][y].num;
                    max = Math.max(max, tiles[nx][ny].num);

                    visited[nx][ny] = true;
                    queue.add(new int[]{nx ,ny});

                    if(tiles[nx][ny-1] != null && tiles[nx][ny-1].num == tiles[nx][ny].num) {
                        visited[nx][ny-1] = true;
                        queue.add(new int[]{nx, ny-1});
                    }
                    else if(tiles[nx][ny+1] != null && tiles[nx][ny+1].num == tiles[nx][ny].num) {
                        visited[nx][ny+1] = true;
                        queue.add(new int[]{nx, ny+1});
                    }
                }
            }
        }
    }

    static void findPath() {
        List<Integer> ans = new ArrayList<>();
        while(max != path[max]) {
            ans.add(max);
            max = path[max];
        }
        ans.add(1);

        System.out.println(ans.size());
        for(int i=ans.size()-1;i>=0;i--) {
            System.out.print(ans.get(i)+" ");
        }
    }
}