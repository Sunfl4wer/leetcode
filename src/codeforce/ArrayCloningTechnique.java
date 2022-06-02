package codeforce;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ArrayCloningTechnique {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        int noTests = scanner.nextInt();
        while (noTests-- > 0) {
            Integer n = scanner.nextInt();
            Map<Integer, Integer> count = new HashMap<>();
            Integer[] arr = new Integer[n];
            for (int i = 0; i < n; i++) {
                arr[i] = scanner.nextInt();
                if (count.containsKey(arr[i])) {
                    count.put(arr[i], count.get(arr[i])+1);
                } else {
                    count.put(arr[i], 1);
                }
            }
            Integer max = Integer.MIN_VALUE;
            for (Integer key : count.keySet()) {
                if (count.get(key) > max) {
                    max = count.get(key);
                }
            }
            int res = 0;
            while (max < n) {
                res++;
                res+=Math.min(max, n-max);
                max*=2;
            }
            System.out.println(res);
        }
    }
}
