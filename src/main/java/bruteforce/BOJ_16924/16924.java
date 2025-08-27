package bruteforce.BOJ_16924;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static class Node {
        int x, y, s;
        Node(int x, int y, int s) {
            this.x = x;
            this.y = y;
            this.s = s;
        }
    }
    
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int N, M;
    static char[][] map, visited;
    static Queue<Node> ans = new LinkedList<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new char[N][M];
        visited = new char[N][M];
        
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j);
            }
        }
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == '*') {
                    int size = getSize(i, j, 1);
                    if (size > 0) {
                        mark(i, j, size);
                        ans.add(new Node(i + 1, j + 1, size));
                    }
                }
            }
        }
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == '*' && (map[i][j] != visited[i][j])) {
                    System.out.println(-1);
                    return;
                }
            }
        }
        
        System.out.println(ans.size());
        for (Node node : ans) {
            System.out.println(node.x + " " + node.y + " " + node.s);
        }
    }
    
    static int getSize(int x, int y, int s) {
        for (int i = 0; i < 4; i++) {
            int tx = x + dx[i] * s;
            int ty = y + dy[i] * s;
            
            if (tx < 0 || ty < 0 || tx >= N || ty >= M || map[tx][ty] == '.') {
                return s - 1;
            }
        }
        return getSize(x, y, s + 1);
    }
    
    static void mark(int x, int y, int s) {
        visited[x][y] = '*';
        
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j <= s; j++) {
                int tx = x + dx[i] * j;
                int ty = y + dy[i] * j;
                visited[tx][ty] = '*';
            }
        }
    }
}