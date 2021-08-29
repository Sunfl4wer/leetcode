package domain;

public class TreeNode {
  public int val;
  public TreeNode left;
  public TreeNode right;

  public TreeNode() {
  }

  public TreeNode(int val) {
    this.val = val;
  }

  public TreeNode(int val, TreeNode left, TreeNode right) {
    this.val = val;
    this.left = left;
    this.right = right;
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
