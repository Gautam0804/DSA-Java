/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

import java.util.ArrayList;

class Solution {

    public boolean getPath(TreeNode root, TreeNode target,
                           ArrayList<TreeNode> path) {

        if (root == null) {
            return false;
        }

        path.add(root);

        if (root == target) {
            return true;
        }

        boolean foundLeft = getPath(root.left, target, path);
        boolean foundRight = getPath(root.right, target, path);

        if (foundLeft || foundRight) {
            return true;
        }

        path.remove(path.size() - 1);
        return false;
    }

    public TreeNode lowestCommonAncestor(
            TreeNode root, TreeNode p, TreeNode q) {

        ArrayList<TreeNode> path1 = new ArrayList<>();
        ArrayList<TreeNode> path2 = new ArrayList<>();

        getPath(root, p, path1);
        getPath(root, q, path2);

        int i = 0;

        while (i < path1.size() && i < path2.size()
                && path1.get(i) == path2.get(i)) {
            i++;
        }

        return path1.get(i - 1);
    }
}
