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
// class Solution {
//     public int maxDepth(TreeNode root) {
//         if(root == null){
//             return 0;
//         }
//         int left = maxDepth(root.left);
//         int right = maxDepth(root.right);

//         return Math.max(left,right)+1;
        
//     }
// }

class Solution {
    public int maxDepth(TreeNode root) {
        if(root == null) 
            return 0;

        ArrayDeque<TreeNode> qu = new ArrayDeque<>();
        HashMap<TreeNode,Integer> mp = new HashMap<>();
         
        qu.offer(root); 
        mp.put(root , 1);

        while(qu.size() > 0) {
            TreeNode C = qu.poll();

            if(C.left != null) {
                qu.offer(C.left);
                mp.put(C.left , mp.get(C) + 1);
            } 

            if(C.right != null) {
                qu.offer(C.right);
                mp.put(C.right , mp.get(C) + 1);
            }             
        }

        int ans = 0;

        for(Map.Entry<TreeNode,Integer> C : mp.entrySet()) 
            ans = Math.max(ans , C.getValue());

        return ans;    
    }
}