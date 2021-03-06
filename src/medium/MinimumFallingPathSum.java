package medium;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class MinimumFallingPathSum {

    public static void main(String[] args) {
       int[][] testCase1 = {{2,1,3},{6,5,4},{7,8,9}};
       int[][] testCase2 = {{-19,-1,-96,48,-94,36,16,55,-42,37,-59,6,-32,96,95,-58,13,-34,94,85},{17,44,36,-29,84,80,-34,50,-99,64,13,91,-27,25,-36,57,20,98,-100,-72},{-92,-75,86,90,-4,90,64,56,50,-63,10,-15,90,-66,-66,32,-69,-78,1,60},{21,51,-47,-43,-14,99,44,90,8,11,99,-62,57,59,69,50,-69,32,85,13},{-28,90,12,-18,23,61,-55,-97,6,89,36,26,26,-1,46,-50,79,-45,89,86},{-85,-10,49,-10,2,62,41,92,-67,85,86,27,89,-50,77,55,22,-82,-94,-98},{-50,53,-23,55,25,-22,76,-93,-7,66,-75,42,-35,-96,-5,4,-92,13,-31,-100},{-62,-78,8,-92,86,69,90,-37,81,97,53,-45,34,19,-19,-39,-88,-75,-74,-4},{29,53,-91,65,-92,11,49,26,90,-31,17,-84,12,63,-60,-48,40,-49,-48,88},{100,-69,80,11,-93,17,28,-94,52,64,-86,30,-9,-53,-8,-68,-33,31,-5,11},{9,64,-31,63,-84,-15,-30,-10,67,2,98,73,-77,-37,-96,47,-97,78,-62,-17},{-88,-38,-22,-90,54,42,-29,67,-85,-90,-29,81,52,35,13,61,-18,-94,61,-62},{-23,-29,-76,-30,-65,23,31,-98,-9,11,75,-1,-84,-90,73,58,72,-48,30,-81},{66,-33,91,-6,-94,82,25,-43,-93,-25,-69,10,-71,-65,85,28,-52,76,25,90},{-3,78,36,-92,-52,-44,-66,-53,-55,76,-7,76,-73,13,-98,86,-99,-22,61,100},{-97,65,2,-93,56,-78,22,56,35,-24,-95,-13,83,-34,-51,-73,2,7,-86,-19},{32,94,-14,-13,-6,-55,-21,29,-21,16,67,100,77,-26,-96,22,-5,-53,-92,-36},{60,93,-79,76,-91,43,-95,-16,74,-21,85,43,21,-68,-32,-18,18,100,-43,1},{87,-31,26,53,26,51,-61,92,-65,17,-41,27,-42,-14,37,-46,46,-31,-74,23},{-67,-14,-20,-85,42,36,56,9,11,-66,-59,-55,5,64,-29,77,47,44,-33,-77}};
       int[][] testCase3 = {{-52,47,-77,-56},{59,-34,63,-80},{-71,40,-91,15},{82,7,36,-95}};
       int[][] testCase4 = {{-21,4,-10,-2,17,36},{-15,-26,98,-48,56,-14},{-28,-88,73,41,81,-10},{36,-77,-21,-59,-92,90},{-57,-71,-61,-25,9,-70},{98,97,-71,-19,36,76}};
       int[][] testCase5 = new int[100][100];

        try {
            File myObj = new File("/Users/son.nguyenthai/IdeaProjects/leetcode/src/medium/test-case");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] rows = data.split(";");
                for (int i = 0; i < rows.length; i++) {
                    String[] columns = rows[i].split(",");
                    for (int j = 0; j < columns.length; j++) {
                        testCase5[i][j] = Integer.parseInt(columns[j]);
                    }

        }
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }

    long start = System.currentTimeMillis();
    System.out.println(Objects.equals(13, new MinimumFallingPathSum().minFallingPathSum(testCase1)));
//    System.out.println(Objects.equals(-1428, new MinimumFallingPathSum().minFallingPathSum(testCase2)));
    //       System.out.println(Objects.equals(-354, new MinimumFallingPathSum().minFallingPathSum(testCase4)));
    long end = System.currentTimeMillis();
    System.out.println("Execution time: " + (end - start) + " ms");
  }

  private static int min = Integer.MAX_VALUE;

  public int minFallingPathSum(int[][] matrix) {
    risingUp(matrix, 0);
    return min;
  }

  private static void risingUp(int[][] matrix, int i) {
    if (i < matrix.length - 1) {
      risingUp(matrix, i + 1);
      for (int j = 0; j < matrix[i].length; j++) {
        int current = matrix[i][j];
        matrix[i][j] = matrix[i + 1][j] + current;
        matrix[i][j] = j - 1 < 0 ? matrix[i][j] : Math.min(matrix[i + 1][j - 1] + current, matrix[i][j]);
        matrix[i][j] = j + 1 > matrix[i + 1].length - 1 ?
            matrix[i][j] :
            Math.min(matrix[i + 1][j + 1] + current, matrix[i][j]);
      }
    }
    if (i > 0) {
      return;
    }
    for (int j = 0; i < matrix.length && j < matrix[i].length; j++) {
      min = Math.min(min, matrix[i][j]);
    }
  }
}
