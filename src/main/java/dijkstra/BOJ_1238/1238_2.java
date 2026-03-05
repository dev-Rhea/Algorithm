package dijkstra.BOJ_1238;
import java.io.*;
import java.util.*;

class Main {
    static class Node{
        int node, time;
        Node(int node, int time) {
            this.node = node;
            this.time = time;
        }
    }

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        List<List<Node>> graph = new ArrayList<>();
        List<List<Node>> reverse = new ArrayList<>();

        for(int i=0;i<=N;i++) {
            graph.add(new ArrayList<>());
            reverse.add(new ArrayList<>());
        }

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            graph.get(s).add(new Node(e, t));
            reverse.get(e).add(new Node(s, t));
        }

        int[] fromX = dijkstra(X, graph);
        int[] toX  = dijkstra(X, reverse);

        int sum = 0;
        for(int i=1;i<=N;i++) {
            int temp = fromX[i] + toX[i];
            sum = Math.max(sum, temp);
        }

        System.out.println(sum);
    }

    static int[] dijkstra(int x, List<List<Node>> graph) {
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[x] = 0;
        Queue<Node> queue = new PriorityQueue<>((o1, o2) -> (o1.time - o2.time));
        queue.add(new Node(x, 0));

        while(!queue.isEmpty()) {
            Node now = queue.poll();

            if (now.time > dist[now.node]) continue;

            for(Node next : graph.get(now.node)) {
                if(dist[next.node] > dist[now.node] + next.time) {
                    dist[next.node] = dist[now.node] + next.time;
                    queue.add(new Node(next.node, dist[now.node] + next.time));
                }
            }
        }
        return dist;
    }
}