package bfs.BOJ_2234;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static class Node {
        int x, y;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N, M;
    static int[][] arr;
    static int[][] room;
    static Map<Integer, Integer> map;
    static int removeWallSize;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[M][N];
        room = new int[M][N];
        map = new HashMap<>();

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 1;
        int max = 0;

        for(int i=0;i<M;i++) {
            for(int j=0;j<N;j++) {
                if(room[i][j] == 0) {
                    int area = bfs(i, j, cnt);
                    max = Math.max(max, area);
                    map.put(cnt, area);
                    cnt++;
                }
            }
        }

        for(int i=0;i<M;i++) {
            for(int j=0;j<N;j++) {
                removeWall(i, j);
            }
        }

        System.out.println(map.size());
        System.out.println(max);
        System.out.println(removeWallSize);
    }

    static String convertBit(int n) {
        StringBuilder sb = new StringBuilder();
        
        sb.append((n & 1) > 0 ? "1" : "0");    // 서쪽 (1)
        sb.append((n & 2) > 0 ? "1" : "0");    // 북쪽 (2)  
        sb.append((n & 4) > 0 ? "1" : "0");    // 동쪽 (4)
        sb.append((n & 8) > 0 ? "1" : "0");    // 남쪽 (8)
        
        return sb.toString();
    }

    static void removeWall(int x, int y) {
        int sum = map.get(room[x][y]);

        int[] dx = {0, -1, 0, 1};
        int[] dy = {-1, 0, 1, 0};

        String bit = convertBit(arr[x][y]);

        for(int i=0;i<4;i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || nx >= M || ny < 0 || ny >= N) continue;

            if(bit.charAt(i) == '1' && room[x][y] != room[nx][ny]) {
                sum += map.get(room[nx][ny]);
                removeWallSize = Math.max(removeWallSize, sum);
                sum -= map.get(room[nx][ny]);
            }
        }
    }

    static int bfs(int x, int y, int cnt) {
        int c = 1;
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(x, y));
        room[x][y] = cnt;

        int[] dx = {0, -1, 0, 1};
        int[] dy = {-1, 0, 1, 0};

        while(!queue.isEmpty()) {
            Node now = queue.poll();
            String bit = convertBit(arr[now.x][now.y]);

            for(int i=0;i<4;i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx < 0 || nx >= M || ny < 0 || ny >= N) continue;
                if(bit.charAt(i) == '1') continue;

                if(room[nx][ny] == 0) {
                    room[nx][ny] = cnt;
                    c++;
                    queue.add(new Node(nx, ny));
                }
            }
        }
        return c;
    }
}