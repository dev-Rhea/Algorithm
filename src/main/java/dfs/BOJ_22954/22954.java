package dfs.BOJ_22954;
import java.io.*;
import java.util.*;

class Main {
    static class Node {
        int node, edge;
        Node(int node, int edge) {
            this.node = node;
            this.edge = edge;
        }
    }

    static int N, M, cnt = 0;
    static List<List<Node>> graph;
    static boolean[] visited;
    static List<Integer> edges, nodes;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N + 1];

        if(N <= 2) {
            System.out.println(-1);
            return;
        }

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        edges = new ArrayList<>();
        nodes = new ArrayList<>();

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(new Node(v, i));
            graph.get(v).add(new Node(u, i));
        }


        for(int i=1;i<=N;i++) {
            if(visited[i]) continue;

            if(cnt == 2) {
                System.out.println(-1);
                return;
            }
            visited[i] = true;
            cnt++;

            edges = new ArrayList<>();
            nodes = new ArrayList<>();
            nodes.add(i);
            dfs(i);

            if(edges.size() == N-1) {
                cal();
                break;
            }

            if(cnt == 1) {
                if(2*nodes.size() == N) {
                    System.out.println(-1);
                    return;
                }

                sb.append(nodes.size()).append(" ").append(N-nodes.size()).append("\n");
            }

            for(int node : nodes) {
                sb.append(node).append(" ");
            }
            sb.append("\n");

            for(int edge : edges) {
                sb.append(edge).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static void cal() {
        sb.append(N-1).append(" ").append(1);
        sb.append("\n");

        for(int i=0;i<nodes.size()-1;i++) {
            sb.append(nodes.get(i)).append(" ");
        }
        sb.append("\n");

        for(int i=0;i<edges.size()-1;i++) {
            sb.append(edges.get(i)).append(" ");
        }
        sb.append("\n");

        sb.append(nodes.get(nodes.size()-1));
        sb.append("\n");
    }

    static void dfs(int idx) {
        List<Node> list = graph.get(idx);
        for(Node n : list) {
            if(visited[n.node]) continue;
            visited[n.node] = true;
            edges.add(n.edge);
            nodes.add(n.node);

            dfs(n.node);
        }
    }
}