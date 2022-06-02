package medium.kthsmallestelementinasortedmatrix;

public class KthSmallestElementInASortedMatrix {
    public static void main(final String[] args) {
        int[][] matrix = new int[][]{new int[]{1,4}, new int[]{2,5}};
        int k = 2;
        final Solution solution = new Solution();
        final Solution1 solution1 = new Solution1();
        final Solution2 solution2 = new Solution2();
        System.out.println(solution.kthSmallest(matrix, k));
        System.out.println(solution1.kthSmallest(matrix, k));
        System.out.println(solution2.kthSmallest(matrix, k));
    }
}
