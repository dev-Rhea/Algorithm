package pre.Week22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2146 {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};
    static int min = Integer.MAX_VALUE;
    static int[][] dist;
    static Queue<Node> queue;

    static class Node {
        int x, y, dist;

        Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist= dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N+1][N+1];
        visited = new boolean[N+1][N+1];
        dist = new int[N][N];
        // 섬 입력
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int num = 1;

        // 같은 섬 찾기
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                if(map[i][j] == 1 && !visited[i][j]) {
                    visited[i][j] = true;
                    DFS(i, j, num);
                    num++;
                }
            }
        }

        // 각 섬별로 거리 구하기
        for(int i=1;i<=num;i++) {
            int[][] newMap = map.clone();
            BFS(map, i);
        }

        System.out.println(min);
    }

    public static void DFS(int x, int y, int num) {
        visited[x][y] = true;
        map[x][y] = num;

        for(int i=0;i<4;i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;;

            if(map[nx][ny] != 0 && !visited[nx][ny]) {
                DFS(nx, ny, num);
            }
        }
    }

    public static void BFS(int[][] arr, int num) {
        boolean[][] check = new boolean[101][101];
        queue = new LinkedList<>();

        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                if(arr[i][j] == num) {
                    queue.add(new Node(i, j, 0));
                    check[i][j] = true;

                }
            }
        }

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            for(int i=0;i<4;i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;;

                if(arr[nx][ny] == num) continue;
                if(check[nx][ny]) continue;
                if(arr[nx][ny] == 0) {
                    queue.add(new Node(nx, ny, node.dist + 1));
                    check[nx][ny] = true;
                }
                else {
                    min = Math.min(min, node.dist);
                    return;
                }

            }
        }
    }


}
