package dijkstra.BOJ_13911;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

class Main {
    static class Node {
        int node, cost;
        Node(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }

    static int V, E;
    static List<Node>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        int[] distM = new int[V+1];
        int[] distS = new int[V+1];
        int[] home = new int[V+1];
        Arrays.fill(distM, Integer.MAX_VALUE);
        Arrays.fill(distS, Integer.MAX_VALUE);
        Arrays.fill(home, -1);

        graph = new ArrayList[V+1];
        for(int i=1;i<=V;i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i=0;i<E;i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph[u].add(new Node(v, w));
            graph[v].add(new Node(u, w));
        }

        Set<Integer> mcSet = new HashSet<>();
        Set<Integer> sbSet = new HashSet<>();
        
        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<M;i++) {
            int mc = Integer.parseInt(st.nextToken());
            mcSet.add(mc);
        }
        
        dijkstra(mcSet, distM);
        
        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<S;i++) {
            int sb = Integer.parseInt(st.nextToken());
            sbSet.add(sb);
        }
        
        dijkstra(sbSet, distS);

        int min = Integer.MAX_VALUE;
        boolean found = false;
        
        for(int i=1;i<=V;i++) {
            if(mcSet.contains(i) || sbSet.contains(i)) continue;
            
            if(distM[i] <= x && distS[i] <= y) {
                home[i] = distM[i] + distS[i];
                min = Math.min(min, home[i]);
                found = true;
            }
        }
        
        System.out.print(found ? min : -1);
    }
    
    static void dijkstra(Set<Integer> sources, int[] dist) {
        Queue<Node> queue = new PriorityQueue<>((o1, o2) -> (o1.cost - o2.cost));
        boolean[] visited = new boolean[V+1];
        
        for(int source : sources) {
            dist[source] = 0;
            queue.add(new Node(source, 0));
        }
        
        while(!queue.isEmpty()) {
            Node now = queue.poll();
            
            if(visited[now.node]) continue;
            visited[now.node] = true;
            
            for(Node next : graph[now.node]) {
                if(!visited[next.node] && dist[next.node] > dist[now.node] + next.cost) {
                    dist[next.node] = dist[now.node] + next.cost;
                    queue.add(new Node(next.node, dist[next.node]));
                }
            }
        }
    }
}