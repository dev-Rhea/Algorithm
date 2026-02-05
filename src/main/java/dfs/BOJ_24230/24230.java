package dfs.BOJ_24230;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {

    static int N, cnt;
    static List<List<Integer>> graph;
    static int[] color;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        graph = new ArrayList<>();
        visited = new boolean[N+1];

        color = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++) {
            color[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0;i<=N;i++) {
            graph.add(new ArrayList<>());
        }

        for(int i=0;i<N-1;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        cnt = color[1] == 0 ? 0 : 1;

        visited[1] = true;
        dfs(1);

        System.out.println(cnt);

    }

    static void dfs(int idx) {
        for(int i=0;i<graph.get(idx).size();i++) {
            int next = graph.get(idx).get(i);
            if(!visited[next]) {
                visited[next] = true;
                if(color[idx] != color[next]) cnt++;
                dfs(next);
            }
        }
    }
}