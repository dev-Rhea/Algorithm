package Week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1916 {
    static int N, M;
    static List<List<Node>> graph = new ArrayList<>();
    static int distance[];
    static boolean visit[];
    static int INF = Integer.MAX_VALUE;

    static class Node{
        int node;
        int value;
        public Node(int node, int value){
            this.node = node;
            this.value = value;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        for(int i=0;i<=N;i++){
            graph.add(new ArrayList<>());
        }
        distance = new int[N+1];
        visit = new boolean[N+1];

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.get(u).add(new Node(v, w));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        dijkstra(start);
        System.out.println(distance[end]);

    }

    public static void dijkstra(int s){
        Arrays.fill(distance, INF);
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.value)); // <Node>
        pq.add(new Node(s, 0));
        distance[s] = 0;

        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(visit[cur.node]) continue;
            visit[cur.node] = true;

            for(Node n : graph.get(cur.node)){
                if(distance[n.node] > distance[cur.node] + n.value){
                    distance[n.node] = distance[cur.node] + n.value;
                    pq.add(new Node(n.node, distance[n.node]));
                }
            }
        }
    }
}
