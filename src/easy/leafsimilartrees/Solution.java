package easy.leafsimilartrees;

import java.util.ArrayDeque;
import java.util.Queue;

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
public class Solution {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        Queue<Integer> tracking = new ArrayDeque<>();
        traverse(root1, tracking);
        traverse1(root2, tracking);
        return tracking.isEmpty();
    }

    private void traverse1(final TreeNode node, final Queue<Integer> q) {
        if (node.left == null && node.right == null && node.val == q.peek().intValue()) {
            q.poll();
            return;
        }
        if (node.left != null) {
            traverse(node.left, q);
        }
        if (node.right != null) {
            traverse(node.right, q);
        }
    }

    private void traverse(final TreeNode node, final Queue<Integer> q) {
        if (node.left == null && node.right == null) {
            q.add(node.val);
            return;
        }
        if (node.left != null) {
            traverse(node.left, q);
        }
        if (node.right != null) {
            traverse(node.right, q);
        }
    }
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
}
