package etc.scc.BOJ_11280;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static class Node {
        ArrayList<Integer> edges = new ArrayList<>();
    }
    static Node[] nodes;
    static int [] visited, stack, group;
    static int v, top, g;
    static boolean[] scc;

    public static void main(String[] args) throws Exception {
        int n = pi(), m = pi(), nl = n * 2 + 1;
        nodes = new Node[nl];
        visited = new int[nl];
        stack = new int[nl];
        group = new int[nl];
        scc = new boolean[nl];

        for(int i=0;i<nl;i++) {
            nodes[i] = new Node();
        }

        for(int i=0;i<m;i++) {
            int a = pi(), b = pi();
            nodes[-a + n].edges.add(b + n);
            nodes[-b + n].edges.add(a + n);
        }

        for(int i=0;i<nl;i++) {
            if(visited[i] == 0) {
                dfs(i);
            }
        }

        for(int i=1;i<=n;i++) {
            if(group[-i + n] == group[i + n]) {
                System.out.println(0);
                return;
            }
        }
        System.out.println(1);
    }

    static int dfs(int idx) {
        int p = ++v;
        visited[idx] = p;
        stack[top++] = idx;

        for(int e : nodes[idx].edges) {
            if(visited[e] == 0) {
                p = Math.min(dfs(e), p);
            }
            else if(!scc[e]) {
                p = Math.min(visited[e], p);
            }
        }

        if(p == visited[idx]) {
            g++;
            int c;
            do {
                c = stack[--top];
                group[c] = g;
                scc[c] = true;
            }
            while (c != idx);
        }
        return p;
    }

    static int pi() throws Exception {
        int c, n;
        boolean minus = false;

        while ((c = br.read()) < '0') {
            if(c == '-') {
                minus = true;
            }
        }
        n = c - '0';
        while ((c = br.read()) >= '0') {
            n = n * 10 + c - '0';
        }
        return minus ? -n : n;
    }
}
