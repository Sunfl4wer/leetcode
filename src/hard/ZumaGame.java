package hard;

import java.util.*;

public class ZumaGame {
    public static void main(final String[] args) {
        String board = "BRWGWYY";
        String hand = "YGBWY";

        long start = System.currentTimeMillis();
        System.out.println(new ZumaGame().findMinStep(board, hand));
        long end = System.currentTimeMillis();
        System.out.println("Run time: " + (end-start)/1000.0 + "s");
    }

    private int totalGivenBalls;
    private int noBallsToWin;

    public int findMinStep(final String board, final String hand) {
        List<Character> target = new ArrayList<>();
        for (int i = 0; i < board.length(); i++) {
            target.add(board.charAt(i));
        }
        Map<Character, Integer> ballToQuantity = new HashMap<>();
        for (char c : hand.toCharArray()) {
            if (ballToQuantity.containsKey(c)) {
                ballToQuantity.put(c, ballToQuantity.get(c)+1);
            } else {
                ballToQuantity.put(c, 1);
            }
        }
        totalGivenBalls = hand.length();
        noBallsToWin = totalGivenBalls +1;

        shoot(target, ballToQuantity, new HashSet<>());

        return noBallsToWin== totalGivenBalls +1 ? -1 : noBallsToWin;
    }

    private void shoot(final List<Character> target, final Map<Character, Integer> ballToQuantity, final Set<String> traversed) {
        String key = buildKey(target, ballToQuantity);
        if (traversed.contains(key)) {
            return;
        } else {
            traversed.add(key);
        }
        int match = containsMatchingBalls(target);
        while (match != -1) {
            while (match < target.size()-1 && target.get(match) == target.get(match+1)) {
                target.remove(match);
            }
            target.remove(match);
            match = containsMatchingBalls(target);
        }

        final Collection<Integer> quantities = ballToQuantity.values();
        int totalBallsLeft = 0;
        for (int quantity : quantities) {
            totalBallsLeft+=quantity;
        }
        if (target.isEmpty()) {
            if (totalGivenBalls - totalBallsLeft < noBallsToWin) {
                noBallsToWin = totalGivenBalls - totalBallsLeft;
            }
            return;
        }

        if (!target.isEmpty() && totalBallsLeft == 0 || nonMatched(target, ballToQuantity) || endGame(target, ballToQuantity)) {
            return;
        }

        for (Character ball : ballToQuantity.keySet()) {
            if (ballToQuantity.get(ball) != 0 && target.contains(ball)) {
                List<Integer> indexes = findPossibleShootingIndex(target, ball);
                if (indexes.isEmpty()) {
                    continue;
                } else {
                    for (Integer index : indexes) {
                        List<Character> t = new ArrayList<>(target);
                        t.add(index, ball);
                        Map<Character, Integer> btq = new HashMap<>(ballToQuantity);
                        btq.put(ball, ballToQuantity.get(ball) - 1);
                        shoot(t, btq, traversed);
                    }
                }
            }
        }
    }

    private static String buildKey(final List<Character> characters, final Map<Character, Integer> ballToQuantity) {
        StringBuilder sb = new StringBuilder();
        for (Character c : characters) {
            sb.append(c);
        }
        sb.append('-');
        Character[] arr = ballToQuantity.keySet().toArray(Character[]::new);
        Arrays.sort(arr);
        for (Character c : arr) {
            for (int i = 0; i < ballToQuantity.get(c); i++) {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    private static boolean endGame(final List<Character> target, final Map<Character, Integer> ballToQuantity) {
        for (Character ball : ballToQuantity.keySet()) {
            if (ballToQuantity.get(ball) >= 1) {
                return false;
            } else {
                for (int i = 0; i < target.size()-1; i++) {
                    if (target.get(i) == ball && target.get(i+1) == ball) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static List<Integer> findPossibleShootingIndex(final List<Character> target, final Character ball) {
        List<Integer> indexes = new ArrayList<>();
        int i = 0;
        while (i < target.size()) {
            if (target.get(i) == ball) {
                if (i == target.size()-1) {
                    indexes.add(i);
                    i++;
                } else {
                    int j = i + 1;
                    while (j < target.size()) {
                        if (target.get(i) != target.get(j)) {
                            break;
                        }
                        j++;
                    }
                    if (j - i <= 2) {
                        indexes.add(i);
                    }
                    i = i + (j - i);
                }
            } else {
                indexes.add(i);
                i++;
            }
        }

        return indexes;
    }

    private static boolean nonMatched(final List<Character> target, final Map<Character, Integer> noBallsToQuantity) {
        for (Character ball : target) {
            if (noBallsToQuantity.containsKey(ball) && noBallsToQuantity.get(ball) != 0)
                return false;
        }
        return true;
    }

    private static int containsMatchingBalls(final List<Character> target) {
        if (target.size() <=2) {
            return -1;
        }
        int i = 0;
        while (i < target.size()-1) {
            int j = i+1;
            while (j < target.size()) {
                if (target.get(i) != target.get(j)) {
                    break;
                }
                j++;
            }
            if (j-i >= 3) {
                break;
            }
            i = i+(j-i);
        }
        return i>=target.size()-1 ? -1 : i;
    }
}
