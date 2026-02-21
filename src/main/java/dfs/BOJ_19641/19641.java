package dfs.BOJ_19641;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Main {

    static StringBuilder sb = new StringBuilder();
    static List<List<Integer>> tree;
    static int[] left, right;
    static int cnt = 0;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        tree = new ArrayList<>();
        for(int i=0;i<=N;i++) {
            tree.add(new ArrayList<>());
        }

        for(int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());

            while(st.hasMoreTokens()) {
                int u = Integer.parseInt(st.nextToken());
                if(u == -1) break;
                tree.get(v).add(u);
            }
        }

        int S = Integer.parseInt(br.readLine());
        left = new int[N+1];
        right = new int[N+1];

        for(int i=1;i<=N;i++) {
            Collections.sort(tree.get(i));
        }

        dfs(S, 0);
        
        for (int i=1;i<=N;i++) {
            sb.append(i).append(" ").append(left[i]).append(" ").append(right[i]).append("\n");
        }
        System.out.print(sb);
    }

    static void dfs(int node, int parent) {
        left[node] = ++cnt;

        for(int c : tree.get(node)) {
            if(c == parent) continue;
            dfs(c, node);
        }

        right[node] = ++cnt;
    }
}