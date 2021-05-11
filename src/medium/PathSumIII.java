package medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PathSumIII {
    public static void main(final String[] args) {
        Integer[] testCase1 = {5,4,8,11,null,13,4,7,2,null,null,5,1};
        TreeNode root = insertLevelOrder(testCase1, new TreeNode(), 0);
        long start = System.currentTimeMillis();
        System.out.println(new PathSumIII().pathSum(root, 22));
        long end = System.currentTimeMillis();
        System.out.println("Execution time: " + (end-start) + " ms");
    }

    private static int noPaths;
    public int pathSum(TreeNode root, int targetSum) {
        noPaths = 0;

        traverse(root, targetSum);

        return noPaths;
    }

    private static Map<Integer, Integer> traverse(TreeNode node, int targetSum) {
        if (node.val == targetSum) {
            noPaths++;
        }
        Map<Integer, Integer> sums = new HashMap<>();
        Map<Integer, Integer> lastNodeSums = new HashMap<>();
        if (node.left != null) {
            lastNodeSums.putAll(traverse(node.left, targetSum));
        }
        if (node.right != null) {
            traverse(node.right, targetSum).forEach((k, v) -> {
                if (lastNodeSums.containsKey(k)) {
                    lastNodeSums.put(k, lastNodeSums.get(k)+v);
                } else {
                    lastNodeSums.put(k, v);
                }
            });
        }
        return sums;
    }

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

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

    public static TreeNode insertLevelOrder(Integer[] arr, TreeNode root, int i) {
        // Base case for recursion
        if (i < arr.length && arr[i] != null) {
            TreeNode temp = new TreeNode(arr[i]);
            root = temp;

            // insert left child
            root.left = insertLevelOrder(arr, root.left,
                    2 * i + 1);

            // insert right child
            root.right = insertLevelOrder(arr, root.right,
                    2 * i + 2);
        }
        return root;
    }
}
