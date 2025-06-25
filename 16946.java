import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
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
    static int[][] map, copy;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};
    static List<Node> wall = new ArrayList<>();
    static Map<Integer, Integer> group = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        copy = new int[N][M];

        for(int i=0;i<N;i++) {
            String str = br.readLine();
            for(int j=0;j<M;j++) {
                map[i][j] = str.charAt(j) - '0';

                if(map[i][j] == 1) wall.add(new Node(i, j));
            }
        }

        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                copy[i][j] = map[i][j];
            }
        }

        int num = 2;
        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                if(map[i][j] == 0) {
                    int size = bfs(i, j, num);
                    group.put(num, size);
                    num++;
                }
            }
        }

        for(Node node : wall) {
            int total = 1; 
            
            Set<Integer> adj = new HashSet<>();
            for(int i=0;i<4;i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];
                
                if(nx >= 0 && nx < N && ny >= 0 && ny < M && map[nx][ny] >= 2) {
                    adj.add(map[nx][ny]);
                }
            }
            
            for(int g : adj) {
                total += group.get(g);
            }
            
            copy[node.x][node.y] = total % 10;
        }
        
        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                System.out.print(copy[i][j]);
            }
            System.out.println();
        }
    }

    static int bfs(int x, int y, int n) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(x, y));
        map[x][y] = n;
        int cnt = 1;

        while(!queue.isEmpty()) {
            Node now = queue.poll();

            for(int i=0;i<4;i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

                if(map[nx][ny] == 0) { 
                    map[nx][ny] = n;
                    queue.add(new Node(nx, ny));
                    cnt++;
                }
            }
        }

        return cnt;
    }
}