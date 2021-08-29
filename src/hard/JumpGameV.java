package hard;

import java.util.Arrays;

public class JumpGameV {
  public static void main(final String[] args) {
    int[] arr1 = {6,4,14,6,8,13,9,7,10,6,12};
    int[] arr2 = {7,6,5,4,3,2,1};
    int[] arr3 = {7,1,7,1,7,1};
    int d = 2;
    System.out.println(new JumpGameV().maxJumps(arr1, d));
  }

  public int maxJumps(int[] arr, int d) {
    int[] noJumps = new int[arr.length];
    Arrays.fill(noJumps, -1);
    for (int i = 0; i < arr.length; i++) {
      maxJumps(arr, i, d, noJumps);
    }
    Arrays.sort(noJumps);
    return noJumps[noJumps.length-1]+1;
  }

  private int maxJumps(int[] arr, int index, int dis, int[] noJumps) {
    if (noJumps[index] != -1) {
      return noJumps[index];
    }
    int result = 0;
    for (int d = dis; d > 0; d--) {
      boolean canJumpLeft = index - d >= 0;
      boolean canJumpRight = index + d < arr.length;
      if (index - d >= 0) {
        for (int i = index - 1; i >= index - d; i--) {
          if (arr[index] <= arr[i]) {
            canJumpLeft = false;
            break;
          }
        }
      }
      if (index + d < arr.length) {
        for (int i = index + 1; i <= index + d; i++) {
          if (arr[index] <= arr[i]) {
            canJumpRight = false;
            break;
          }
        }
      }
      if (!canJumpLeft && !canJumpRight && d == 1)  {
        noJumps[index] = 0;
        return 0;
      }
      int noJumpsLeft = canJumpLeft ? maxJumps(arr, index - d, dis, noJumps) : 0;
      int noJumpsRight = canJumpRight ? maxJumps(arr, index + d, dis, noJumps) : 0;
      result = Math.max(1 + Math.max(noJumpsLeft, noJumpsRight), result);
    }
    noJumps[index] = result;
    return result;
  }
}
