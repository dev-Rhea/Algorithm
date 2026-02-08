package bfs.BOJ_5022;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.*;

class Main {
    static class Node{
        int x, y, dist;
        Node prev;
        Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.prev = null;
        }
    }

    static int N, M;
    static int[][] map;
    static List<Node> list;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N+1][M+1];

        list = new ArrayList<>();
        
        for(int i=0;i<4;i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            list.add(new Node(x, y, 0));
            map[x][y] = 1;
        }

        int d1 = connect(list.get(0), list.get(1), list.get(2), list.get(3));
        int d2 = connect(list.get(2), list.get(3), list.get(0), list.get(1));

        int min = Math.min(d1, d2);

        System.out.println(min == Integer.MAX_VALUE ? "IMPOSSIBLE" : min);
    }

    static int connect(Node s1, Node e1, Node s2, Node e2) {
        Node ans1 = bfs(s1, e1);
        if(ans1 == null) return Integer.MAX_VALUE;

        List<Node> path = new ArrayList<>();
        Node temp = ans1;
        while(temp != null) {
            path.add(temp);

            if(map[temp.x][temp.y] == 0) map[temp.x][temp.y] = 1;
            temp = temp.prev;
        }

        Node ans2 = bfs(s2, e2);
        int total = Integer.MAX_VALUE;
        if(ans2 != null) total = ans1.dist + ans2.dist;

        for(Node n : path) {
            if(!(n.x == s1.x && n.y == s1.y) && !(n.x == e1.x && n.y == e1.y)) map[n.x][n.y] = 0;
        }

        return total;
    }

    static Node bfs(Node s, Node e) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(s.x, s.y, 0));
        visited = new boolean[N+1][M+1];
        visited[s.x][s.y] = true;

        while(!queue.isEmpty()) {
            Node now = queue.poll();

            if(now.x == e.x && now.y == e.y) return now;

            for(int i=0;i<4;i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx < 0 || nx > N || ny < 0 || ny > M) continue;
                if(visited[nx][ny]) continue;
                if(map[nx][ny] == 1) {
                    if(nx != e.x || ny != e.y) continue;
                }

                visited[nx][ny] = true;
                Node next = new Node(nx, ny, now.dist+1);
                next.prev = now;
                queue.add(next);
            }
        }

        return null;
    }
}