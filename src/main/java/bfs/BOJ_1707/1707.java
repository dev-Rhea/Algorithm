package bfs.BOJ_1707;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

    static List<List<Integer>> graph;
    static int[] color;
    static boolean bipartite;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int K = Integer.parseInt(br.readLine());

        while(K-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            graph = new ArrayList<>();

            for(int i=0;i<=V;i++) {
                graph.add(new ArrayList<>());
            }

            for(int i=0;i<E;i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                graph.get(u).add(v);
                graph.get(v).add(u);
            }
            color = new int[V+1];
            bipartite = true;
            
            for(int i=1;i<=V;i++) {
                if(color[i] == 0) bfs(i);
            }
            sb.append(bipartite ? "YES\n" : "NO\n");
        }

        System.out.println(sb);
    }

    static void bfs(int v) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(v);
        color[v] = 1;

        while(!queue.isEmpty()) {
            int now = queue.poll();

             for(int next : graph.get(now)) {
                if(color[next] == 0) {
                    color[next] = -color[now];
                    queue.offer(next);
                }
                else if(color[next] == color[now]) {
                    bipartite = false;
                    return;
                }
             }
        }

        
    }
}