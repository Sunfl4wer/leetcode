package medium.minimummovestoequalarrayelements;

import medium.eliminatemaximumnumberofmonsters.EliminateMaximumNumberOfMonsters;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MinimumMovesToEqualArrayElements {
    private static int[] testCase1;
    public static void main(final String[] args) {
        final Solution solution = new Solution();
        final Solution1 solution1 = new Solution1();

        int[] test1 = new int[]{1,1,1};
        int[] test2 = new int[]{5,6,8,8,5};
        int[] test3 = new int[]{83,86,77,15,93,35,86,92,49,21};

        long start = System.currentTimeMillis();
//        System.out.println(solution.minMoves(Arrays.copyOf(testCase1, testCase1.length)));
        long end = System.currentTimeMillis();
        System.out.println("Execution time: " + (end-start)/1000.0 + "s");


        start = System.currentTimeMillis();
        System.out.println(solution1.minMoves(Arrays.copyOf(test2, test2.length)));
        end = System.currentTimeMillis();
        System.out.println("Execution time: " + (end-start)/1000.0 + "s");
    }

    static {

        try {
            InputStream is = MinimumMovesToEqualArrayElements.class.getResourceAsStream("test-case-48");
            Scanner scanner = new Scanner(is);
            int i = 0;
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                List<String> elements = Arrays.asList(line.split(","));
                if (i == 0) {
                    int[] dists = new int[elements.size()];
                    for (int j = 0; j < elements.size(); j++) {
                        dists[j] = Integer.valueOf(elements.get(j)).intValue();
                    }
                    if (testCase1 == null) {
                        testCase1 = new int[dists.length];
                    }
                    testCase1 = dists;
                }
                i++;
            }
        } catch (Exception e) {

        }
    }
}
