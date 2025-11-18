package bfs.BOJ_1325;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

    static int N, M;
    static List<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        for(int i=1;i<=N;i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[b].add(a);
        }
        
        for(int i=1;i<=N;i++) {
            bfs(i);
        }

        int max = 0;
        int[] conn = new int[N+1];

        for(int i=1;i<=N;i++) {
            conn[i] = bfs(i);
            max = Math.max(max, conn[i]);
        }

        for(int i=1;i<=N;i++) {
            if(max == conn[i]) sb.append(i).append(" ");
        }

        System.out.println(sb);
    }

    static int bfs(int node) {
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N+1];
        int cnt = 0;

        queue.add(node);
        visited[node] = true;

        while(!queue.isEmpty()) {
            int now = queue.poll();
            cnt++;

            for(int next : graph[now]) {
                if(!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                }
            }
        }
        return cnt;
    }
}