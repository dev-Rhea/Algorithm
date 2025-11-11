package dijkstra.BOJ_16681;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
    static class Node implements Comparable<Node> {
        int node;
        long dist;
        
        Node(int node, long dist) {
            this.node = node;
            this.dist = dist;
        }
        
        @Override
        public int compareTo(Node o) {
            if(this.dist < o.dist) {
                return -1;
            } else if(this.dist == o.dist) {
                return 0;
            } else {
                return 1;
            }
        }
    }

    static int N, M, D, E;
    static long[] height;
    static List<Node>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        height = new long[N+1];
        graph = new ArrayList[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            height[i] = Integer.parseInt(st.nextToken());
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b, n));
            graph[b].add(new Node(a, n));
        }

        long[] upDijkstra = bfs(true);
        long[] downDijkstra = bfs(false);
        
        long maxValue = Long.MIN_VALUE;
        for(int i=1; i<=N; i++) {
            if(upDijkstra[i] == Long.MAX_VALUE || downDijkstra[i] == Long.MAX_VALUE) {
                continue;
            }
            
            maxValue = Math.max(maxValue, (height[i] * E) - ((upDijkstra[i] + downDijkstra[i]) * D));
        }
        
        if(maxValue == Long.MIN_VALUE) {
            System.out.println("Impossible");
        } else {
            System.out.println(maxValue);
        }
    }
    
    static long[] bfs(boolean isUp) {
        long[] dist = new long[N+1];
        Arrays.fill(dist, Long.MAX_VALUE);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        
        if(isUp) { // 등산하는 경우
            pq.offer(new Node(1, 0));
            dist[1] = 0;
        } else { // 하산하는 경우
            pq.offer(new Node(N, 0));
            dist[N] = 0;
        }
        
        while(!pq.isEmpty()) {
            Node v = pq.poll();
            
            if(dist[v.node] < v.dist) continue;
            
            for(Node nextV : graph[v.node]) {
                if(height[v.node] >= height[nextV.node]) continue;
                
                if(v.dist + nextV.dist >= dist[nextV.node]) continue;
                
                dist[nextV.node] = v.dist + nextV.dist;
                pq.offer(new Node(nextV.node, dist[nextV.node]));
            }
        }
        
        return dist;
    }
}