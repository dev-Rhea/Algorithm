package dfs.BOJ_2533;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main2 {
    static class Node {
        int idx;
        Node edge;
        public Node(int idx, Node edge) {
            this.idx = idx;
            this.edge = edge;
        }
    }

    static Node[] nodes;
    static int cnt;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        nodes = new Node[N + 1];

        for(int i=0;i<N-1;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            nodes[u] = new Node(v, nodes[u]);
            nodes[v] = new Node(u, nodes[v]);
        }

        dfs(1, -1);
        System.out.println(cnt);
    }

    static boolean dfs(int node, int parent) {
        boolean isEalryer = false;

        for(Node c  = nodes[node] ; c != null; c = c.edge) {
            if(c.idx == parent) continue; // 부모 노드 제외
            if(!dfs(c.idx, node)) isEalryer = true; // 자식 노드가 얼리어답터가 아닌 경우, 현재 노드는 얼리어답터여야 함.
        }

        if(isEalryer) cnt++;
        return isEalryer;
    }
}
