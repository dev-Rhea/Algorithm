package dijkstra.BOJ_17835;
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
        int city, dist;
        City(int city, int dist) {
            this.city = city;
            this.dist = dist;
        }
    }

    static int N;
    static List<List<City>> cities;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        cities = new ArrayList<>();
        for(int i=0;i<=N;i++) {
            cities.add(new ArrayList<>());
        }

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            cities.get(v).add(new City(u, c));
        }

        long[] cost = new long[N+1];
        Arrays.fill(cost, Long.MAX_VALUE);

        Queue<City> queue = new PriorityQueue<>((o1, o2) -> (o1.dist - o2.dist));
        
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<K;i++) {
            int k = Integer.parseInt(st.nextToken());

            cost[k] = 0;
            queue.add(new City(k, 0));
        }

        while(!queue.isEmpty()) {
            City now = queue.poll();

            if(now.dist > cost[now.city]) continue;

            for(City next : cities.get(now.city)) {
                if(cost[next.city] > cost[now.city] + next.dist) {
                    cost[next.city] = cost[now.city] + next.dist;
                    queue.add(new City(next.city, (int) cost[next.city]));
                }
            }
        }

        long max = 0;
        int num = -1;
        for(int i=1;i<=N;i++) {
            if(cost[i] > max) {
                max = cost[i];
                num = i;
            }
        }
        System.out.println(num);
        System.out.println(max);
    }
}