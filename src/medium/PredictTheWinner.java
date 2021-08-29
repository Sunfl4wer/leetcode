package medium;

import java.util.Arrays;

public class PredictTheWinner {
  private static long player1Win;
  private static long player2Win;;
  public static void main(final String[] args) {
    int[] test = {1,5,2};
    int[] test1 = {1,2};
    int[] test2 = {1,5,233,7};
    int[] test3 = {306416,2889306,7742619,3897090,6904996,1954213,8815586,9031637,256723,4662300,3024674,5433146,8190137,5093129,9258336,3161122,3217503,1331124,9213976,8810715};
    System.out.println(new PredictTheWinner().predictTheWinner(test3));
  }

  public boolean predictTheWinner(int[] nums) {
    long player1 = 0;
    long player2 = 0;
    int startIndex = 0;
    int endIndex = nums.length-1;
    return move(startIndex, endIndex, nums, player1, player2);
  }

  public static boolean move(int startIndex, int endIndex, int[] nums, long player1, long player2) {
    long temp1 = 0;
    long temp2 = 0;
    while (true) {
      if (startIndex > endIndex) {
        break;
      }
      winningMove(startIndex+1, endIndex,nums, player1+nums[startIndex], player2, 2);
      temp1 = player1Win;
      System.out.println(Arrays.toString(new long[]{player1Win, player2Win}));
      player1Win = 0;
      player2Win = 0;
      winningMove(startIndex, endIndex-1,nums, player1+nums[endIndex], player2, 2);
      temp2 = player1Win;
      System.out.println(Arrays.toString(new long[]{player1Win, player2Win}));
      player1Win = 0;
      player2Win = 0;
      System.out.println(Math.max(temp1, temp2));
      if (temp1 >= temp2) {
        player1+=nums[startIndex];
        startIndex+=1;
      } else {
        player1+=nums[endIndex];
        endIndex-=1;
      }
      if (startIndex > endIndex) {
        break;
      }
      winningMove(startIndex+1, endIndex,nums, player1, player2+nums[startIndex], 1);
      temp1 = player2Win;
      player1Win = 0;
      player2Win = 0;
      winningMove(startIndex, endIndex-1,nums, player1, player2+nums[endIndex], 1);
      temp2 = player2Win;
      player1Win = 0;
      player2Win = 0;
      if (temp1 >= temp2) {
        player2+=nums[startIndex];
        startIndex+=1;
      } else {
        player2+=nums[endIndex];
        endIndex-=1;
      }
    }
    return player1 >= player2;
  }

  public static void winningMove(int startIndex, int endIndex, int[] nums, long player1, long player2, int turn) {
    if (startIndex > endIndex) {
      if (player1 >= player2) {
        player1Win += 1;
      } else {
        player2Win += 1;
      }
      return;
    }
    if (turn == 1) {
      winningMove(startIndex+1, endIndex, nums, player1+nums[startIndex], player2, 2);
      winningMove(startIndex, endIndex-1, nums, player1+nums[endIndex], player2, 2);
    } else {
      winningMove(startIndex + 1, endIndex, nums, player1, player2 + nums[startIndex], 1);
      winningMove(startIndex, endIndex - 1, nums, player1, player2 + nums[endIndex], 1);
    }
  }
}
