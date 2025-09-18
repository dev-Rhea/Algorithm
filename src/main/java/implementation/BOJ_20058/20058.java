package implementation.BOJ_20058;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

    static int N, Q;
    static int[][] map, temp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        int n = (int) Math.pow(2, N);

        map = new int[n+1][n+1];
        temp = new int[n+1][n+1];
        for(int i=1;i<=n;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=n;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<Q;i++) {
            int l = Integer.parseInt(st.nextToken());

            firestorm(l, n);
        }

        int sum = 0;
        int cnt = 0;

        for(int i=1;i<=n;i++) {
            for(int j=1;j<=n;j++) {
                sum += map[i][j];
            }
        }

        boolean[][] visited = new boolean[n+1][n+1];
        for(int i=1;i<=n;i++) {
            for(int j=1;j<=n;j++) {
                if(map[i][j] > 0 && !visited[i][j]) {
                    int size = find(i, j, n, visited);
                    cnt = Math.max(cnt, size);
                }
            }
        }

        System.out.println(sum);
        System.out.println(cnt);
    }

    static void firestorm(int l, int n) {
        int L = (int) Math.pow(2, l);

        for(int i=1;i<=n;i+=L) {
            for(int j=1;j<=n;j+=L) {
                rotate(i, j, L);
            }
        }


        ice(n);
    }

    static void rotate(int x, int y, int size) {
        for(int i=0;i<size;i++) {
            for(int j=0;j<size;j++) {
                temp[x+j][y+size-1-i] = map[x+i][y+j];
            }
        }

        for(int i=0;i<size;i++) {
            for(int j=0;j<size;j++) {
                map[x+i][y+j] = temp[x+i][y+j];
            }
        }
    }

    static void ice(int n) {
        int[] dy = {1, 0, -1, 0};
        int[] dx = {0, 1, 0, -1};

        boolean[][] reduce = new boolean[n+1][n+1];


        for(int i=1;i<=n;i++) {
            for(int j=1;j<=n;j++) {
                if(map[i][j] > 0) {
                    int ice = 0;

                    for(int d=0;d<4;d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];
                        
    
                        if(nx >= 1 && nx <= n && ny >= 1 && ny <= n && map[nx][ny] > 0) ice++;
                    }

                    if(ice < 3) reduce[i][j] = true;
                }
            }
        }

        for(int i=1;i<=n;i++) {
            for(int j=1;j<=n;j++) {
                if(reduce[i][j] && map[i][j] > 0) map[i][j]--;
            }
        }
    }

    static int find(int r, int c, int n, boolean[][] visited) {
        int[] dy = {1, 0, -1, 0};
        int[] dx = {0, 1, 0, -1};

        int size = 1;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{r, c});
        visited[r][c] = true;

        while(!queue.isEmpty()) {
            int[] now = queue.poll();

            for(int i=0;i<4;i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if(nx >= 1 && nx <= n && ny >= 1 && ny <= n && !visited[nx][ny] && map[nx][ny] > 0) {
                    visited[nx][ny] = true;
                    queue.add(new int[]{nx, ny});
                    size++;
                }
            }
        }
        return size;
    }
}