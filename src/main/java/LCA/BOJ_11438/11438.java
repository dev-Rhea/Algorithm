package LCA.BOJ_11438;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

    static int N, h;
    static int[] depth;
    static int[][] parents;
    static List<List<Integer>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        h = 1;

        while((1 << h) <= N) h++;

        depth = new int[N+1];
        parents = new int[N+1][h];
        boolean[] visited = new boolean[N+1];
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

        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        visited[1] = true;
        depth[1] = 0;
        parents[1][0] = 1;

        while(!queue.isEmpty()) {
            int now = queue.poll();

            for(int next : graph.get(now)) {
                if(!visited[next]) {
                    visited[next] = true;
                    depth[next] = depth[now] + 1;
                    parents[next][0] = now;
                    queue.add(next);
                }
            }
        }

        for(int j=1;j<h;j++) {
            for(int i=1;i<=N;i++) {
                parents[i][j] = parents[parents[i][j-1]][j-1];
            }
        }

        int M = Integer.parseInt(br.readLine());
        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            sb.append(lca(u, v)).append("\n");
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
        for(int i=0;i<h;i++) {
            if((diff >> i & 1) == 1) u = parents[u][i];
        }

        if(u == v) return u;

        for(int i=h-1;i>=0;i--) {
            if(parents[v][i] != parents[u][i]) {
                u = parents[u][i];
                v = parents[v][i];
            }
        }

        return parents[u][0];
    }
}