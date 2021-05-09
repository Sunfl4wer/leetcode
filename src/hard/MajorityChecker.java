package hard;

import java.util.*;

public class MajorityChecker {
    public static void main(String[] args) {
//       testCase1();
        int[] input = new int[]{1,1,2,2,1,1};
//        int[] input = new int[20000];
//        for (int i = 0; i < input.length; i++) {
//            input[i] = 1;
//        }

        ArrayList<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(3);
        list.add(5);
        System.out.println(binarySearch(list, 2));

//        MajorityChecker object = new MajorityChecker(input);
////        System.out.println(object.query(0,19999,14423));
//        System.out.println(object.query(0,3,3));
//        System.out.println(object.query(2,3,2));2
    }

    private int[][] counts;
    private static final int NO_BITS = 15;
    private ArrayList<Integer>[] positions;

    public MajorityChecker(int[] arr) {
        int length = arr.length;
        int[][] counts = new int[arr.length+1][NO_BITS];
        ArrayList<Integer>[] positions = new ArrayList[20001];
        for (int i = 0; i < length; i++) {
            int n = arr[i];
            ArrayList<Integer> position = positions[n] == null ? new ArrayList<>() : positions[n];
            position.add(i);
            positions[n] = position;
            for (int j = 0; j < NO_BITS; j++) {
                counts[i+1][j] = counts[i][j] + (n&1);
                n>>=1;
            }
        }
        this.counts = counts;
        this.positions = positions;
    }

    public int query(int left, int right, int threshold) {
        int result = 0;
        for (int i = NO_BITS-1; i >= 0; i--) {
            int bit = 0;
            int count = counts[right+1][i] - counts[left][i];
            if (count >= threshold) {
                bit = 1;
            } else if (right-left+1-count >= threshold) {
                bit = 0;
            } else {
                return -1;
            }
            result = (result<<1)+bit;
        }

        ArrayList<Integer> position = positions[result];
        if (position == null || position.isEmpty()) {
            return -1;
        }
        int leftIndex = binarySearch(position, left);
        int rightIndex = binarySearch(position, right);
        if (rightIndex-leftIndex + 1 < threshold) {
            return -1;
        }
        return result;
    }


    /*
        [a1, a2, a3, a4, a5,...,aN]
        given p, find nearest index i so that ai
    */


    private static int binarySearch(ArrayList<Integer> positions, int position) {
        int left = 0, right = positions.size()-1, mid=0;
        while (left <= right) {
            mid = left+(right-left)/2;
            if (positions.get(mid) == position) {
                return left;
            }
            if (positions.get(mid) < position) {
                left = mid+1;
            }
            if (positions.get(mid) > position) {
                right = mid-1;
            }
        }
        return left;
    }
}
