package dijkstra.BOJ_1753;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static class Node {
        int node, value;
        Node(int node, int value) {
            this.node = node;
            this.value = value;
        }
    }

    static int V, E;
    static List<List<Node>> graph;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        for(int i=0;i<=V;i++) {
            graph.add(new ArrayList<>());
        }

        for(int i=0;i<E;i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(u).add(new Node(v, w));
        }

        dijkstra(K);

        System.out.println(sb);
    }

    static void dijkstra(int start) {
        int[] dist = new int[V+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        Queue<Node> queue = new PriorityQueue<>((o1, o2) -> (o1.value - o2.value));
        queue.add(new Node(start, 0));

        while(!queue.isEmpty()) {
            Node now = queue.poll();

            for(Node next : graph.get(now.node)) {
                if(dist[next.node] > dist[now.node] + next.value) {
                    dist[next.node] = dist[now.node] + next.value;
                    queue.add(new Node(next.node, dist[next.node]));
                }
            }
        }

        for(int i=1;i<=V;i++) {
            if(dist[i] == Integer.MAX_VALUE) sb.append("INF").append("\n");
            else sb.append(dist[i]).append("\n");
        }
    }
}