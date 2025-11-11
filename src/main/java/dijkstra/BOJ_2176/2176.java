package dijkstra.BOJ_2176;
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
        int node, edge;
        Node(int node, int edge) {
            this.node = node;
            this.edge = edge;
        }
    }

    static int N, M;
    static List<Node>[] graph;
    static int[] dp;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        dp = new int[N+1];
        dist = new int[N+1];


        for(int i=1;i<=N;i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            graph[A].add(new Node(B, C));
            graph[B].add(new Node(A, C));
        }

        dijkstra(2);

        System.out.println(count(1));
    }

    static void dijkstra(int start) {
        Queue<Node> queue = new PriorityQueue<>((o1, o2) -> (o1.edge - o2.edge));
        queue.add(new Node(start, 0));
        boolean[] visited = new boolean[N+1];
        
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        while(!queue.isEmpty()) {
            Node now = queue.poll();

            if(visited[now.node]) continue;
            visited[now.node] = true;

            for(Node next : graph[now.node]) {
                if(!visited[next.node] && dist[next.node] > dist[now.node] + next.edge) {
                    dist[next.node] = dist[now.node] + next.edge;
                    queue.add(new Node(next.node, dist[next.node]));
                }
            }
        }
    }

    static int count(int start) {
        Arrays.fill(dp, -1);
        return dfs(start);
    }

    static int dfs(int node) {
        if(node == 2) return 1;
        if(dp[node] != -1) return dp[node];
        dp[node] = 0;

        for(Node next : graph[node]) {
            // 다음 노드가 도착점에 더 가까울 때
            if(dist[next.node] < dist[node]) dp[node] += dfs(next.node);
        }
        return dp[node];
    }
}