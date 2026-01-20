package dfs.BOJ_2458;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {
    
    static int N, ans;
    static List<List<Integer>> big, small;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        big = new ArrayList<>();
        small = new ArrayList<>();
        for(int i=0;i<=N;i++) {
            big.add(new ArrayList<>());
            small.add(new ArrayList<>());
        }

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            big.get(a).add(b);
            small.get(b).add(a);
        }

        int ans = 0;
        for(int i=1;i<=N;i++) {
            visited = new boolean[N+1];
            int b = dfs(i, big) - 1;

            visited = new boolean[N+1];
            int s = dfs(i, small) - 1;

            if((b + s) == N-1) ans++;
        }
        System.out.println(ans);
    }

    static int dfs(int idx, List<List<Integer>> graph) {
        visited[idx] = true;
        int cnt = 1;

        for(int next : graph.get(idx)) {
            if(!visited[next]) cnt += dfs(next, graph);
        }
        return cnt;
    }
}