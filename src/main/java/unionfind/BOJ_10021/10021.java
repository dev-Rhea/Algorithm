package unionfind.BOJ_10021;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Main {
    static class Edge implements Comparable<Edge> {
        int u, v;
        long cost;
        Edge(int u, int v, long cost) {
            this.u = u;
            this.v = v;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Long.compare(this.cost, o.cost);
        }
    }

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[][] fields = new int[N][2];

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            fields[i][0] = Integer.parseInt(st.nextToken());
            fields[i][1] = Integer.parseInt(st.nextToken());
        }
        
        List<Edge> edges = new ArrayList<>();

        for(int i=0;i<N;i++) {
            for(int j=i+1;j<N;j++) {
                long dx = fields[i][0] - fields[j][0];
                long dy = fields[i][1] - fields[j][1];
                long cost = dx * dx + dy * dy;

                if(cost >= C) edges.add(new Edge(i, j, cost));
            }
        }

        Collections.sort(edges);
        parent = new int[N];

        for(int i=0;i<N;i++) parent[i] = i;

        int connection = 0; // 연결 엣지 수 
        long ans = 0;

        for(Edge e : edges) {
            // 사이클이 생기지 않는 경우 
            if(union(e.u, e.v)) {
                ans += e.cost;
                connection++;
                // 모든 엣지 순회 완료 
                if(connection == N - 1) break;
            }
        }

        if(connection != N - 1) System.out.println(-1);
        else System.out.println(ans);
    }

    static int find(int x) {
        if(parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    static boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        // 같은 집합이라면 제외 
        if(pa == pb) return false;

        parent[pa] = pb;
        return true;
    }
}