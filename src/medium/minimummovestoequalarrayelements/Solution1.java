package medium.minimummovestoequalarrayelements;

import java.util.Arrays;

class Solution1 {
    public int minMoves(int[] nums) {
        //Arrays.sort(nums);
        int max = 1;
        int min = 2;
        int res = 0;
        if (max == min) {
            return res;
        }
        Arrays.sort(nums);
        int smallest = nums[0];
        int secondSmallest = nums[0];
        int secondSmallestIdx = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > smallest) {
                secondSmallest = nums[i];
                secondSmallestIdx = i;
                break;
            }
        }
        while (max != min) {
            System.out.println(Arrays.toString(nums));
            int diff = (secondSmallest - smallest);
            res += diff;
            int largest = nums[0]+diff;
            smallest = nums[0]+diff;
            secondSmallest = secondSmallest+diff;
            boolean first = true;
            int smallestIdx = 0;
            for (int i = 0; i < nums.length; i++) {
                if (i == secondSmallestIdx && first) {
                    first = false;
                    continue;
                }
                nums[i] += diff;
                if (nums[i] < smallest) {
                    secondSmallest = smallest;
                    secondSmallestIdx = smallestIdx;
                    smallest = nums[i];
                    smallestIdx = i;
                } else if (nums[i] < secondSmallest && nums[i] != smallest) {
                    secondSmallest = nums[i];
                    secondSmallestIdx = i;
                }

                if (nums[i] > largest) {
                    largest = nums[i];
                }
            }
            Arrays.sort(nums);
            max = largest;
            min = smallest;
        }
        return res;
    }
}
