package LCA.Leetcode_1483;

public class Solution {

    private int[][] up;
    private int LOG;

    public void TreeAncestor(int n, int[] parent) {
        LOG = 1;
        while ((1 << LOG) < n) {
            LOG++;
        }

        up = new int[LOG][n];
        up[0] = parent;

        for (int i = 1; i < LOG; i++) {
            for (int node = 0; node < n; node++) {
                int mid = up[i - 1][node];
                up[i][node] = (mid == -1) ? -1 : up[i - 1][mid];
            }
        }
    }

    public int getKthAncestor(int node, int k) {
        for (int i = 0; i < LOG; i++) {
            if ((k & (1 << i)) != 0) {
                node = up[i][node];
                if (node == -1) {
                    return -1;
                }
            }
        }

        return node;
    }
}
