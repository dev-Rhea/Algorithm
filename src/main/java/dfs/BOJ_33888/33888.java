package dfs.BOJ_33888;
import java.io.*;
import java.util.*;

class Main {

    static int N;
    static List<Integer>[] graph;
    static int[] ans = new int[6];
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N+1];
        visited = new boolean[N+1];
        

        for(int i=0;i<=N;i++) {
            graph[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for(int i=0;i<N+3;i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[u].add(v);
            graph[v].add(u);
        }


        for(int i=1;i<=N;i++) {
            if(graph[i].size() == 1) {
                dfs(i);
                ans[5] = i;
            }
        }

        if(ans[1] > ans[3]) {
            int temp = ans[3];
            ans[3] = ans[1];
            ans[1] = temp;
        }

        for(int num : ans) {
            System.out.print(num+" ");
        }
    }

    static void dfs(int node) {
        if(ans[4] == 0 && graph[node].size() == 4) {
            ans[4] = node;
        }
        if(ans[4] != node && graph[node].size() == 4) {
            ans[2] = node;
            return;
        }

        visited[node] = true;

        if(graph[node].size() == 3) {
            if(ans[1] == 0) ans[1] = node;
            else if(ans[0] == 0) ans[0] = node;
            else if(ans[3] == 0) ans[3] = node;
        }
        for(int next : graph[node]) {
            if(!visited[next]) dfs(next);
        }
    }
}