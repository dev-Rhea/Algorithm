package bfs.BOJ_10159;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        List<List<Integer>> graph = new ArrayList<>();
        List<List<Integer>> hparg = new ArrayList<>();
        for(int i=0;i<=N;i++) {
            graph.add(new ArrayList<>());
            hparg.add(new ArrayList<>());
        }

        for(int i=0;i<M;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            hparg.get(b).add(a);
        }

        for(int i=1;i<=N;i++) {
            int f = bfs(i, graph);
            int b = bfs(i, hparg);
            sb.append(N-1-f-b).append('\n');
        }

        System.out.print(sb);
    }

    static int bfs(int start, List<List<Integer>> list) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N+1];
        
        queue.add(start);
        visited[start] = true;
        int cnt = 0;

        while(!queue.isEmpty()) {
            int now = queue.poll();

            for(int next : list.get(now)) {
                if(!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                    cnt++;
                }
            }
        }
        return cnt;
    }
}