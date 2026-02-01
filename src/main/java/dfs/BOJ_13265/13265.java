package dfs.BOJ_13265;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {

    static int n;
    static int[] color;
    static List<List<Integer>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        
        for(int t=0;t<T;t++) {
            graph = new ArrayList<>();

            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            for(int i=0;i<=n;i++) {
                graph.add(new ArrayList<>());
            }

            for(int i=0;i<m;i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                graph.get(x).add(y);
                graph.get(y).add(x);
            }

            color = new int[n+1];
            boolean flag = true;

            for(int i=1;i<=n;i++) {
                if(color[i] == 0) {
                    if(!dfs(i, 1)) {
                        flag = false;
                        break;
                    }
                }
            }
            sb.append(flag ? "possible":"impossible").append("\n");
        }

        System.out.println(sb);
    }

    static boolean dfs(int node, int c) {
        color[node] = c;

        for(int next : graph.get(node)) {
            if(color[next] == 0) {
                if(!dfs(next, 3-c)) return false;
            }
            else if(color[next] == c) return false;
        }
        return true;
    }
}