package dijkstra.BOJ_2211;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

    static class Net {
        int com, conn;
        Net(int com, int conn) {
            this.com = com;
            this.conn = conn;
        }
    }

    static List<Net>[] graph;
    static int N, M, cnt;
    static StringBuilder sb;
    static int[] dist;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        graph = new ArrayList[N+1];
        for(int i=1; i<=N; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new Net(b, c));
            graph[b].add(new Net(a, c));
        }

        dist = new int[N+1];
        parent = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        dijkstra(1);
        
        int count = 0;
        for(int i=2; i<=N; i++) {
            if(parent[i] != 0) {
                count++;
            }
        }
        
        sb.append(count).append("\n");
        for(int i=2; i<=N; i++) {
            if(parent[i] != 0) {
                sb.append(parent[i]).append(" ").append(i).append("\n");
            }
        }
        
        System.out.print(sb);
    }

    static void dijkstra(int start) {
        PriorityQueue<Net> queue = new PriorityQueue<>((o1, o2) -> (o1.conn - o2.conn));
        queue.add(new Net(start, 0));
        
        dist[start] = 0;
        
        while(!queue.isEmpty()) {
            Net now = queue.poll();
            
            if(dist[now.com] < now.conn) continue;
            
            for(Net next : graph[now.com]) {
                if(dist[next.com] > dist[now.com] + next.conn) {
                    dist[next.com] = dist[now.com] + next.conn;
                    parent[next.com] = now.com;
                    queue.add(new Net(next.com, dist[next.com]));
                }
            }
        }
    }
}