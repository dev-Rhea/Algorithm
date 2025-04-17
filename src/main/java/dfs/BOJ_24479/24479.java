package dfs.BOJ_24479;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Main {

    static List<List<Integer>> graph = new ArrayList<>();
    static int[] visited;
    static int no = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        visited = new int[N+1];

        for(int i=0;i<=N;i++) {
            graph.add(new ArrayList<>());
        }

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        for(int i=1;i<=N;i++) {
            Collections.sort(graph.get(i));
        }

        dfs(R);

        for(int i=1;i<=N;i++) {
            sb.append(visited[i]).append("\n");
        }
        System.out.println(sb);        
    }

    static void dfs(int R) {
        visited[R] = no++;

        for(int next : graph.get(R)) {
            if(visited[next] == 0) dfs(next);
        }
    }
}