import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int t=0;t<T;t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[] times = new int[N+1];
            List<List<Integer>> graph = new ArrayList<>();
            int[] degree = new int[N+1];

            for(int i=0;i<=N;i++) {
                graph.add(new ArrayList<>());
            }

            st = new StringTokenizer(br.readLine());
            for(int i=1;i<=N;i++) {
                times[i] = Integer.parseInt(st.nextToken());
            }

            for(int i=0;i<K;i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                graph.get(x).add(y);
                degree[y]++;
            }

            int W = Integer.parseInt(br.readLine());
            int[] start = new int[N+1];

            Queue<Integer> queue = new LinkedList<>();
            for(int i=1;i<=N;i++) {
                if(degree[i] == 0) queue.add(i);
            }

            while(!queue.isEmpty()) {
                int now = queue.poll();

                if(now == W) break;

                for(int next : graph.get(now)) {
                    start[next] = Math.max(start[next], start[now] + times[now]);
                    if(--degree[next] == 0) queue.add(next);
                }
            }
            sb.append(start[W] + times[W]).append('\n');
        }
        System.out.print(sb);
    }
}