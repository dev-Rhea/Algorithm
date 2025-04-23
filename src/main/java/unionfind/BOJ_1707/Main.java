package unionfind.BOJ_1707;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] parent;
    static int[] not;
    static boolean bipartite;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int K = Integer.parseInt(br.readLine());

        while(K-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            parent = new int[V+1];
            not = new int[V+1];
            bipartite = true;

            for(int i=1;i<=V;i++) {
                parent[i] = i;
                not[i] = 0;
            }

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                if (!union(u, v)) {
                    bipartite = false;
                }
            }

            sb.append(bibpartite ? "YES\n" : "NO\n");
        }

        System.out.println(sb);
    }

    static int find(int x) {
        if(parent[x] != x) parent[x] = find(parent[x]);
        return parent[x]; 
    }

    static boolean union(int u, int v) {
        int pu = find(u);
        int pv = find(v);

        if(pu == pv) return false;

        // 인접 노드를 다른 그룹으로 분류 
        if(not[pu] == 0) not[pu] = pv; // 다른 그룹으로 분류 
        else merge(not[pu], pv); // 다른 그룹으로 분류된 인접 노드가 있다면, 해당 인접 노드와 같은 그룹으로 묶기 

        if(not[pv] == 0) not[pv] = pu;
        else merge(not[pv], pu);

        return true;
    }
    
    // 두 노드가 다른 그룹이면 연결 
    static void merge(int u, int v) {
        int pu = find(u);
        int pv = find(v);

        if(pu != pv) parent[pu] = pv;
    }
    
}
