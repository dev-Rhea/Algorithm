package bfs.BOJ_24444;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

    static List<List<Integer>> graph = new ArrayList<>();
    static int[] order;
    static boolean[] visited;
    static int N, M, R;

    public static void main(String[] args) throws IOException {
        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        order = new int[N+1];
        visited = new boolean[N+1];

        for(int i=0;i<N;i++) {
            graph.add(new ArrayList<>());
        }

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        for(List<Integer> list : graph) Collections.sort(list);

        bfs(R);

        for(int i=1;i<=N;i++) sb.append(order[i]).append("\n");

        System.out.println(sb);
    }

    static void bfs(int r) {
        Queue<Integer> queue = new LinkedList<>();
        int cnt = 1;

        queue.add(r);
        visited[r] = true;
        order[r] = cnt++;
        
        while(!queue.isEmpty()) {
            int now = queue.poll();

            for(int next : graph.get(now)) {
                if(!visited[next]) {
                    visited[next] = true;
                    order[next] = cnt++;
                    queue.add(next);
                }
            }
        }
    }
}