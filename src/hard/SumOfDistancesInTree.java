package hard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class SumOfDistancesInTree {
  public static void main(final String[] args) {
    SumOfDistancesInTree sumOfDistancesInTree = new SumOfDistancesInTree();
    int[][] test = {{0,1},{0,2},{2,3},{2,4},{2,5}};
    System.out.println(Arrays.toString(sumOfDistancesInTree.sumOfDistancesInTree(6, test)));
  }

  public int[] sumOfDistancesInTree(int n, int[][] edges) {
    Node[] nodes = new Node[n];
    Set<Node> traversed = new HashSet<>();
    for (int[] edge : edges) {
      Node node1 = new Node(edge[0]);
      Node node2 = new Node(edge[1]);
      node1.add(node2, 1);
      node2.add(node1, 1);
      if (edge[0] < n) {
        nodes[edge[0]] = node1;
      }
      if (edge[1] < n) {
        nodes[edge[1]] = node2;
      }
    }
    int[] res = new int[n];
    for (int i = 0; i < nodes.length; i++) {
      res[i] = nodes[i].getSum();
    }
    return res;
  }

  public static class Node extends Object {
    private int val;
    private Map<Node, Integer> nodeToDist;

    public int getSum() {
      int sum = 0;
      Set<Node> nodes = nodeToDist.keySet();
      for (Node node : nodes) {
        sum += nodeToDist.get(node);
      }
      return sum;
    }

    public boolean containsKey(Node node) {
      return nodeToDist.containsKey(node);
    }

    public void add(Node node, Integer dist) {
      if (!nodeToDist.containsKey(node)) {
        Set<Node> nodes = nodeToDist.keySet();
        for (Node n : nodes) {
          Integer d = nodeToDist.get(node);
          n.add(node, d+dist);
        }
        nodeToDist.put(node, dist);
      } else {
        final Integer currDist = nodeToDist.get(node);
        if (currDist > dist) {
          nodeToDist.put(node, dist);
        }
      }
    }

    public Node(int val) {
      this.val = val;
      this.nodeToDist = new HashMap<>();
    }

    @Override
    public boolean equals(Object obj) {
      if(obj == null || !(obj instanceof Node)) {
        return false;
      }
      return Objects.equals(((Node)obj).val, this.val);
    }

    @Override
    public int hashCode() {
      return this.val;
    }
  }
}
