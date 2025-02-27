package dijkstra.BOJ_1238;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Edge {

        int node;
        int time;

        Edge(int node, int time) {
            this.node = node;
            this.time = time;
        }
    }

    static int N, M, X;
    static ArrayList<Edge>[] to, from;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        to = new ArrayList[N + 1];
        from = new ArrayList[N + 1];

        int max = 0;

        for (int i = 0; i <= N; i++) {
            to[i] = new ArrayList<>();
            from[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            to[a].add(new Edge(b, t)); // a-> b
            from[b].add(new Edge(a, t)); // b -> a
        }

        int[] distTo = find(to, X);
        int[] distFrom = find(from, X);

        for (int i = 1; i <= N; i++) {
            max = Math.max(max, distTo[i] + distFrom[i]);
        }
        System.out.println(max);
    }

    private static int[] find(ArrayList<Edge>[] graph, int start) {
        int[] dist = new int[N + 1]; // 최단 거리 저장

        for (int i = 1; i <= N; i++) {
            dist[i] = Integer.MAX_VALUE; // 거리 초기화
        }
        dist[start] = 0;

        boolean[] visited = new boolean[N + 1];
        PriorityQueue<Integer> pq = new PriorityQueue<>(
            Comparator.comparingInt(s -> dist[s]));
        pq.offer(start);

        while (!pq.isEmpty()) {
            int now = pq.poll(); // 가장 가까운 노드
            if (visited[now]) {
                continue;
            }
            visited[now] = true; // 방문 처리

            // 현재 지점에서 갈 수 있는 모든 노드 확인
            for (Edge e : graph[now]) {
                int next = e.node;
                int time = e.time;
                if (!visited[next] && dist[now] + time < dist[next]) {
                    dist[next] = dist[now] + time;
                    pq.offer(next);
                }
            }
        }
        return dist;
    }
}
