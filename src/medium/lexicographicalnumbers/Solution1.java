package medium.lexicographicalnumbers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Solution1 {
    public List<Integer> lexicalOrder(final int n) {
        final List<Integer> res = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            res.add(i);
        }

        Collections.sort(res, new LexicalOrderComparator());

        return res;
    }

    private static class LexicalOrderComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            if (o1 == o2) {
                return 0;
            }
            return isLexicographicallyLarger(o1, o2);
        }

        private int isLexicographicallyLarger(Integer num1, Integer num2) {
            final char[] num1Chars = num1.toString().toCharArray();
            final char[] num2Chars = num2.toString().toCharArray();
            final int comparingLength = num2Chars.length < num1Chars.length
                    ? num2Chars.length : num1Chars.length;
            for (int i = 0; i < comparingLength; i++) {
                int num1Char = num1Chars[i];
                int num2Char = num2Chars[i];
                if (num1Char > num2Char) {
                    return 1;
                }
                if (num1Char < num2Char) {
                    return -1;
                }
            }
            return 0;
        }
    }
}
