package unionfind.Leetcode_1319;

public class Solution {

    private int[] parent, size;

    public int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1) {
            return -1;
        }

        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = i;
        }

        int cnt = 0;
        for (int[] c : connections) {
            if (union(c[0], c[1])) {
                cnt++;
            }
        }

        return (n - cnt) - 1;
    }

    private int find(int a) {
        int root = a;
        while (parent[root] != root) {
            root = parent[root];
        }

        while (parent[a] != root) {
            int next = parent[a];
            parent[a] = root;
            a = next;
        }
        return root;
    }

    private boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa == pb) {
            return false;
        }

        if (size[pa] < size[pb]) {
            int temp = pa;
            pa = pb;
            pb = temp;
        }

        parent[pb] = pa;
        size[pa] += size[pb];

        return true;
    }
}
