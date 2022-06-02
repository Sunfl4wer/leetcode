package medium.eliminatemaximumnumberofmonsters;

import hard.MinimumIntervalToIncludeEachQuery;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class EliminateMaximumNumberOfMonsters {
    private static int[][] testCase1;
    public static void main(final String[] args) {
        final int[][] test1 = new int[][]{new int[]{46,33,44,42,46,36,7,36,31,47,38,42,43,48,48,25,28,44,49,47,29,32,30,6,42,9,39,48,22,26,50,34,40,22,10,45,7,43,24,18,40,44,17,39,36},
                new int[]{1,2,1,3,1,1,1,1,1,1,1,1,1,1,7,1,1,3,2,2,2,1,2,1,1,1,1,1,1,1,1,6,1,1,1,8,1,1,1,3,6,1,3,1,1}};

        final Solution1 sol1 = new Solution1();
        long start = System.currentTimeMillis();
        System.out.println(sol1.eliminateMaximum(testCase1[0], testCase1[1]));
        long end = System.currentTimeMillis();
        System.out.println("Execution time: " + (end-start)/1000.0 + "s");

        final Solution2 sol2 = new Solution2();
        start = System.currentTimeMillis();
        System.out.println(sol2.eliminateMaximum(testCase1[0], testCase1[1]));
        end = System.currentTimeMillis();
        System.out.println("Execution time: " + (end-start)/1000.0 + "s");

        final Solution3 sol3 = new Solution3();
        start = System.currentTimeMillis();
        System.out.println(sol3.eliminateMaximum(testCase1[0], testCase1[1]));
        end = System.currentTimeMillis();
        System.out.println("Execution time: " + (end-start)/1000.0 + "s");
    }

    static {

        try {
            InputStream is = EliminateMaximumNumberOfMonsters.class.getResourceAsStream("test-case");
            Scanner scanner = new Scanner(is);
            int i = 0;
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                line = line.replace("]", "");
                line = line.replace("[", "");
                List<String> elements = Arrays.asList(line.split(","));
                if (i == 0) {
                    int[] dists = new int[elements.size()];
                    for (int j = 0; j < elements.size(); j++) {
                       dists[j] = Integer.valueOf(elements.get(j)).intValue();
                    }
                    if (testCase1 == null) {
                        testCase1 = new int[2][dists.length];
                    }
                    testCase1[0] = dists;
                }
                if (i == 1) {
                    int[] speeds = new int[elements.size()];
                    for (int j = 0; j < elements.size(); j++) {
                        speeds[j] = Integer.valueOf(elements.get(j)).intValue();
                    }
                    testCase1[1] = speeds;
                }
                i++;
            }
        } catch (Exception e) {

        }
    }
}
