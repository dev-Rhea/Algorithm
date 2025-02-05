package pre.Week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1753 {
    static int V, E, K;
    static List<List<Node>> graph;
    static boolean[] visit;
    static int[] dijkstra;

    static class Node implements Comparable<Node> {
        int node;
        int value;

        Node(int node, int value){
            this.node = node;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.value, o.value);
        }
    }

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>(V + 1);
        for(int i = 0; i <= V; i++) graph.add(new ArrayList<>());
        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.get(u).add(new Node(v, w));
        }
        dijkstra(K);

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= V; i++){
            if(dijkstra[i] == Integer.MAX_VALUE) sb.append("INF").append("\n");
            else sb.append(dijkstra[i]).append("\n");
        }
        System.out.println(sb);
    }

    static void dijkstra(int start){
        visit = new boolean[V + 1];
        dijkstra = new int[V + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>();

        Arrays.fill(dijkstra, Integer.MAX_VALUE);
        dijkstra[start] = 0;
        pq.add(new Node(start, 0));

        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(visit[cur.node]) continue;
            visit[cur.node] = true;

            for(Node n : graph.get(cur.node)){
                if(!visit[n.node] && dijkstra[n.node] > dijkstra[cur.node] + n.value){
                    dijkstra[n.node] = dijkstra[cur.node] + n.value;
                    pq.add(new Node(n.node, dijkstra[n.node]));
                }
            }
        }
    }
}
