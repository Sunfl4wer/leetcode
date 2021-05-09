package hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SwimInRisingWater {
    public static void main(final String[] args) {
        int[][] testCase1 = {{35, 19, 17, 25, 4, 10}, {8, 18, 29, 21, 28, 31}, {15, 5, 33, 2, 13, 3}, {26, 20, 27, 23, 11, 1}, {6, 14, 24, 7, 12, 16}, {30, 34, 32, 22, 0, 9}};
        int[][] testCase2 = {{26, 99, 80, 1, 89, 86, 54, 90, 47, 87}, {9, 59, 61, 49, 14, 55, 77, 3, 83, 79}, {42, 22, 15, 5, 95, 38, 74, 12, 92, 71}, {58, 40, 64, 62, 24, 85, 30, 6, 96, 52}, {10, 70, 57, 19, 44, 27, 98, 16, 25, 65}, {13, 0, 76, 32, 29, 45, 28, 69, 53, 41}, {18, 8, 21, 67, 46, 36, 56, 50, 51, 72}, {39, 78, 48, 63, 68, 91, 34, 4, 11, 31}, {97, 23, 60, 17, 66, 37, 43, 33, 84, 35}, {75, 88, 82, 20, 7, 73, 2, 94, 93, 81}};
        int[][] testCase3 = {{0,3},{1,2}};
        int[][] testCase4 = {{29,28,12,2,24,11},{17,30,25,9,13,33},{1,0,34,35,23,19},{31,22,4,26,6,3},{21,14,15,8,32,20},{5,18,7,27,16,10}};
        int[][] testCase5 = {{31,28,33,0,8,57,86,99,23,98},{25,90,20,73,34,65,29,9,42,46},{17,84,10,4,40,5,41,21,71,79},{13,70,69,81,63,93,77,1,94,53},{38,87,61,50,92,2,15,95,82,68},{44,72,88,47,27,91,37,48,83,16},{3,30,96,66,7,58,76,54,19,64},{85,45,60,11,51,26,6,22,74,32},{43,12,62,59,89,52,36,97,49,78},{75,24,14,67,56,35,55,39,80,18}};
        int[][] testCase6 =
               {{105,209,171,91,64,394,279,11,45,84,207,321,216,197,381,377,78,19,203,198},{141,10,335,170,265,104,338,40,397,376,346,356,212,154,280,177,247,90,87,360},{99,59,242,149,344,172,276,230,133,193,284,345,46,363,30,142,295,70,224,200},{251,88,379,72,319,272,243,165,180,182,387,264,23,67,137,342,125,139,144,367},{94,211,151,37,290,112,343,157,300,271,260,373,369,294,289,57,44,12,20,340},{220,368,186,277,181,187,273,214,315,337,328,18,231,223,331,75,275,96,135,150},{202,74,27,184,399,341,49,62,261,86,314,383,302,257,61,148,268,120,36,25},{15,253,285,185,226,146,126,122,83,361,110,234,183,239,52,190,152,81,136,188},{39,199,358,26,301,116,32,386,29,138,393,159,102,140,370,227,282,111,5,33},{189,35,132,54,210,235,28,353,281,127,318,58,100,286,384,24,307,252,80,103},{244,176,124,79,161,355,218,398,392,380,225,121,178,352,329,322,167,51,313,85},{107,118,351,287,324,283,48,320,82,364,357,16,219,330,89,143,241,262,71,191},{95,97,3,7,270,249,213,339,362,298,4,258,248,390,299,306,156,164,109,229},{221,9,228,160,274,263,374,147,98,63,13,41,326,396,349,372,385,317,325,266},{53,131,173,312,174,114,250,119,163,22,246,92,278,365,292,215,14,304,204,73},{233,323,366,130,378,305,311,93,134,217,297,327,232,194,240,1,208,6,310,47},{69,101,332,195,254,236,50,166,56,168,267,17,359,347,65,316,238,296,348,222},{76,123,129,293,391,2,245,108,303,38,66,55,43,256,162,60,179,77,336,21},{196,388,333,395,42,382,291,237,288,375,128,145,192,158,350,259,206,34,334,255},{201,175,153,68,205,155,115,269,389,169,371,308,117,31,354,8,113,309,106,0}};
        long start = System.currentTimeMillis();
        System.out.println(new SwimInRisingWater().swimInWater(testCase6));
        long end = System.currentTimeMillis();
        System.out.println("Execution time: " + (end - start) + " ms");
    }

    private static int min;
    private static int start;
    private static int end;

    public int swimInWater(final int[][] grid) {
        min = grid.length * grid.length - 1;
        start = grid[0][0];
        end = grid[grid.length - 1][grid.length - 1];
        if (start == min || end == min) {
            return min;
        }
        if (Math.min(grid[0][1], grid[1][0]) >= min - 2 && Math.min(grid[0][1], grid[1][0]) > start && Math.min(grid[0][1], grid[1][0]) > end) {
            return Math.min(grid[0][1], grid[1][0]);
        }
        if (Math.min(grid[grid.length - 1][grid.length - 2], grid[grid.length - 2][grid.length - 1]) >= min - 2 && Math.min(grid[grid.length - 1][grid.length - 2], grid[grid.length - 2][grid.length - 1]) > grid[grid.length - 1][grid.length - 1] && Math.min(grid[grid.length - 1][grid.length - 2], grid[grid.length - 2][grid.length - 1]) > grid[0][0]) {
            return Math.min(grid[grid.length - 1][grid.length - 2], grid[grid.length - 2][grid.length - 1]);
        }
        int[] traversed = new int[grid.length * grid.length];
        findRoute(grid, traversed, new int[]{0, 0}, new ArrayList<>());
        return min;
    }

    private static void findRoute(int[][] grid, int[] traversed, int[] current, List<Integer> route) {
        route.add(grid[current[0]][current[1]]);
        traversed[grid[current[0]][current[1]]] = 1;
        grid[current[0]][current[1]] = -1;
        if (current[0] == grid.length - 1 && current[1] == grid.length - 1) {
            for (int i = traversed.length - 1; i >= 0; i--) {
                if (traversed[i] == 1) {
                    if (min > i) {
                        min = i;
                    }
                    break;
                }
            }
//            System.out.println("Min: " + min + " - " + route);
            return;
        }
//        System.out.println("Min: " + "   " + " - " + route);

        List<int[]> nextMoves = generateNextMove(current, grid);
        for (int i = traversed.length - 1; i >= 0; i--) {
            if (traversed[i] == 1) {
                if (min <= i) {
                    nextMoves = new ArrayList<>();
                }
                break;
            }
        }
        for (int[] nextMove : nextMoves) {
            int[] trv = Arrays.copyOf(traversed, traversed.length);
            int[][] gr = new int[grid.length][];
            for (int i = 0; i < grid.length; i++) {
                gr[i] = Arrays.copyOf(grid[i], grid.length);
            }
            findRoute(gr, trv, nextMove, new ArrayList<>(route));
        }
    }

    private static boolean validNextMove(int[] nextMove, int[][] grid) {
        return nextMove[0] < grid.length && nextMove[0] > -1
                && nextMove[1] < grid.length && nextMove[1] > -1
                && grid[nextMove[0]][nextMove[1]] != -1
                && grid[nextMove[0]][nextMove[1]] < min;
    }

    private static List<int[]> generateNextMove(int[] current, int[][] grid) {
        if (min == Math.max(start, end) || min == Math.max(Math.min(grid[grid.length-1][grid.length-2], grid[grid.length-2][grid.length-1]), Math.min(grid[0][1], grid[1][0]))) {
            return new ArrayList<>();
        }
        List<int[]> result = new ArrayList<>();
        int cx = current[0];
        int cy = current[1];
        int[][] next = new int[4][2];
        next[0][0] = cx+1;
        next[0][1] = cy;
        next[1][0] = cx-1;
        next[1][1] = cy;
        next[2][0] = cx;
        next[2][1] = cy-1;
        next[3][0] = cx;
        next[3][1] = cy+1;
        for (int[] move : next) {
            if (validNextMove(move, grid)) {
                result.add(move);
            }
        }
        if (result.size() > 1) {
          for (int i = 0; i < result.size()-1; i++) {
              int[] minPos = result.get(i);
              int min = 0;
              for (int j = i+1; j < result.size(); j++) {
                  if (grid[minPos[0]][minPos[1]] > grid[result.get(j)[0]][result.get(j)[1]]) {
                      minPos = result.get(j);
                      min = j;
                  }
              }
              if (min != i) {
                  int[] temp = result.get(min);
                  result.set(min, result.get(i));
                  result.set(i, temp);
              }
          }
        }

        return result;
    }
}
