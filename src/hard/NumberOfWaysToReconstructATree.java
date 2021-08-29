package hard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NumberOfWaysToReconstructATree {
  public int checkWays(int[][] pairs) {
    Map<Integer, Node> valToNode = new HashMap<>();
    for (int i = 0; i < pairs.length; i++) {
      int firstVal = pairs[i][0];
      int secondVal = pairs[i][1];
      if (!valToNode.containsKey(firstVal)) {
        Node firstNode = new Node(firstVal);
        valToNode.put(firstVal, firstNode);
      }
      if (!valToNode.containsKey(secondVal)) {
        Node secondNode = new Node(secondVal);
        valToNode.put(secondVal, secondNode);
      }
      valToNode.get(firstVal).linkedNodes.add(valToNode.get(secondVal));
      valToNode.get(secondVal).linkedNodes.add(valToNode.get(firstVal));
    }

    Set<Integer> keys = valToNode.keySet();
    Set<Integer> roots = new HashSet<>();
    for (Integer key : keys) {
      if (valToNode.get(key).linkedNodes.size() == keys.size()-1) {
        roots.add(key);
      }
    }

    if (roots.size() == 0) {
      return 0;
    } else if (roots.size() == 1) {
      return 2;
    } else {
      return 2;
    }
  }

  public static class Node {
    public int val;
    public Set<Node> linkedNodes;

    public Node(){}

    public Node(int val){
      this.val = val;
      this.linkedNodes = new HashSet<>();
    }
  }
}
