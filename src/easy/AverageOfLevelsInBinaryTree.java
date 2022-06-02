package easy;

import domain.TreeNode;
import java.util.ArrayList;
import java.lang.Double;
import java.util.List;
import java.lang.Integer;

public class AverageOfLevelsInBinaryTree {
  public static void main(final String[] args) {
    Integer[] test = new Integer[]{3,9,20,null,null,15,7};
    TreeNode root = TreeNode.insertLevelOrder(test, new TreeNode(), 0);
    AverageOfLevelsInBinaryTree a = new AverageOfLevelsInBinaryTree();
    System.out.println(a.averageOfLevels(root));
  }
  private static List<List<Integer>> count;
  public List<Double> averageOfLevels(TreeNode root) {
    count = new ArrayList<>();
    traverse(root, 0);
    List<Double> res = new ArrayList<>();
    int size = count.size();
    for (int i = 0; i <size; i++) {
      List<Integer> val = count.get(i);
      res.add(val.get(0)/(double)val.get(1));
    }
    return res;
  }
  
  private static void traverse(TreeNode node, int level) {
    if (count.size() == level) {
      count.add(List.of(node.val, 1));
    } else {
      List<Integer> val = count.get(level);
      count.set(level, List.of(val.get(0)+node.val, val.get(1)+1));
    }
    if (node.left != null) {
      traverse(node.left, level+1);
    }
    if (node.right != null) {
      traverse(node.right, level+1);
    }
  }
}
