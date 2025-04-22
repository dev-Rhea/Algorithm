package bfs.BOJ_2151;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

class Main {
    static class Node {
        int x, y, d, m;
        Node(int x, int y, int d, int m) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.m = m;
        }
    }

    static int N;
    static char[][] home;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};
    static List<int[]> doors = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        home = new char[N][N];

        for(int i = 0; i < N; i++) {
            String input = br.readLine();
            for(int j = 0; j < N; j++) {
                home[i][j] = input.charAt(j);
                if(home[i][j] == '#') doors.add(new int[]{i, j});
            }
        }

        bfs();
    }

    static void bfs() {
        int[][][] visited = new int[N][N][4];
        for(int[][] arr : visited)
            for(int[] a : arr)
                Arrays.fill(a, Integer.MAX_VALUE);

        Deque<Node> dq = new ArrayDeque<>();

        int sx = doors.get(0)[0]; // 첫번째 문 
        int sy = doors.get(0)[1];
        int ex = doors.get(1)[0]; // 두번째 문 
        int ey = doors.get(1)[1];

        for(int i = 0; i < 4; i++) {
            int nx = sx + dx[i];
            int ny = sy + dy[i];
            if(nx >= 0 && nx < N && ny >= 0 && ny < N && home[nx][ny] != '*') {
                dq.add(new Node(nx, ny, i, 0));
                visited[nx][ny][i] = 0;
            }
        }

        while(!dq.isEmpty()) {
            Node now = dq.poll();

            if(now.x == ex && now.y == ey) {
                System.out.println(now.m);
                return;
            }

            // 직진
            int nx = now.x + dx[now.d];
            int ny = now.y + dy[now.d];
            if(nx >= 0 && nx < N && ny >= 0 && ny < N && home[nx][ny] != '*') {
                // 현재 경로의 거울 수가 더 적다면, 갱신 
                if(visited[nx][ny][now.d] > now.m) {
                    visited[nx][ny][now.d] = now.m;
                    dq.addFirst(new Node(nx, ny, now.d, now.m));
                }
            }

            if(home[now.x][now.y] == '!') {
                for(int t : new int[]{-1, 1}) {
                    int newDir = (now.d + t + 4) % 4; // 45도 반사 
                    nx = now.x + dx[newDir];
                    ny = now.y + dy[newDir];

                    if(nx >= 0 && nx < N && ny >= 0 && ny < N && home[nx][ny] != '*') {
                        // 거울 설치 했을 때가 더 효율적인 경우 
                        if(visited[nx][ny][newDir] > now.m + 1) {
                            visited[nx][ny][newDir] = now.m + 1;
                            dq.addLast(new Node(nx, ny, newDir, now.m + 1));
                        }
                    }
                }
            }
        }
    }
}
