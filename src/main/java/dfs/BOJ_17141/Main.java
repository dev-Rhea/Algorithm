package dfs.BOJ_17141;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Node {
        int x, y, time;
        Node(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
    
    static int[][] map;
    static int N, M;
    static int[] choice;
    static List<Node> virus = new ArrayList<>();
    static boolean[][] selected;
    static int min = Integer.MAX_VALUE;
    static int uninfected = 0;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        choice = new int[M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] == 2) virus.add(new Node(i, j, 0));
                else if(map[i][j] == 0) uninfected += 1;
            }
        }

        uninfected += virus.size() - M;

        dfs(0, 0);

        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    static void dfs(int idx, int cnt) {
        if(cnt == M) {
            int time = bfs();
            if(time != -1) min = Math.min(min, time);
            return;
        }

        for(int i = idx; i < virus.size(); i++) {
            choice[cnt] = i;
            dfs(i + 1, cnt + 1); // i+1. cnt+1 -> i+1, cnt+1
        }
    }

    static int bfs() {
        Queue<Node> queue = new LinkedList<>();
        selected = new boolean[N][N];

        for(int i = 0; i < M; i++) {
            int now = choice[i];
            Node node = virus.get(now);

            queue.add(node);
            selected[node.x][node.y] = true;
        }

        int t = 0;
        int infected = 0;

        while(!queue.isEmpty()) {
            Node node = queue.poll();
            
            t = node.time;

            for(int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if(nx < 0 || nx >= N || ny < 0 || ny >= N || selected[nx][ny]) continue;

                if(map[nx][ny] != 1) {
                    selected[nx][ny] = true;
                    infected += 1;
                    queue.add(new Node(nx, ny, t + 1));
                }
            }
        }

        return infected == uninfected ? t : -1;
    }
}