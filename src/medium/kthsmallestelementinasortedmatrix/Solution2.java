package medium.kthsmallestelementinasortedmatrix;

import java.util.Arrays;

public class Solution2 {
    public int kthSmallest(int[][] matrix, int k) {
        int[] arr = new int[k+1];
        int count = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (count < k+1) {
                    arr[count] = matrix[i][j];
                    count++;
                } else {
                    Arrays.sort(arr);
                    arr[k] = matrix[i][j];
                }
            }
        }
        Arrays.sort(arr);
        return arr[k-1];
    }
}
