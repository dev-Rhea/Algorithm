package bfs.BOJ_2573;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

class Main {
    static class Ice {
        int x, y, h;
        Ice(int x, int y, int h) {
            this.x = x;
            this.y = y;
            this.h = h;
        }
    }

    static int N, M;
    static int[][] map;
    static List<Ice> ice;
    static int[] parent;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        ice = new LinkedList<>();
        parent = new int[N * M];

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] != 0) ice.add(new Ice(i, j, map[i][j]));
            }
        }

        int year = 0;
        
        while(true) {
            melting();
            ice.removeIf(i -> map[i.x][i.y] == 0);

            if(ice.isEmpty()) {
                System.out.println(0);
                return;
            }

            for(int i=0;i<N*M;i++) {
                parent[i] = i;
            }

            bfs();

            Set<Integer> group = new HashSet<>();

            for(Ice i : ice) group.add(find(getIdx(i.x, i.y)));

            if(group.size() >= 2) {
                System.out.println(year + 1);
                return;
            }

            year++;
        }
        
    }

    static void melting() {
        int[][] temp = new int[N][M];

        for(Ice i : ice) {
            int cnt = 0;
            for(int j=0;j<4;j++) {
                int nx = i.x + dx[j];
                int ny = i.y + dy[j];

                if(map[nx][ny] == 0) cnt++;
            }

            temp[i.x][i.y] = Math.max(0, map[i.x][i.y] - cnt);
        }
        for(Ice i :ice) map[i.x][i.y] = temp[i.x][i.y];
    }

    static void bfs() {
        boolean[][] visited = new boolean[N][M];

        for(Ice i : ice) {
            if(!visited[i.x][i.y]) {
                Queue<int[]> queue = new LinkedList<>();
                queue.add(new int[]{i.x, i.y});
                visited[i.x][i.y] = true;

                while (!queue.isEmpty()) {
                    int[] now = queue.poll();
                    int nx = now[0];
                    int ny = now[1];
                    int idx = getIdx(nx, ny);
                
                    for (int j = 0; j < 4; j++) {
                        int tx = nx + dx[j];
                        int ty = ny + dy[j];
                
                        if (tx < 0 || ty < 0 || tx >= N || ty >= M) continue;
                        if (!visited[tx][ty] && map[tx][ty] > 0) {
                            visited[tx][ty] = true;
                            queue.add(new int[]{tx, ty});
                            union(idx, getIdx(tx, ty));
                        }
                    }
                }
            }
        }
    }

    static int getIdx(int x, int y) {
        return x * M + y;
    }

    static int find(int x) {
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if(pa != pb) parent[pb] = pa;
    }
}