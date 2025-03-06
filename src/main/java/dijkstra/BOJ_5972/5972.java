package dijkstra.BOJ_5972;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

    static class Node {
        int m;
        int cost;

        public Node(int m, int cost) {
            this.m = m;
            this.cost = cost;
        }
    }
    static ArrayList<ArrayList<Node>> map = new ArrayList<>();
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        dist = new int[N + 1];

        for(int i=0;i<=N;i++) {
            map.add(new ArrayList<>());
        }

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            map.get(a).add(new Node(b, c));
            map.get(b).add(new Node(a, c));
        }

        Arrays.fill(dist, Integer.MAX_VALUE);
        dijkstra();
        System.out.println(dist[N]);
    }

    static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<Node>((a, b) -> Integer.compare(a.cost, b.cost));
        pq.add(new Node(1, 0));

        dist[1] = 0;
        while(!pq.isEmpty()) {
            Node now = pq.poll();

            for(Node next : map.get(now.m)) {
                if(dist[next.m] > dist[now.m] + next.cost) {
                    dist[next.m] = dist[now.m] + next.cost;
                    pq.add(new Node(next.m, dist[next.m]));
                }
            }
        }
    }
}