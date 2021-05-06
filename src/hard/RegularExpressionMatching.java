package hard;

import java.util.*;

public class RegularExpressionMatching {
    public static void main(final String[] args) {
        final String str = "aa";
        final String regex = "a*"; // .* c* .

        System.out.println(new RegularExpressionMatching().decompose(regex));
        System.out.println(new RegularExpressionMatching().isMatch(str, regex));
        System.out.println("Test 1: " + test("mississippi", "mis*is*p*.", false));
        System.out.println("Test 2: " + test("aa", "a*", true));
        System.out.println("Test 3: " + test("aaa", "a*a", true));
        System.out.println("Test 4: " + test("a", "ab*", true));
        System.out.println("Test 5: " + test("a", ".*..a*", false));
        System.out.println("Test 6: " + test("", "..*", false));
        System.out.println("Test 7: " + test("abbbcd", "ab*bbbcd", true));
        System.out.println("Test 8: " + test("abcaaaaaaabaabcabac", ".*ab.a.*a*a*.*b*b*", true));
    }

    public static String test(String s, String p, boolean expected) {
        return passed(Objects.equals(expected, new RegularExpressionMatching().isMatch(s, p)));
    }

    public static String passed(final boolean bool) {
        return bool ? "passed" : "failed";
    }


    public boolean isMatch(String s, String p) {
        final char[] strAr = s.toCharArray();
        final Character[][] decomposedRegex = decompose(p);
        return isMatch(strAr, 0, strAr.length, decomposedRegex, 0, decomposedRegex.length);
    }

    public static boolean isMatch(char[] src, int srcFrom, int srcTo,
                                  Character[][] decomposedRegex, int regexFrom, int regexTo) {
        boolean match = true;
        int strIndex = srcFrom;
        int regexIndex = regexFrom;
        while (strIndex < srcTo && regexIndex < decomposedRegex.length) {
            final Character[] pattern = decomposedRegex[regexIndex];
            if (onlyAlphabeticChars(pattern)) {
                if (src.length - strIndex < pattern.length) {
                    match = false;
                    break;
                }
                if (equals(pattern, src, strIndex)) {
                    strIndex += pattern.length;
                    regexIndex++;
                } else {
                    match = false;
                    break;
                }
                continue;
            }
            if (pattern.length == 1 && pattern[0] == '.') {
                regexIndex++;
                strIndex++;
                continue;
            }
            if (pattern.length == 2 && pattern[1] == '*') {
                if (pattern[0] == '.') {
                    if (regexIndex == decomposedRegex.length - 1) {
                        regexIndex++;
                        strIndex = src.length;
                        break;
                    }
                    for (int j = strIndex; j < src.length; j++) {
                        if (isMatch(src, j, src.length, decomposedRegex, regexIndex + 1, decomposedRegex.length)) {
                            strIndex = src.length;
                            regexIndex = decomposedRegex.length;
                        }
                    }
                    break;
                } else {
                    for (int j = strIndex; j < src.length; j++) {
                        if (src[j] != decomposedRegex[regexIndex][0]) {
                            break;
                        }
                        if (isMatch(src, j, src.length, decomposedRegex, regexIndex + 1, decomposedRegex.length)) {
                            strIndex = src.length;
                            regexIndex = decomposedRegex.length;
                            break;
                        }
                    }
                    while (strIndex < src.length && src[strIndex] == pattern[0]) {
                        strIndex++;
                    }
                    regexIndex++;
                }
            }
        }
        return match && strIndex >= src.length && (regexIndex >= decomposedRegex.length || onlyWildCardsLeft(decomposedRegex, regexIndex));
    }

    public static boolean equals(final Character[] matchingPattern, final char[] sourceString, final int startIndex) {
        boolean match = true;
        for (int i = startIndex; i < startIndex+matchingPattern.length; i++) {
            if (sourceString[i] != matchingPattern[i-startIndex]) {
                match = false;
                break;
            }
        }
        return match;
    }

    public static boolean onlyWildCardsLeft(final Character[][] decomposedRegex, int index) {
        boolean result = true;
        for (int i = index; i < decomposedRegex.length; i++) {
            if (onlyAlphabeticChars(decomposedRegex[i]) || decomposedRegex[i].length == 1 && decomposedRegex[i][0] == '.') {
                result = false;
                break;
            }
        }
        return result;
    }

    public static boolean onlyAlphabeticChars(final Character[] string) {
        return string[string.length-1] != '.' && string[string.length-1] != '*';
    }

    public static Character[][] decompose(final String p) {
        final List<Character[]> result = new ArrayList<>();
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '.' && (i == p.length() - 1 || p.charAt(i + 1) != '*')) {
                result.add(new Character[]{'.'});
                continue;
            }
            if (p.charAt(i) == '*') {
                if (result.size() == 0 || !(result.get(result.size() - 1).length == 2 && result.get (result.size()-1)[0] == '.')) {
                    if (p.charAt(i - 1) == '.') {
                        while (!result.isEmpty() && result.get(result.size()-1).length == 2 && result.get(result.size() - 1)[1] == '*') {
                                result.remove(result.size() - 1);
                        }
                    }
                    result.add(new Character[]{p.charAt(i - 1),'*'});
                }
                continue;
            }
            if (p.charAt(i) != '.' && p.charAt(i) != '*' && (i == p.length() - 1 || (i < p.length() - 1 && p.charAt(i + 1) != '*'))) {
                List<Character> sb = new ArrayList<>();
                while (p.charAt(i) != '.' && p.charAt(i) != '*' && (i == p.length() - 1 || p.charAt(i + 1) != '*')) {
                    sb.add(p.charAt(i));
                    if (i < p.length() - 1 && p.charAt(i + 1) != '.') {
                        i++;
                    } else {
                        break;
                    }
                }
                result.add(sb.toArray(Character[]::new));
            }
        }
        final Character[][] characters = new Character[result.size()][];
        int i = 0;
        for (Character[] pattern : result) {
            characters[i] = pattern;
            i++;
        }
        return characters;
    }
}
