package dfs.BOJ_16347;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {

    static class Node {
        long wolf;
        long sheep;
        List<Integer> child = new ArrayList<>();

        Node() {
            this.wolf = 0;
            this.sheep = 0;
        }
    }

    static Node[] nodes;
    static boolean visited[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        nodes = new Node[N + 1];
        visited = new boolean[N + 1];

        for(int i=1;i<=N;i++) {
            nodes[i] = new Node();
        }

        
        for(int i=2;i<=N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String type = st.nextToken();
            long count = Long.parseLong(st.nextToken());
            int parent = Integer.parseInt(st.nextToken());

            nodes[i].wolf = type.equals("W") ? count : 0;
            nodes[i].sheep = type.equals("S") ? count : 0;

            nodes[parent].child.add(i);
        }

        long ans = dfs(1);
        System.out.println(ans);
    }

    static long dfs(int node) {
        visited[node] = true;
        long total = nodes[node].sheep;

        for(int c : nodes[node].child) {
            if(!visited[c]) {
                long save = dfs(c);

                if(save > nodes[node].wolf) {
                    save -= nodes[node].wolf;
                    nodes[node].wolf = 0;
                }
                else {
                    nodes[node].wolf -= save;
                    save = 0;
                }
                total += save;
            }
        }
        return total;
    }
}