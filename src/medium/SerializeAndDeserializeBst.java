package medium;

public class SerializeAndDeserializeBst {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode lv1Node1 = new TreeNode(3);
        TreeNode lv1Node2 = new TreeNode(6);
        root.left = lv1Node1; root.right= lv1Node2;
        TreeNode lv2Node1 = new TreeNode(2);
        TreeNode lv2Node2 = new TreeNode(4);
        lv1Node1.left = lv2Node1; lv1Node1.right = lv2Node2;
        TreeNode lv3Node1 = new TreeNode(1);
        lv2Node1.left = lv3Node1;
        String encoded = new SerializeAndDeserializeBst().serialize(root);
        TreeNode decoded = new SerializeAndDeserializeBst().deserialize(encoded);
        System.out.println(encoded);
        System.out.println(new SerializeAndDeserializeBst().serialize(decoded));
    }

    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if (root == null) {
            return ",0";
        }
        traverse(root, sb);
        return sb.toString();
    }

    private static void traverse(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append((Object)null);
            sb.append(",");
            return;
        }
        sb.append(node.val);
        sb.append(",");
        traverse(node.left, sb);
        traverse(node.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] vals = data.split(",");
        if (vals.length == 0 || vals[0].equals("")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
        index = 0;
        buildTree(root, vals);
        return root;
    }

    private static int index;
    private static void buildTree(TreeNode currentNode, String[] vals) {
        buildLeftTree(currentNode, vals);
        buildRightTree(currentNode, vals);
    }

    private static void buildLeftTree(TreeNode currentNode, String[] vals) {
        index++;
        String val = vals[index];
        if (val.equals("null")) {
            return;
        } else {
            currentNode.left = new TreeNode(Integer.parseInt(val));
        }
        buildTree(currentNode.left, vals);
    }

    private static void buildRightTree(TreeNode currentNode, String[] vals) {
        index++;
        String val = vals[index];
        if (val.equals("null")) {
            return;
        } else {
            currentNode.right = new TreeNode(Integer.parseInt(val));
        }
        buildTree(currentNode.right, vals);
    }

    private static int power(int val) {
        if (val == 0){
            return 1;
        }
        int result = 1;
        for (int i = 0; i < val; i++) {
            result*=2;
        }
        return result;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
