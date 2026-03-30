package LCA.BOJ_1761;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.*;

class Main {
    static class Node {
        int node, dist;
        Node(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }
    }

    static int N, LOG;
    static List<List<Node>> graph;
    static int[][] parent;
    static int[] depth;
    static long[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        LOG = (int) (Math.log(N)/Math.log(2)) + 2;

        graph = new ArrayList<>();
        for(int i=0;i<=N;i++) {
            graph.add(new ArrayList<>());
        }

        for(int i=0;i<N-1;i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            graph.get(u).add(new Node(v, d));
            graph.get(v).add(new Node(u, d));
        }

        depth = new int[N+1];
        parent = new int[N+1][LOG];
        dist = new long[N+1];

        boolean[] visited = new boolean[N+1];
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(1);
        visited[1] = true;

        while(!queue.isEmpty()) {
            int now = queue.poll();

            for(Node next : graph.get(now)) {
                if(!visited[next.node]) {
                    visited[next.node] = true;
                    parent[next.node][0] = now;
                    depth[next.node] = depth[now] + 1;
                    dist[next.node] = dist[now] + next.dist;
                    queue.add(next.node);
                }
            }
        }

        for(int i=1;i<LOG;i++) {
            for(int j=1;j<=N;j++) {
                parent[j][i] = parent[parent[j][i-1]][i-1];
            }
        }

        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine());
        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int l = lca(s, e);

            sb.append(dist[s] + dist[e] - 2 * dist[l]).append('\n');
        }

        System.out.print(sb);
    }

    static int lca(int start, int end) {
        if(depth[start] < depth[end]) {
            int temp = start;
            start = end;
            end = temp;
        }

        for(int i=LOG-1;i>=0;i--) {
            if(depth[start] - depth[end] >= (1 << i)) start = parent[start][i];
        }

        if(start == end) return start;

        for(int i=LOG-1;i>=0;i--) {
            if(parent[start][i] != parent[end][i]) {
                start = parent[start][i];
                end = parent[end][i];
            }
        }

        return parent[start][0];
    }
}