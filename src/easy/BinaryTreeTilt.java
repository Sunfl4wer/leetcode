package easy;

public class BinaryTreeTilt {
  public static void main(final String[] args) {
    final TreeNode left = new TreeNode(3);
    final TreeNode right = new TreeNode(2);
    final TreeNode root = new TreeNode(1, left, right);
    System.out.println(new BinaryTreeTilt().findTilt(root));
  }

  private static Integer tiltSum;

  public int findTilt(TreeNode root) {
    tiltSum = 0;
    sumNode(root);
    return tiltSum;
  }

  private static int sumNode(TreeNode node) {
    if (node.left == null && node.right == null) {
      return node.val;
    }

    int currentSum = node.val;
    int sumLeft = 0;
    int sumRight = 0;
    if (node.left != null) {
      sumLeft = sumNode(node.left);
    }
    if (node.right != null) {
      sumRight = sumNode(node.right);
    }
    if (sumLeft < sumRight) {
      tiltSum = tiltSum+sumRight-sumLeft;
    } else {
      tiltSum = tiltSum-sumRight+sumLeft;
    }
    return currentSum+sumLeft+sumRight;
  }

 public static class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode() {}
     public TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
     }
 }
}
