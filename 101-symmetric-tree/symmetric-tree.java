/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode left, TreeNode right) {
        // Case 1: Both nodes are null
        if (left == null && right == null) {
            return true;
        }

        // Case 2: Only one node is null
        if (left == null || right == null) {
            return false;
        }

        // Case 3: Values are different
        if (left.val != right.val) {
            return false;
        }

        // Case 4: Compare mirror-side children
        return isMirror(left.left, right.right)
            && isMirror(left.right, right.left);
    }
}
