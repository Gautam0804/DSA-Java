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

import java.util.*;

class Solution {
    void dfs(TreeNode curr , int height , List<List<Integer>> Ans) {
        if(curr == null) 
            return;

        if(height >= Ans.size()) 
            Ans.add(new ArrayList<>());

        Ans.get(height).add(curr.val);    

        dfs(curr.left , height + 1 , Ans);
        dfs(curr.right , height + 1 , Ans);
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> Ans = new ArrayList<>();

        dfs(root , 0 , Ans);

        return Ans;
    }
}
