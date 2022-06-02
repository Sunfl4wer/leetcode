package medium.gridgame;

import common.AbstractProblem;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class GridGame extends AbstractProblem {

    private static int[][] testCase4;
    public static Solution solution;

    public static void main(final String[] args) {
        final Solution solution = new Solution();
        GridGame.solution = solution;
        int[][] test1 = new int[][]{{3,3,1},{8,5,2}};
        int[][] test2 = new int[][]{{1,3,1,15},{1,3,3,1}};
        int[][] test3 = new int[][]{{20,3,20,17,2,12,15,17,4,15},{20,10,13,14,15,5,2,3,14,3}};
        System.out.println(solution.gridGame(test1) == 4);
        System.out.println(solution.gridGame(test2) == 7);
        System.out.println(solution.gridGame(test3) == 63);

        long start = System.currentTimeMillis();
        System.out.println(solution.gridGame(testCase4));
        long end = System.currentTimeMillis();
        System.out.println("Execution time: " + (end-start)/1000.0 +"s");
    }

    static {

        try {
            InputStream is = GridGame.class.getResourceAsStream("test-case");
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
                    if (testCase4 == null) {
                        testCase4 = new int[2][dists.length/2];
                    }
                    testCase4[0] = Arrays.copyOfRange(dists, 0, elements.size()/2);
                    testCase4[0] = Arrays.copyOfRange(dists, elements.size()/2, elements.size());
                }
                i++;
            }
        } catch (Exception e) {

        }
    }

    @Override
    public void executeTestCase(Object result, Object... params) {
        int[][] grid = (int[][]) params[0];
        GridGame.solution.gridGame(grid);
    }
}
