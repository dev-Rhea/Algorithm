package dijkstra.BOJ_13907;
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
    static class City {
        int city, cost, cnt;
        City(int city, int cost, int cnt) {
            this.city = city;
            this.cost = cost;
            this.cnt = cnt;
        }
    }

    static int N, S, D;
    static List<City>[] graph;
    static int[][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        for(int i=0;i<=N;i++) {
            graph[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph[a].add(new City(b, w, 0));
            graph[b].add(new City(a, w, 0));
        }

        dist = new int[N+1][N+1];
        for(int[] d : dist) {
            Arrays.fill(d, Integer.MAX_VALUE);
        }

        dist[S][0] = 0;
        dijkstra(S);
        sb.append(minCost(0)).append('\n');

        int tax = 0;
        for(int i=0;i<K;i++) {
            int p = Integer.parseInt(br.readLine());
            tax += p;

            sb.append(minCost(tax)).append('\n');
        }

        System.out.print(sb);
    }

    static void dijkstra(int start) {
        Queue<City> queue = new PriorityQueue<>((o1, o2) -> (o1.cost - o2.cost));
        queue.add(new City(start, 0, 0));

        while(!queue.isEmpty()) {
            City now = queue.poll();

            if(dist[now.city][now.cnt] < now.cost) continue;
            if(now.cnt >= N-1) continue;

            boolean flag = false;
            for(int i=0;i<now.cnt;i++) {
                if(dist[now.city][i] < now.cost) {
                    flag = true;
                    break;
                }
            }

            if(flag) continue;

            for(City next : graph[now.city]) {

                if(dist[next.city][now.cnt+1] > now.cost + next.cost) {
                    dist[next.city][now.cnt+1] = now.cost + next.cost;
                    queue.add(new City(next.city, dist[next.city][now.cnt+1], now.cnt+1));
                }
            }
        }
    }

    static int minCost(int tax) {
        int min = Integer.MAX_VALUE;

        for(int i=1;i<=N;i++) {
            if(dist[D][i] == Integer.MAX_VALUE) continue;

            int total = dist[D][i] + (i * tax);
            if(total < min) min = total;
        }

        return min;
    }
}