package dijkstra.BOJ_11779;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static class City {
        int city, cost;
        City(int city, int cost) {
            this.city = city;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        List<List<City>> graph = new ArrayList<>();
        for(int i=0;i<=n;i++) {
            graph.add(new ArrayList<>());
        }

        for(int i=0;i<m;i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(u).add(new City(v, c));
        }

        st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        List<Integer> ans = new ArrayList<>();

        int[] dist = new int[n+1];
        int[] prev = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(prev, -1);
        Queue<City> queue = new PriorityQueue<>((c1, c2) -> (c1.cost - c2.cost));

        dist[A] = 0;
        queue.add(new City(A, 0));

        while(!queue.isEmpty()) {
            City now = queue.poll();

            if(dist[now.city] < now.cost) continue;

            for(City next : graph.get(now.city)) {
                if(dist[next.city] > dist[now.city] + next.cost) {
                    dist[next.city] = dist[now.city] + next.cost;
                    prev[next.city] = now.city;
                    queue.add(new City(next.city, dist[next.city]));
                }
            }
        }

        for(int i=B;i!=-1;i=prev[i]) {
            ans.add(i);
        }
        Collections.reverse(ans);

        StringBuilder sb = new StringBuilder();
        sb.append(dist[B]).append('\n');
        sb.append(ans.size()).append('\n');

        for(int i=0;i<ans.size();i++) {
            sb.append(ans.get(i)).append(' ');
        }

        System.out.println(sb);
    }
}