package bfs.BOJ_34697;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] height = new int[N+1];
        List<List<Integer>> graph = new ArrayList<>();
        graph.add(new ArrayList<>());
        
        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++) {
            height[i] = Integer.parseInt(st.nextToken());
            graph.add(new ArrayList<>());
        }

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        Queue<Integer> queue = new ArrayDeque<>();
        int K = Integer.parseInt(br.readLine());
        boolean[] open = new boolean[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=K;i++) {
            int o = Integer.parseInt(st.nextToken());
            open[o] = true;
            queue.add(o);
        }

        boolean flag = true;
        while(!queue.isEmpty()) {
            int now = queue.poll();
            for(int next : graph.get(now)) {
                if(!open[next] && height[next] > height[now]) {
                    open[next] = true;
                    queue.add(next);
                }
            }
        }

        for(int i=1;i<=N;i++) {
            if(!open[i]) flag = false;
        }

        System.out.print(flag ? "no flood" : "flood");
    }
}