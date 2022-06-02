package codeforce;

import java.util.Scanner;
import java.util.StringJoiner;

public class AntiFibonacciPermutation {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        int noTests = scanner.nextInt();
        while (noTests-- > 0) {
            int n = scanner.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = arr.length-i;
            }
            StringJoiner sb = new StringJoiner(" ");
            for (int i = 0; i < arr.length; i++) {
                sb.add(""+arr[i]);
            }
            System.out.println(sb.toString());
            for (int i = arr.length-1; i > 0; i--) {
                int temp = arr[i];
                arr[i] = arr[i-1];
                arr[i-1] = temp;
                sb = new StringJoiner(" ");
                for (int j = 0; j < arr.length; j++) {
                    sb.add(""+arr[j]);
                }
                System.out.println(sb.toString());
            }
        }
    }
}
