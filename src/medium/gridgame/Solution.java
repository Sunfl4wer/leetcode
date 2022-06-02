package medium.gridgame;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    public long gridGame(int[][] grid) {
        Map<String, String> map = new HashMap<>();
        map.forEach((k,v) -> System.out.println(k+", "+v));
        return minimizeSecondRobotScore(grid);


    }

    private long minimizeSecondRobotScore(int[][] grid) {
        long totalTopArrayScore = 0;
        long totalBottomArrayScore = 0;
        long secondRobotScore = Long.MIN_VALUE;
        long minSecondRobotScore = Long.MAX_VALUE;
        long topArrayScore = grid[0][0];
        long bottomArrayScore = grid[1][0];
        for (int i = 1; i < grid[1].length; i++) {
            bottomArrayScore+=grid[1][i];
        }
        for (int i = 0; i < grid[1].length; i++) {
            totalTopArrayScore+=grid[0][i];
        }
        totalBottomArrayScore = bottomArrayScore;

        secondRobotScore = Math.max(totalTopArrayScore-topArrayScore, totalBottomArrayScore-bottomArrayScore);
        minSecondRobotScore = Math.min(minSecondRobotScore, secondRobotScore);

        for (int i = 1; i < grid[0].length; i++) {
            topArrayScore+=grid[0][i];
            bottomArrayScore-=grid[1][i-1];

            secondRobotScore = Math.max(totalTopArrayScore-topArrayScore, totalBottomArrayScore-bottomArrayScore);
            minSecondRobotScore = Math.min(minSecondRobotScore, secondRobotScore);
        }
        return minSecondRobotScore;
    }
}
