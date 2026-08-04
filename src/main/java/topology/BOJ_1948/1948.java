import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static class City{
        int city, time;
        City(int city, int time) {
            this.city = city;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        List<List<City>> graph = new ArrayList<>();
        List<List<City>> hparg = new ArrayList<>();
        for(int i=0;i<=n;i++) {
            graph.add(new ArrayList<>());
            hparg.add(new ArrayList<>());
        }

        int[] degree = new int[n+1];
        for(int i=0;i<m;i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(u).add(new City(v, w));
            hparg.get(v).add(new City(u, w));
            degree[v]++;
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int rome = Integer.parseInt(st.nextToken());

        long[] dist = new long[n+1];
        Queue<Integer> queue = new LinkedList<>();
        for(int i=1;i<=n;i++) {
            if(degree[i] == 0) queue.add(i);
        }

        while(!queue.isEmpty()) {
            int now = queue.poll();

            for(City next : graph.get(now)) {
                dist[next.city] = Math.max(dist[next.city], dist[now] + next.time);
                if(--degree[next.city] == 0) queue.add(next.city);
            }
        }

        boolean[] visited = new boolean[n+1];
        Queue<Integer> que = new LinkedList<>();
        que.add(rome);
        visited[rome] = true;
        int cnt = 0;

        while(!que.isEmpty()) {
            int now = que.poll();

            for(City prev : hparg.get(now)) {
                if(dist[now] == dist[prev.city] + prev.time) {
                    cnt++;
                    if(!visited[prev.city]) {
                        visited[prev.city] = true;
                        que.add(prev.city);
                    }
                }
            }
        }

        System.out.println(dist[rome]);
        System.out.println(cnt);
    }
}
