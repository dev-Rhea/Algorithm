package bfs.BOJ_17142;
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
        int x, y, cnt;
        Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    static int N, M;
    static int[][] map;
    static int min = Integer.MAX_VALUE;
    static boolean[][] visited;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};
    static List<Node> virus = new ArrayList<>();
    static Node[] active;
    static int empty = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        active = new Node[M];

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] == 0) empty++;
                else if(map[i][j] == 2) virus.add(new Node(i, j, 0));
            }
        }

        if(empty == 0) System.out.println(0);
        else {
            select(0, 0);
            System.out.println(min == Integer.MAX_VALUE ? -1 : min);
        }
    }

    static void select(int start, int choice) {

        if(choice == M) {
            infection(empty);
            return;
        }

        for(int i=start;i<virus.size();i++) {
            active[choice] = virus.get(i);
            select(i+1, choice + 1);
        }
    }

    static void infection(int e) {
        Queue<Node> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];

        for(int i=0;i<M;i++) {
            Node node = active[i];
            visited[node.x][node.y] = true;
            queue.add(node);
        }

        while(!queue.isEmpty()) {
            Node now = queue.poll();

            for(int i=0;i<4;i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if(visited[nx][ny] || map[nx][ny] == 1) continue;

                if(map[nx][ny] == 0) e--;
                if(e == 0) {
                    min = Math.min(min, now.cnt + 1);
                    return;
                }

                visited[nx][ny] = true;
                queue.add(new Node(nx, ny, now.cnt+1));
            }
        }
    }
}