import java.util.*;

class Solution {
    public TreeNode balanceBST(TreeNode root) {

        ArrayList<Integer> inorder = new ArrayList<>();
        getInorder(root, inorder);

        TreeNode newRoot = createBST(inorder, 0, inorder.size() - 1);

        return newRoot;
    }

    public static void getInorder(TreeNode root, ArrayList<Integer> inorder) {
        if (root == null) {
            return;
        }

        getInorder(root.left, inorder);
        inorder.add(root.val);
        getInorder(root.right, inorder);
    }

    public static TreeNode createBST(ArrayList<Integer> inorder, int st, int end) {
        if (st > end) {
            return null;
        }

        int mid = st + (end - st) / 2;

        TreeNode root = new TreeNode(inorder.get(mid));

        root.left = createBST(inorder, st, mid - 1);
        root.right = createBST(inorder, mid + 1, end);

        return root;
    }
}