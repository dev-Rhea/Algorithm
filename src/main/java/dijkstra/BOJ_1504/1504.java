package dijkstra.BOJ_1504;
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
        int node;
        int dist;
        Node(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }
    }

    static List<List<Node>> graph;
    static int N, E;
    static final int INF = 200000000;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();

        for(int i=0;i<=N;i++) graph.add(new ArrayList<>());

        for(int i=0;i<E;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        int[] from1 = dijkstra(1);
        int[] fromV1 = dijkstra(v1);
        int[] fromV2 = dijkstra(v2);

        long case1 = (long)from1[v1] + fromV1[v2] + fromV2[N];
        long case2 = (long)from1[v2] + fromV2[v1] + fromV1[N];

        long ans = Math.min(case1, case2);

        if(ans >= INF) System.out.println(-1);
        else System.out.println(ans);
    }

    static int[] dijkstra(int s) {
        int[] dist = new int[N+1];
        Arrays.fill(dist, INF);
        dist[s] = 0;

        Queue<Node> queue = new PriorityQueue<>((o1, o2) -> (o1.dist - o2.dist));
        queue.add(new Node(s, 0));
        
        while(!queue.isEmpty()) {
            Node now = queue.poll();
            if(dist[now.node] < now.dist) continue;

            for(Node next : graph.get(now.node)) {
                if(dist[next.node] > dist[now.node] + next.dist) {
                    dist[next.node] = dist[now.node] + next.dist;
                    queue.add(new Node(next.node, dist[next.node]));
                }
            }
        }
        return dist;
    }
}