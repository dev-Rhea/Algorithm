package MST.Leetcode_1489;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    private int[] parent;

    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        int m = edges.length;

        int[][] indexed = new int[m][4];

        for (int i = 0; i < m; i++) {
            indexed[i][0] = edges[i][0];
            indexed[i][1] = edges[i][1];
            indexed[i][2] = edges[i][2];
            indexed[i][3] = i;
        }

        Arrays.sort(indexed, (a, b) -> (a[2] - b[2]));

        int bw = kruskal(n, indexed, -1, -1);

        List<Integer> critical = new ArrayList<>();
        List<Integer> pseudo = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            if (kruskal(n, indexed, i, -1) > bw) {
                critical.add(indexed[i][3]);
            } else if (kruskal(n, indexed, -1, i) == bw) {
                pseudo.add(indexed[i][3]);
            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        ans.add(critical);
        ans.add(pseudo);
        return ans;
    }

    private int kruskal(int n, int[][] idx, int skip, int force) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        int total = 0;
        int used = 0;

        if (force != -1) {
            union(idx[force][0], idx[force][1]);
            total += idx[force][2];
            used++;
        }

        for (int i = 0; i < idx.length; i++) {
            if (i == skip || i == force) {
                continue;
            }

            if (find(idx[i][0]) != find(idx[i][1])) {
                union(idx[i][0], idx[i][1]);
                total += idx[i][2];
                used++;
            }
        }

        return used == n - 1 ? total : Integer.MAX_VALUE;
    }

    private int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    private void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa != pb) {
            parent[pb] = pa;
        }
    }
}
