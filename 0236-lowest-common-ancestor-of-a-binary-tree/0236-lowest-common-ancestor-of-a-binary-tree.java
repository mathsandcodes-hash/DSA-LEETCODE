/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q){
            return root;
        }

        TreeNode leftresult = lowestCommonAncestor(root.left , p ,q);
        TreeNode rightresult = lowestCommonAncestor(root.right , p ,q);

        if(leftresult!= null &&  rightresult !=null){
            return root;
        }

        return leftresult != null ? leftresult:rightresult;
    }
}