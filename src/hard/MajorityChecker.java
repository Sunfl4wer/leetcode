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
        MajorityChecker object = new MajorityChecker(input);
//        System.out.println(object.query(0,19999,14423));
        System.out.println(object.query(0,3,3));
        System.out.println(object.query(2,3,2));
    }

    private final int digits=15;
    private int[][]presum;
    private ArrayList<Integer>[]pos;

    public MajorityChecker(int[] arr) {
        int len = arr.length;
        presum = new int[len + 1][digits];
        pos = new ArrayList[20001];

        for (int i = 0; i < len; i++) {
            int n = arr[i];
            if (pos[n] == null) pos[n] = new ArrayList();
            pos[n].add(i);

            for (int j = 0; j < digits; j++) {
                presum[i + 1][j] = presum[i][j] + (n & 1);
                n >>= 1;
            }
        }
    }

    public int query(int left, int right, int threshold) {
        return threshold;
    }
}
