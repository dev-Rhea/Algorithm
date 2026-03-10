package dijkstra.BOJ_5719;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.*;

class Main {
    static class City {
        int city, cost;
        City(int city, int cost) {
            this.city = city;
            this.cost = cost;
        }
    }
    
    static int N, M, S, D;
    static List<List<City>> graph, reverse;
    static boolean[][] deleted;
    static final long INF = Long.MAX_VALUE/2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            if(N == 0 && M == 0) break;

            graph = new ArrayList<>();
            reverse = new ArrayList<>();
            for(int i=0;i<N;i++) {
                graph.add(new ArrayList<>());
                reverse.add(new ArrayList<>());
            }

            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());

            for(int i=0;i<M;i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());

                graph.get(u).add(new City(v, p));
                reverse.get(v).add(new City(u, p));
            }

            deleted = new boolean[N][N];
            long[] dist = dijkstra(S, graph);

            deletePath(dist);

            long[] appr = dijkstra(S, graph);

            sb.append(appr[D] == INF ? -1 : appr[D]).append('\n');
        }

        System.out.print(sb);
    }

    static long[] dijkstra(int start, List<List<City>> cities) {
        long[] dist = new long[N];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        Queue<City> queue = new PriorityQueue<>((o1, o2) -> (o1.cost - o2.cost));
        queue.add(new City(start, 0));

        while(!queue.isEmpty()) {
            City now = queue.poll();

            if((long) now.cost > dist[now.city]) continue;

            for(City next : cities.get(now.city)) {
                if(deleted[now.city][next.city]) continue;
                if(dist[next.city] > dist[now.city] + (long) next.cost) {
                    dist[next.city] = dist[now.city] + (long) next.cost;
                    queue.add(new City(next.city, (int) dist[next.city]));
                }
            }
        }

        return dist;
    }

    static void deletePath(long[] dist) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N];

        queue.add(D);
        visited[D] = true;

        while(!queue.isEmpty()) {
            int now = queue.poll();

            for(City next : reverse.get(now)) {
                if(dist[next.city] != INF && dist[next.city] + (long) next.cost == dist[now]) {
                    deleted[next.city][now] = true;
                    if(!visited[next.city]) {
                        visited[next.city] = true;
                        queue.add(next.city);
                    }
                }
            }
        }
    }
}