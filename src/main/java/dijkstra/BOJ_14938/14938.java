package dijkstra.BOJ_14938;
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
        int node, l;
        Node(int node, int l) {
            this.node = node;
            this.l = l;
        }
    }

    static int n, m, r;
    static List<List<Node>> graph;
    static int[] items;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        items = new int[n+1];
        
        for(int i=0;i<=n;i++) {
            graph.add(new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=n;i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0;i<r;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, l));
            graph.get(b).add(new Node(a, l));
        }

        int max = 0;
        for(int i=1;i<=n;i++) {
            max = Math.max(dijkstra(i), max);
        }

        System.out.println(max);
    }

    static int dijkstra(int start) {
        Queue<Node> queue = new PriorityQueue<>((o1, o2) -> (o1.l - o2.l));
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        queue.add(new Node(start, 0));
        dist[start] = 0;

        while(!queue.isEmpty()) {
            Node now = queue.poll();

            if(now.l > dist[now.node]) continue;

            for(Node next : graph.get(now.node)) {
                if(dist[now.node] + next.l < dist[next.node]) {
                    dist[next.node] = dist[now.node] + next.l;
                    queue.add(new Node(next.node, dist[next.node]));
                }
            }
        }

        int cnt = 0;
        for(int i=1;i<=n;i++) {
            if(dist[i] <= m) cnt += items[i];
        }
        
        return cnt;
    }
}