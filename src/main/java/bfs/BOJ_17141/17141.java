package bfs.BOJ_17141;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
    static int min = Integer.MAX_VALUE;
    static int[][] map;
    static List<Node> virus;
    static boolean[] selected;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        virus = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2) {
                    virus.add(new Node(i, j));
                }
            }
        }

        selected = new boolean[virus.size()];
        combine(0, 0);

        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    static void combine(int start, int cnt) {
        if(cnt == M) {
            bfs();
            return;
        }

        for(int i = start; i < virus.size(); i++) {
            selected[i] = true;
            combine(i + 1, cnt + 1);
            selected[i] = false;
        }
    }

    static void bfs() {
        Queue<Node> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        
        for(int i = 0; i < virus.size(); i++) {
            if(selected[i]) {
                Node v = virus.get(i);
                queue.add(new Node(v.x, v.y));
                visited[v.x][v.y] = true;
            }
        }

        int time = 0;

        while(!queue.isEmpty()) {
            if(time >= min) return;
            
            int size = queue.size();
            
            for(int i = 0; i < size; i++) {
                Node now = queue.poll();

                for(int j = 0; j < 4; j++) {
                    int nx = now.x + dx[j];
                    int ny = now.y + dy[j];

                    if(nx < 0 || nx >= N || ny < 0 || ny >= N || 
                       visited[nx][ny] || map[nx][ny] == 1) {
                        continue;
                    }

                    visited[nx][ny] = true;
                    queue.add(new Node(nx, ny));
                }
            }
            
            time++;
        }

        if(allInfected(visited)) {
            min = Math.min(min, time - 1);
        }
    }

    static boolean allInfected(boolean[][] visited) {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(map[i][j] != 1 && !visited[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}