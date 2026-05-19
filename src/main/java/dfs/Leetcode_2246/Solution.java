package dfs.Leetcode_2246;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    List<Integer>[] trees;
    int max = 1;
    String s;

    public int longestPath(int[] parent, String s) {
        this.s = s;

        int n = parent.length;
        trees = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            trees[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            trees[parent[i]].add(i);
        }

        dfs(0);

        return max;
    }

    private int dfs(int node) {
        int c1 = 0;
        int c2 = 0;

        for (int child : trees[node]) {
            int len = dfs(child);
            if (s.charAt(child) == s.charAt(node)) {
                continue;
            }

            if (len > c1) {
                c2 = c1;
                c1 = len;
            } else if (len > c2) {
                c2 = len;
            }
        }

        max = Math.max(max, c1 + c2 + 1);

        return c1 + 1;
    }
}
