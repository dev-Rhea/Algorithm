package dijkstra.BOJ_17396;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static class Node {
        int node;
        long time;
        Node(int node, long time) {
            this.node = node;
            this.time = time;
        }
    }

    static int N;
    static boolean[] view;
    static List<Node>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        view = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            int v = Integer.parseInt(st.nextToken());
            if(v == 1) view[i] = true;
        }

        graph = new ArrayList[N];
        for(int i=0;i<N;i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long t = Long.parseLong(st.nextToken());

            graph[a].add(new Node(b, t));
            graph[b].add(new Node(a, t));
        }

        System.out.println(dijkstra(0));
    }

    static long dijkstra(int start) {
        long[] dist = new long[N];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[start] = 0;

        Queue<Node> queue = new PriorityQueue<>(Comparator.comparingLong(o -> o.time));
        queue.add(new Node(start, 0));

        while(!queue.isEmpty()) {
            Node now = queue.poll();

            if(dist[now.node] < now.time) continue;
            if(now.node == N-1) return dist[N-1];

            for(Node next : graph[now.node]) {
                if(view[next.node] && next.node != N - 1) continue;

                if(dist[next.node] > dist[now.node] + next.time) {
                    dist[next.node]  = dist[now.node] + next.time;
                    queue.add(new Node(next.node, dist[next.node]));
                }
            }
        }
        
        return dist[N-1] == Long.MAX_VALUE ? -1 : dist[N-1];
    }
}