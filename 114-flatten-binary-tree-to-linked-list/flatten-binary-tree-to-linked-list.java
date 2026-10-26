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
    public void dfs(TreeNode C , ArrayList<TreeNode> T) {
        if(C == null) 
            return;

        T.add(C);
      
        dfs(C.left , T);
        dfs(C.right , T);
    } 

    public void flatten(TreeNode root) {
        ArrayList<TreeNode> T = new ArrayList<>();

        dfs(root , T);

        for(int i = 0; i < T.size(); ++i) {
            T.get(i).left = null;
            
            if(i != (int)T.size() - 1) 
                T.get(i).right = T.get(i + 1);
            else 
               T.get(i).right = null;       
        }           
    }
}