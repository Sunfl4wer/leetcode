package medium.minimummovestoequalarrayelements;

import java.util.Arrays;

class Solution {
    public int minMoves(int[] nums) {
        //Arrays.sort(nums);
        int max = 1;
        int min = 2;
        int res = 0;
        if (max == min) {
            return res;
        }
        while (max != min) {
//            Arrays.sort(nums);
//            System.out.println(Arrays.toString(nums));
            int smallest = nums[0];
            int secondSmallest = nums[0];
            int secondSmallestIdx = 0;

            for (int i = 0; i < nums.length; i++) {
                if (nums[i] < smallest) {
                    smallest = nums[i];
                }
                if (secondSmallest == smallest && nums[i] > smallest) {
                    secondSmallest = nums[i];
                    secondSmallestIdx = i;
                }
                if (nums[i] < secondSmallest && nums[i] > smallest) {
                    secondSmallest = nums[i];
                    secondSmallestIdx = i;
                }
            }
            int diff = (secondSmallest - smallest);
            res += diff;
            int largest = nums[0]+diff;
            smallest = nums[0]+diff;
            for (int i = 0; i < nums.length; i++) {
                if (i == secondSmallestIdx) {
                    continue;
                }
                nums[i] += diff;
                if (nums[i] < smallest) {
                    smallest = nums[i];
                }

                if (nums[i] > largest) {
                    largest = nums[i];
                }
            }
            max = largest;
            min = smallest;
        }
        return res;
    }
}
