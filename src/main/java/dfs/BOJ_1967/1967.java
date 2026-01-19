package dfs.BOJ_1967;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {
    static class Node {
        int next, cost;
        Node(int next, int cost) {
            this.next = next;
            this.cost = cost;
        }
    }

    static int n, max;
    static List<Node>[] graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();
        if (line == null) return;
        
        n = Integer.parseInt(line);
        graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) { 
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            graph[u].add(new Node(v, dist));
            graph[v].add(new Node(u, dist)); 
        }

        max = 0;
        for(int i=1;i<=n;i++) {
            visited = new boolean[n + 1];
            dfs(i, 0);
        }
        
        System.out.println(max);
    }

    static void dfs(int idx, int totalDist) {
        visited[idx] = true;
        max = Math.max(max, totalDist);

        for (Node node : graph[idx]) {
            if (!visited[node.next]) {
                dfs(node.next, totalDist + node.cost);
            }
        }
    }
}