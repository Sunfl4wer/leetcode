package medium.balanceabinarysearchtree;

import domain.TreeNode;

import java.util.Objects;

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
    public TreeNode balanceBST(TreeNode root) {
        int[] depth = findDepth(root);
        if (depth[1] == 0) {
            return root;
        }
        return root;
    }

    private int[] findDepth(final TreeNode node) {
        if (isLeaf(node)) {
            return new int[]{1,1};
        }
        int leftDepth = 0;
        int rightDepth = 0;
        if (node.left != null) {
            int[] leftRes = findDepth(node.left);
            if (leftRes[1] == 1) {
                return leftRes;
            }
            leftDepth = leftRes[0];
        }
        if (node.right != null) {
            int[] rightRes = findDepth(node.right);
            if (rightRes[1] == 1) {
                return rightRes;
            }
            rightDepth = rightRes[0];
        }
        if (Math.abs(leftDepth-rightDepth) > 1) {
            return new int[]{1+Math.max(leftDepth, rightDepth), 1};
        }
        return new int[]{1+Math.max(leftDepth, rightDepth), 0};
    }

    private boolean isLeaf(final TreeNode node) {
        if (node == null) {
            return false;
        }
        return node.left == null && node.right == null;
    }
}