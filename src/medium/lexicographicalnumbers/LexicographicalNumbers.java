package medium.lexicographicalnumbers;

import java.util.List;
import java.util.stream.Collectors;

/*
Given an integer n, return all the numbers in the range [1, n] sorted in lexicographical order.

You must write an algorithm that runs in O(n) time and uses O(1) extra space.
 */
public class LexicographicalNumbers {
    private static final StringBuilder sb = new StringBuilder();

    public static void main(final String[] args) {
        final Solution solution = new Solution();
        printRes(solution.lexicalOrder(100));
    }

    private static void printRes(final List<Integer> res) {
        System.out.println(res.stream().map(i -> i.toString())
                .collect(Collectors.joining(" ")));
    }
}
