package codeforce;

import java.util.Objects;
import java.util.Scanner;
import java.util.StringJoiner;

public class IncreaseSubArraySum {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        int noTests = scanner.nextInt();
        while (noTests-- > 0) {
            int noElems = scanner.nextInt();
            int x = scanner.nextInt();
            int[] arr = new int[noElems];
            int sum = 0;
            for (int i = 0; i < noElems; i++) {
                arr[i] = scanner.nextInt();
                sum+=arr[i];
            }
            StringJoiner sj = new StringJoiner(" ");
            if (sum < 0) {
                sj.add("0");
            } else {
                sj.add(Objects.toString(sum));
            }

            int max = sum;
            int noDigits = arr.length;
            int startIdx = 0;
            int endIdx = arr.length;
            for (int i = 1; i < arr.length; i++) {
                int tempSum = 0;
                for (int j = 0; j < i; j++) {
                    tempSum+=arr[j];
                }

                max = Math.max(tempSum, max);
                if (max == tempSum) {
                    noDigits = i;
                    startIdx = 0;
                    endIdx = i-1;
                }

                for (int k = i; k < arr.length; k++) {
                    tempSum-=arr[k-i];
                    tempSum+=arr[k];
                    max = Math.max(tempSum, max);
                    if (max == tempSum) {
                        noDigits = i;
                        startIdx = k-i+1;
                        endIdx = k;
                    }
                }
            }
            for (int k = 1; k < noElems+1; k++) {
                if (k <= noDigits) {
                    max+=k*x;
                    sj.add(""+max);
                    max-=k*x;
                } else {
                    int diff = noDigits-k;
                    int[] subArr = new int[diff*2];
                    for (int i = 0; i < subArr.length; i++) {
                        subArr[i] = Integer.MIN_VALUE;
                    }
                    int mid = diff-1;
                    for (int i = startIdx-1; i >= 0 && mid >= 0; i--) {
                        subArr[mid] = arr[i];
                        mid--;
                    }
                    mid = diff;
                    for (int i = endIdx+1; i < arr.length && mid < subArr.length; i++) {
                        subArr[mid] = arr[i];
                        mid++;
                    }
                    int windowSum = 0;
                    int tempSum = 0;
                    for (int i = 0; i < diff; i++) {
                        tempSum+=subArr[i];
                    }
                    windowSum = Math.max(tempSum, windowSum);
                    for (int i = diff; i < subArr.length; i++) {
                        tempSum-=subArr[i-diff];
                        tempSum+=subArr[i];
                        windowSum = Math.max(tempSum, windowSum);
                    }
                    windowSum+=x * diff;
                    if (windowSum > 0) {
                        max += windowSum;
                    }
                    max += x * noDigits;
                    sj.add("" + max);
                    max -= x * noDigits;
                    if (windowSum > 0) {
                        max -= windowSum;
                    }
                }
            }
            System.out.println(sj.toString());
        }
    }
}
