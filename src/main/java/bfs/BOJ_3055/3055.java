package bfs.BOJ_3055;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static class Node {
        int x, y, t;
        Node(int x, int y, int t) {
            this.x = x;
            this.y = y;
            this.t = t;
        }
    }

    static int R, C;
    static char[][] map;
    static int[][] waterTime;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        waterTime = new int[R][C];

        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                waterTime[i][j] = -1;
            }
        }

        int startX = 0, startY = 0;
        Queue<Node> waterQueue = new LinkedList<>();

        for(int i = 0; i < R; i++) {
            String str = br.readLine();
            for(int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'S') {
                    startX = i;
                    startY = j;
                } else if(map[i][j] == '*') {
                    waterTime[i][j] = 0;
                    waterQueue.add(new Node(i, j, 0));
                }
            }
        }

        calculateWaterSpread(waterQueue);

        int result = bfs(startX, startY);
        System.out.println(result == -1 ? "KAKTUS" : result);
    }

    static void calculateWaterSpread(Queue<Node> waterQueue) {
        while(!waterQueue.isEmpty()) {
            Node now = waterQueue.poll();

            for(int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
                if(map[nx][ny] == 'X' || map[nx][ny] == 'D') continue; 
                if(waterTime[nx][ny] != -1) continue;

                waterTime[nx][ny] = now.t + 1;
                waterQueue.add(new Node(nx, ny, now.t + 1));
            }
        }
    }

    static int bfs(int startX, int startY) {
        boolean[][] visited = new boolean[R][C];
        Queue<Node> queue = new LinkedList<>();

        visited[startX][startY] = true;
        queue.add(new Node(startX, startY, 0));

        while(!queue.isEmpty()) {
            Node now = queue.poll();

            if(map[now.x][now.y] == 'D') {
                return now.t;
            }

            for(int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
                if(map[nx][ny] == 'X') continue;
                if(visited[nx][ny]) continue;

                int arrivalTime = now.t + 1;
                if(waterTime[nx][ny] != -1 && waterTime[nx][ny] <= arrivalTime) {
                    continue; 
                }

                visited[nx][ny] = true;
                queue.add(new Node(nx, ny, arrivalTime));
            }
        }

        return -1;
    }
}