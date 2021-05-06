package medium;

import java.util.*;

public class ShortEncodingOfWords {
    private static DictNode root = new DictNode();

    public static void main(String[] args) {
        System.out.println(new ShortEncodingOfWords().minimumLengthEncoding(new String[]{"time"}));
    }

    public int minimumLengthEncoding(String[] words) {
        Set<String> wordSet = new HashSet<>(Arrays.asList(words));
        int result =0;
        for (String word : wordSet) {
            result += traverseWord(word);
        }
        return result;
    }

    public static int traverseWord(String word) {
        int result = 0;
        boolean isNew = false;
        DictNode node = root;
        for (int i = word.length()-1; i > -1; i--) {
            char c = word.charAt(i);
            if (node.nextNodes[c-'a'] == null) {
                isNew = true;
                result-=node.wordLength;
            }
            if (isNew) {
                node.wordLength = 0;
                node.nextNodes[c-'a'] = new DictNode();
            }
            node = node.nextNodes[c-'a'];
        }
        if (isNew) {
            node.wordLength = word.length()+1;
            result+=node.wordLength;
        }
        return result;
    }

    public static class DictNode {
        public DictNode[] nextNodes;
        public int wordLength;

        public DictNode() {
            this.nextNodes = new DictNode[26];
            this.wordLength = 0;
        }
    }

//    public int minimumLengthEncoding(String[] words) {
//        int refLength = 0;
//        Arrays.sort(words, (a, b) -> b.length()-a.length());
//        Set<Integer> lengths = new HashSet<>();
//        for (String word : words) {
//            lengths.add(word.length());
//        }
//        Set<String> dict = new HashSet<>();
//        int minLength = words[words.length-1].length();
//        for (int k = 0; k < words.length; k++) {
//            if (dict.contains(words[k])) {
//                continue;
//            }
//            int maxLength = k == words.length-1 ? words[k].length() : words[k+1].length();
//            generateDict(dict, words[k], minLength, maxLength, lengths);
//            refLength = refLength + words[k].length() + 1;
//        }
//
//        return refLength;
//    }
//
//    public static void generateDict(Set<String> dict, String word, int minLength, int maxLength, Set<Integer> lengths) {
//        for (int i = word.length()-maxLength; word.length()-i >= minLength && i < word.length(); i++) {
//            if (!lengths.contains(word.length()-i)) {
//                continue;
//            }
//            StringBuilder sb = new StringBuilder();
//            for (int j = i; j < word.length(); j++) {
//                sb.append(word.charAt(j));
//            }
//            String key = sb.toString();
//            if (!dict.contains(key)) {
//                dict.add(key);
//            }
//        }
//    }

//    public int minimumLengthEncoding(String[] words) {
//        int referenceStringLength = 0;
//        int i = 0;
//        List<String> wrds = new ArrayList<>(Arrays.asList(words));
//        while (i < wrds.size()) {
//            String temp = wrds.get(i);
//            int limit = wrds.size();
//            int j = i + 1;
//            while(j < limit) {
//                if (match(wrds.get(j), temp)) {
//                    if (wrds.get(j).length() > temp.length()) {
//                        temp = wrds.get(j);
//                    }
//                    wrds.remove(j);
//                    limit = wrds.size();
//                    if (j != i+1)
//                        j++;
//                } else {
//                    j++;
//                }
//            }
//            referenceStringLength = referenceStringLength+temp.length()+1;
//            i++;
//        }
//        return referenceStringLength;
//    }
//
//    public static boolean match(String src, String ref) {
//        for (int i = 0; i < Math.min(src.length(), ref.length()); i++) {
//            if (src.charAt(src.length()-1-i) != ref.charAt(ref.length()-1-i))
//                return false;
//        }
//        return true;
//    }
}
