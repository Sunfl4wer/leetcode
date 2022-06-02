package medium.kthsmallestelementinasortedmatrix;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution1 {
    public int kthSmallest(int[][] matrix, int k) {
        final PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new IntComp());
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (pq.size() == k+1) {
                    pq.poll();
                }
                pq.add(Integer.valueOf(matrix[i][j]));
            }
        }
        if (pq.size() == k+1) {
            pq.poll();
        }
        return pq.poll().intValue();
    }

    class IntComp implements Comparator<Integer> {
        @Override
        public int compare(final Integer i1, final Integer i2) {
            if (i1.intValue() < i2.intValue()) {
                return 1;
            } else if (i1.intValue() > i2.intValue()) {
                return -1;
            } else {
                return 0;
            }
        }

    }
}
