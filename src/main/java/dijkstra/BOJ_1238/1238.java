package dijkstra.BOJ_1238;
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
    static class Street {
        int node, time;
        Street(int node, int time) {
            this.node = node;
            this.time = time;
        }
    }

    static List<Street>[] streets, reverse;
    static int N, M, X;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        streets = new ArrayList[N+1];
        reverse = new ArrayList[N+1];

        for(int i=1;i<=N;i++) {
            streets[i] = new ArrayList<>();
            reverse[i] = new ArrayList<>();
        }
        
        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            streets[start].add(new Street(end, t));
            reverse[end].add(new Street(start, t));
        }

        int[] toX = dijkstra(X, reverse);
        int[] back = dijkstra(X, streets);

        int ans = 0;
        for(int i=1;i<=N;i++) {
            ans = Math.max(ans, toX[i] + back[i]);
        }

        System.out.println(ans);
    }

    static int[] dijkstra(int start, List<Street>[] graph) {
        Queue<Street> queue = new PriorityQueue<>((o1, o2) -> (o1.time - o2.time));
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        queue.add(new Street(start, 0));
        dist[start] = 0;

        while(!queue.isEmpty()) {
            Street now = queue.poll();

            if(now.time > dist[now.node]) continue;

            for(Street next : graph[now.node]) {
                if(dist[next.node] > dist[now.node] + next.time) {
                    dist[next.node] = dist[now.node] + next.time;
                    queue.add(new Street(next.node, dist[next.node]));
                }
            }
        }
        return dist;
    }
}