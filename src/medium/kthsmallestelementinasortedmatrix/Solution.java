package medium.kthsmallestelementinasortedmatrix;

import java.util.Arrays;

public class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        final Arr arr = new Arr(k);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                arr.addVal(matrix[i][j]);
            }
        }
        return arr.getLast();
    }

    class Arr {
        private int k;
        private int count = 0;
        private int[] vals;

        public Arr(int k) {
            this.k = k;
            vals = new int[k];
        }

        public void addVal(int val) {
            if (count < k) {
                vals[count] = val;
                count++;
            } else {
                Arrays.sort(vals);
                if (val >= vals[k-1]) {
                    return;
                }
                if (val <= vals[0]) {
                    shiftRight(0);
                    vals[0] = val;
                    return;
                }
                int idx = search(vals, val);
                shiftRight(idx);
                vals[idx] = val;
            }
        }

        public int getLast() {
            return vals[k-1];
        }

        private void shiftRight(int idx) {
            for (int i = k-1; i > idx; i--) {
                this.vals[i] = this.vals[i-1];
            }
        }

        private int search(int[] vals, int val) {
//            return linearSearch(vals, val);
            return binarySearch(vals, val);
        }

        private int linearSearch(int[] vals, int val) {
            for (int i = 1; i < vals.length; i++) {
                if (vals[i] >= val && vals[i-1] <= val) {
                    return i;
                }
            }
            return -1;
        }

        private int binarySearch(int[] vals, int val) {
            int start = 0;
            int end = k;
            int mid = (start+end)/2;
            while(start != end-1) {
                if (vals[mid] >= val) {
                    end = mid;
                } else {
                    start = mid;
                }
                mid = (start+end)/2;
            }
            return end;
        }
    }
}
