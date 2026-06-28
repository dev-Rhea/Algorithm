package divideandconquer.Leetcode_95;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}


public class Solution {

    public List<TreeNode> generateTrees(int n) {
        return build(1, n);
    }

    private List<TreeNode> build(int start, int end) {
        List<TreeNode> trees = new ArrayList<>();
        if (start > end) {
            trees.add(null);
            return trees;
        }

        for (int root = start; root <= end; root++) {
            List<TreeNode> lefts = build(start, root - 1);
            List<TreeNode> rights = build(root + 1, end);

            for (TreeNode left : lefts) {
                for (TreeNode right : rights) {
                    TreeNode node = new TreeNode(root);
                    node.left = left;
                    node.right = right;
                    trees.add(node);
                }
            }
        }

        return trees;
    }
}
