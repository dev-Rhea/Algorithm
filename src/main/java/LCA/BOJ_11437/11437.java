package LCA.BOJ_11437;
import java.io.*;
import java.util.*;

class Main {

    static int N;
    static List<List<Integer>> graph;
    static int[] depth;
    static int[] parents;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        for(int i=0;i<=N;i++) {
            graph.add(new ArrayList<>());
        }

        for(int i=0;i<N-1;i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        depth = new int[N+1];
        parents = new int[N+1];
        visited = new boolean[N+1];
        dfs(1, 0);

        int M = Integer.parseInt(br.readLine());
        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            sb.append(lca(u, v)).append("\n");
        }

        System.out.println(sb);
    }

    static void dfs(int node, int dep) {
        visited[node] = true;
        depth[node] = dep;

        for(int next : graph.get(node)) {
            if(!visited[next]) {
                parents[next] = node;
                dfs(next, dep+1);
            }
        }
    }

    static int lca(int a, int b) {
        if(depth[a] < depth[b]) {
            int temp = a;
            a = b;
            b = temp; 
        }

        while(depth[a] != depth[b]) {
            a = parents[a];
        }

        while(a != b) {
            a = parents[a];
            b = parents[b];
        }

        return a;
    }
}