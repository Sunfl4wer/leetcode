package medium.lexicographicalnumbers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Solution {
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
            return isLexicographicallyLarger(o1, o2);
        }

        private int isLexicographicallyLarger(Integer num1, Integer num2) {
            final String num1Str = num1.toString();
            final String num2Str = num2.toString();
            final int comparingLength = num2Str.length() < num1Str.length()
                    ? num2Str.length() : num1Str.length();
            for (int i = 0; i < comparingLength; i++) {
                if ((int)num1Str.charAt(i) > (int)num2Str.charAt(i)) {
                    return 1;
                }
                if ((int)num1Str.charAt(i) < (int)num2Str.charAt(i)) {
                    return -1;
                }
            }
            return 0;
        }
    }
}
