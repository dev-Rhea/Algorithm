package MST.BOJ_1647;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Main {
    static class Home implements Comparable<Home>{
        int a, b, cost;
        Home(int a, int b, int cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }

        @Override
        public int compareTo(Home h) {
            return this.cost - h.cost;
        }
    }

    static int N, M;
    static List<Home> graph;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        parent = new int[N+1];
        for(int i=1;i<=N;i++) {
            parent[i] = i;
        }

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.add(new Home(a, b, c));
        }

        Collections.sort(graph);

        System.out.println(kruskal());
    }

    static int kruskal() {
        int sum = 0;
        int max = 0;

        for(Home h : graph) {
            if(find(h.a) != find(h.b)) {
                union(h.a, h.b);
                sum += h.cost;
                max = Math.max(max, h.cost);
            }
        }
        return sum - max;
    }

    static int find(int x) {
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a != b) parent[b] = a;
    }
}