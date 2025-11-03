package dijkstra.BOJ_9370;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {

    static class Node {
        int node, d;
        Node(int node, int d) {
            this.node = node;
            this.d = d;
        }
    }

    static final int INF = 200_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());

        for(int test=0; test<T; test++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            List<Node>[] map = new ArrayList[n+1];

            for(int i=1; i<=n; i++) {
                map[i] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            for(int i=0; i<m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken()) << 1;
                
                if((a == g && b == h) || (a == h && b == g)) {
                    d--;
                }
                
                map[a].add(new Node(b, d));
                map[b].add(new Node(a, d));
            }

            int[] dist = dijkstra(s, map, n);
            
            Set<Integer> destinations = new TreeSet<>();
            
            for(int i=0; i<t; i++) {
                int e = Integer.parseInt(br.readLine());
                
                // 최단 경로가 존재하고, 그 경로가 홀수 가중치 -> g-h 도로를 지난 것
                if(dist[e] != INF && dist[e] % 2 == 1) {
                    destinations.add(e);
                }
            }
            
            for(int dest : destinations) {
                sb.append(dest).append(" ");
            }
            sb.append("\n");
        }
        
        System.out.print(sb);
    }

    static int[] dijkstra(int start, List<Node>[] map, int n) {
        int[] dist = new int[n+1];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> (o1.d - o2.d));
        pq.add(new Node(start, 0));
        
        while(!pq.isEmpty()) {
            Node now = pq.poll();
            
            if(dist[now.node] < now.d) continue;
            
            for(Node next : map[now.node]) {
                int nextDist = now.d + next.d;
                
                if(nextDist < dist[next.node]) {
                    dist[next.node] = nextDist;
                    pq.add(new Node(next.node, nextDist));
                }
            }
        }
        
        return dist;
    }
}
