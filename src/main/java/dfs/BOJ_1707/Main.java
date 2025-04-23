package dfs.BOJ_1707;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static List<List<Integer>> graph;
    static int[] color;
    static boolean isBipartite;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int K = Integer.parseInt(br.readLine());

        while (K-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            graph = new ArrayList<>();
            for (int i = 0; i <= V; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                graph.get(u).add(v);
                graph.get(v).add(u);
            }

            color = new int[V + 1];
            isBipartite = true;

            for (int i = 1; i <= V; i++) {
                if (color[i] == 0) {
                    dfs(i, 1);
                }
            }

            sb.append(isBipartite ? "YES\n" : "NO\n");
        }

        System.out.println(sb);
    }

    static void dfs(int node, int c) {
        color[node] = c;

        for (int next : graph.get(node)) {
            if (color[next] == 0) { // 미방문 했다면 
                dfs(next, -c); // 다른 색으로 표시 후, 다음 노드로
                if (!isBipartite) return; // 만약 같은 색상이면 return false 
            } else if (color[next] == color[node]) { // 인접 노드가 같은 그룹인 경우 
                isBipartite = false;
                return;
            }
        }
    }
}
