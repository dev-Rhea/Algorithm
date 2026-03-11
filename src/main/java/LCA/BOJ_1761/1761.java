package LCA.BOJ_1761;
import java.io.*;
import java.util.*;

class Main {
    static class Node {
        int node, dist;
        Node(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }
    }

    static int N;
    static final int LOG = 16;
    static int[] depth;
    static long[] dist;
    static int[][] parents;
    static List<List<Node>> tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        tree = new ArrayList<>();
        for(int i=0;i<=N;i++) {
            tree.add(new ArrayList<>());
        }

        for(int i=0;i<N-1;i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            tree.get(u).add(new Node(v, d));
            tree.get(v).add(new Node(u, d));
        }

        depth = new int[N+1];
        dist = new long[N+1];
        parents = new int[N+1][LOG];

        boolean[] visited = new boolean[N+1];
        Deque<int[]> stack = new ArrayDeque<>();
        stack.push(new int[]{1, 0, 0});
        visited[1] = true;
        parents[1][0] = 1;

        while(!stack.isEmpty()) {
            int[] now = stack.pop();
            int node = now[0];
            int parent = now[1];
            dist[node] = now[2];

            depth[node] = (node == 1) ? 0 : depth[parent] + 1;
            parents[node][0] = parent == 0 ? 1 : parent;

            for(int k=1;k<LOG;k++) {
                parents[node][k] = parents[parents[node][k-1]][k-1];
            }

            for(Node next : tree.get(node)) {
                if(!visited[next.node]) {
                    visited[next.node] = true;
                    stack.push(new int[]{next.node, node, (int)(dist[node] + next.dist)});
                }
            }
        }

        int M = Integer.parseInt(br.readLine());

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int l = lca(u, v);

            sb.append(dist[u] + dist[v] - 2 * dist[l]).append('\n');
        }
        System.out.print(sb);
    }

    static int lca(int u, int v) {
        if(depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }
        
        int diff = depth[u] - depth[v];
        for(int i=0;i<LOG;i++) {
            if((diff >> i & 1) == 1) u = parents[u][i];
        }

        if(u == v) return u;

        for(int i=LOG-1;i>=0;i--) {
            if(parents[u][i] != parents[v][i]) {
                u = parents[u][i];
                v = parents[v][i];
            }
        }
        return parents[u][0];
    }
}