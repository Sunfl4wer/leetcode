package medium.countnodeswiththehighestscore;

public class Solution {
    private static int score = Integer.MIN_VALUE;
    private static int count = 0;

    public int countHighestScoreNodes(int[] parents) {
        count = 0;
        score = Integer.MIN_VALUE;
        Node[] nodes = new Node[parents.length+1];
        for (int currentVal = 1; currentVal <  parents.length; currentVal++) {
            if (nodes[currentVal] == null) {
                nodes[currentVal] = new Node(currentVal);
            }

            final Node currentNode = nodes[currentVal];

            int parentVal = parents[currentVal];
            if (nodes[parentVal] == null) {
                nodes[parentVal] = new Node(parentVal);
            }

            final Node parentNode = nodes[parentVal];

            if (parentVal != currentVal) {
                currentNode.setParents(parentNode);
                parentNode.addChild(currentNode);
            }
        }

        final Node root = nodes[0];
        root.countChildren();
        countHighestScoreNodes(root);
        return count;
    }

    private void countHighestScoreNodes(final Node node) {
        final int product = getCount(node.getParents(), node)*
                getCount(node.getChild1(), null)*getCount(node.getChild2(), null);

        if (product > score) {
            count = 1;
            score = product;
        } else if (product == score) {
            count++;
        }

        if (node.getChild1() != null) {
            countHighestScoreNodes(node.getChild1());
        }
        if (node.getChild2() != null) {
            countHighestScoreNodes(node.getChild2());
        }
    }

    private int getCount(final Node node, final Node child) {
        if (node == null) {
            return 1;
        }
        if (child == null) {
            int nodeCount = node.getChild1Count() + node.getChild2Count();
            nodeCount += 1;
            return nodeCount;
        }
        int nodeCount = 1;
        if (node.getChild1() != child) {
            nodeCount+=node.getChild1Count();
        }
        if (node.getChild2() != child) {
            nodeCount+=node.getChild2Count();
        }
        if (node.getParents() != null) {
            nodeCount+=(node.getParents().getChild1Count()+node.getParents().getChild2Count()+1);
        }
        return nodeCount;
    }

    class Node {
        private int val;
        private Node parents;
        private Node child1;
        private Node child2;

        private int child1Count;
        private int child2Count;

        public Node(final int val) {
            this.val = val;
        }

        public void setParents(final Node parents) {
            this.parents = parents;
        }

        public Node getParents() {
            return this.parents;
        }

        public void addChild(final Node node) {
            if (child1 == null) {
                this.child1 = node;
            } else {
                this.child2 = node;
            }
        }

        public Node getChild1() {
            return this.child1;
        }

        public int getChild1Count() {
            return this.child1Count;
        }

        public Node getChild2() {
            return this.child2;
        }

        public int getChild2Count() {
            return this.child2Count;
        }

        public int countChildren() {
            if (this.child1 != null) {
                this.child1Count = this.child1.countChildren() + 1;
            }
            if (this.child2 != null) {
                this.child2Count = this.child2.countChildren() + 1;
            }
            return this.child1Count+this.child2Count;
        }
    }
}