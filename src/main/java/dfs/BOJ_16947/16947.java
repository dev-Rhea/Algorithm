package dfs.BOJ_16947;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {

    static List<List<Integer>> graph;
    static int[] dist;
    static int[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());

        dist = new int[N+1];
        graph = new ArrayList<>();
        visited = new int[N+1];

        for(int i=0;i<=N;i++) {
            graph.add(new ArrayList<>());
        }

        for(int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        dfs(1, -1);

        for(int i=1;i<=N;i++) {
            if(visited[i] != 2) visited[i] = -1;
        }

        for(int i=1;i<=N;i++) {
            if(visited[i] == 2) { 
                for(int next : graph.get(i)) {
                    if(visited[next] == -1) {
                        dist[next] = 1;
                        find(2, next, i);
                    }
                }
            }
        }

        for(int i=1;i<=N;i++) {
            System.out.print(dist[i] + " ");
        }
    }

    static int dfs(int idx, int parent) {
        if(visited[idx] == 1) return idx;

        visited[idx] = 1;

        for(int next : graph.get(idx)) {
            if(next == parent) continue;

            int res = dfs(next, idx);

            if(res == -2) return -2;

            if(res >= 0) {
                visited[idx] = 2;
                if(idx == res) return -2;
                else return res;
            }
        }

        return -1;
    }

    static void find(int d, int node, int parent) {
        for(int next : graph.get(node)) {
            if(next == parent) continue;

            dist[next] = d;
            find(d + 1, next, node);
        }
    }
}