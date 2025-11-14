package MST.BOJ_17472;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static class Node{
        int x, y;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Island {
        int n;
        List<Node> area;

        Island(int n) {
            this.n = n;
            this.area = new ArrayList<>();
        }

        List<Node> getArea() {
            return area;
        }

        void setArea(List<Node> area) {
            this.area = area;
        }
    }

    static class Bridge {
        int start, end, dist;
        Bridge(int start, int end, int dist) {
            this.start = start;
            this.end = end;
            this.dist = dist;
        }
    }

    static int N, M, num;
    static int[][] map;
    static boolean[][] visited;
    static List<Island> islands;
    static List<Bridge> bridges;
    static int[] parent;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        islands = new ArrayList<>();
        num = 1;
        
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 섬 번호 매기기
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j] == 1 && !visited[i][j]) {
                    Island island = new Island(num);
                    bfs(num, i, j, island.getArea());
                    islands.add(island);
                    num++;
                }
            }
        }

        // 다리 찾기
        bridges = new ArrayList<>();
        for(Island island : islands) {
            for(Node node : island.getArea()) {
                for(int d=0; d<4; d++) {
                    findBridge(island.n, node.x, node.y, d, 0);
                }
            }
        }
        
        // 다리 길이 기준으로 정렬
        bridges.sort((o1, o2) -> (o1.dist - o2.dist));
        
        // 크루스칼 알고리즘으로 MST 구하기
        System.out.println(kruskal());
    }

    static void bfs(int num, int x, int y, List<Node> area) {
        Queue<Node> queue = new LinkedList<>();
        visited[x][y] = true;  // 방문 처리 먼저!
        map[x][y] = num;
        queue.add(new Node(x, y));
        area.add(new Node(x, y));

        while(!queue.isEmpty()) {
            Node now = queue.poll();

            for(int i=0; i<4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if(map[nx][ny] != 1 || visited[nx][ny]) continue;

                visited[nx][ny] = true;
                map[nx][ny] = num;
                queue.add(new Node(nx, ny));
                area.add(new Node(nx, ny));
            }
        }
    }

    static int kruskal() {
        int size = islands.size();
        parent = new int[size+1];
        for(int i=1; i<=size; i++) {
            parent[i] = i;
        }

        int edge = 0;
        int cnt = 0;
        for(Bridge bridge : bridges) {
            int idx1 = bridge.start;
            int idx2 = bridge.end;

            if(union(idx1, idx2)) {
                edge++;
                cnt += bridge.dist;

                if(edge == size - 1) return cnt;
            }
        }
        return -1;
    }

    static boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if(pa == pb) return false;
        parent[pb] = pa;
        return true;
    }

    static int find(int x) {
        if(parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    static void findBridge(int idx, int x, int y, int d, int dist) {
        int nx = x + dx[d];
        int ny = y + dy[d];
        
        if(nx < 0 || nx >= N || ny < 0 || ny >= M || map[nx][ny] == idx) return;
        
        if(map[nx][ny] == 0) {
            findBridge(idx, nx, ny, d, dist+1);
            return;
        }
        
        if(dist >= 2) bridges.add(new Bridge(idx, map[nx][ny], dist));
    }
}