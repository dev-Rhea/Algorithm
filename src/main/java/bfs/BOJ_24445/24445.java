package bfs.BOJ_24445;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

    static int N, M, R;
    static Queue<Integer>[] graph;
    static int[] order;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        order = new int[N + 1];
        graph = new PriorityQueue[N+1];
        for (int i = 0; i <= N; i++) graph[i] = new PriorityQueue<>((o1, o2) -> (o2-o1));

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }

        bfs(R);

        for (int i = 1; i <= N; i++) {
            System.out.println(order[i]);
        }
    }

    static void bfs(int node) {
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> queue = new ArrayDeque<>();
        int cnt = 1;

        visited[node] = true;
        order[node] = cnt++;
        queue.add(node);

        while (!queue.isEmpty()) {
            int now = queue.poll();

            while (!graph[now].isEmpty()) {
                int n = graph[now].poll();
                if (!visited[n]) {
                    visited[n] = true;
                    order[n] = cnt++;
                    queue.add(n);
                }
            }
        }
    }
}
