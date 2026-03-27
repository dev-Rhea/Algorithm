package dijkstra.BOJ_1854;
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
    static class Street{
        int city, time;
        Street(int city, int time) {
            this.city = city;
            this.time = time;
        }
    }

    static int n, k;
    static List<List<Street>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for(int i=0;i<=n;i++) {
            graph.add(new ArrayList<>());
        }

        for(int i=0;i<m;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Street(b, c));
        }

        int[] dist = dijkstra(1);

        StringBuilder sb = new StringBuilder();
        for(int i=1;i<=n;i++) {
            sb.append(dist[i]).append('\n');
        }

        System.out.print(sb);
    }

    static int[] dijkstra(int start) {
        Queue<Street> queue = new PriorityQueue<>((o1, o2) -> (o1.time - o2.time));
        int[] cnt = new int[n+1]; // 도시별 방문 횟수 
        int[] res = new int[n+1]; // k 번째 경로 값 저장 
        Arrays.fill(res, -1);

        queue.add(new Street(start, 0));

        while(!queue.isEmpty()) {
            Street now = queue.poll();

            cnt[now.city]++;
            if(cnt[now.city] > k) continue;
            if(cnt[now.city] == k) res[now.city] = now.time;

            for(Street next : graph.get(now.city)) {
                if(cnt[next.city] < k) {
                    queue.add(new Street(next.city, now.time + next.time));
                }
            }
        }

        return res;
    }
}