package dijkstra.BOJ_10282;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static class Flu {
        int node, time;
        Flu(int node, int time) {
            this.node = node;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int t=0;t<T;t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            List<List<Flu>> graph = new ArrayList<>();
            for(int i=0;i<=n;i++) {
                graph.add(new ArrayList<>());
            }

            for(int i=0;i<d;i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());

                graph.get(b).add(new Flu(a, s));
            }

            int[] times = virus(n, c, graph);

            int cnt = 0;
            int max = 0;
            for(int i=1;i<=n;i++) {
                if(times[i] != Integer.MAX_VALUE) {
                    cnt++;
                    max = Math.max(max, times[i]);
                }
            }

            sb.append(cnt).append(" ").append(max).append("\n");
        }
        System.out.println(sb);
    }

    static int[] virus(int n, int start, List<List<Flu>> graph) {
        int[] time = new int[n+1];
        Arrays.fill(time, Integer.MAX_VALUE);
        time[start] = 0;

        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        queue.add(new int[]{start, 0});

        while(!queue.isEmpty()) {
            int[] now = queue.poll();

            if(now[1] > time[now[0]]) continue;

            for(Flu next : graph.get(now[0])) {
                if(time[next.node] > time[now[0]] + next.time) {
                    time[next.node] = time[now[0]] + next.time;
                    queue.add(new int[]{next.node, time[next.node]});
                }
            }
        }

        return time;
    }
}