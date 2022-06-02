package medium;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NumberOfIslands {
    public static void main(final String[] args) {
        Solution solution = new NumberOfIslands().new Solution();
        char[][] test1 = {{'1','1','1','1','1','0','1','1','1','1'}
                ,{'1','0','1','0','1','1','1','1','1','1'}
                ,{'0','1','1','1','0','1','1','1','1','1'}
                ,{'1','1','0','1','1','0','0','0','0','1'}
                ,{'1','0','1','0','1','0','0','1','0','1'}
                ,{'1','0','0','1','1','1','0','1','0','0'}
                ,{'0','0','1','0','0','1','1','1','1','0'}
                ,{'1','0','1','1','1','0','0','1','1','1'}
                ,{'1','1','1','1','1','1','1','1','0','1'}
                ,{'1','0','1','1','1','1','1','1','1','0'}};
        int res = solution.numIslands(test1);
        System.out.println(res == 2);
    }

    class Solution {
        public Solution() {

        }
        private Map<Flag, Set<Island>> flags;
        private void add(final Flag flag, final Island island) {
            if (!flags.containsKey(flag)) {
                final Set<Island> islands = new HashSet<>();
                flags.put(flag, islands);
            }
            flags.get(flag).add(island);
        }

        private void addAll(final Flag flag, final Set<Island> islands) {
            if (!flags.containsKey(flag)) {
                final Set<Island> newIslands = new HashSet<>();
                flags.put(flag, newIslands);
            }
            for (Island island : islands) {
                island.setFlag(flag);
            }
            flags.get(flag).addAll(islands);
        }

        public int numIslands(char[][] grid) {
            flags = new HashMap<>();
            Land[][] landGrid = new Land[grid.length][grid[0].length];
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == '1') {
                        Land land;
                        if (j == 0 || grid[i][j-1] == '0') {
                            land = new Land();
                            Island island = new Island();
                            land.setIsland(island);
                        } else {
                            land = landGrid[i][j-1].clone();
                        }
                        add(land.getIsland().getFlag(), land.getIsland());
                        if (i > 0 && grid[i-1][j] == '1') {
                            if (land.getIsland().getFlag() == null || landGrid[i-1][j].getIsland().getFlag() != land.getIsland().getFlag()) {
                                Set<Island> islands = flags.remove(land.getIsland().getFlag());
                                addAll(landGrid[i-1][j].getIsland().getFlag(), islands);
                                land.getIsland().setFlag(landGrid[i-1][j].getIsland().getFlag());
                            }
                        }
                        landGrid[i][j] = land;
                    }
//                    System.out.println("Row: " + i + " col: " + j + " flags size: " + flags.size() /*+ " flags: " + .toString(flags.toArray())*/);
//                    System.out.println(printMatrix(landGrid));
                }
            }
            return flags.size();
        }

        public String printMatrix(final Land[][] landGrid) {
            StringBuilder stringBuilder = new StringBuilder();
            int maxLength = 8;
            for (int i = 0; i < landGrid.length; i++) {
                for (int j = 0; j < landGrid[0].length; j++) {
                    if (landGrid[i][j] != null) {
                        String address = landGrid[i][j].getIsland().getFlag().toString().split("@")[1];
                        maxLength = Math.max(address.length(), maxLength);
                        stringBuilder.append(address);
                        for (int k =0; k < maxLength-address.length(); k++) {
                            stringBuilder.append(" ");
                        }
                        stringBuilder.append(" ");
                    } else {
                        stringBuilder.append("        ").append(" ");
                    }
                }
                stringBuilder.append("\n");
            }

            return stringBuilder.toString();
        }

        class Land {
            private Island island;
            public Land() {

            }
            public Land(Island island) {
                this.island = island;
            }
            public void setIsland(final Island island) {
                this.island = island;
            }
            public Island getIsland() {
                return this.island;
            }

            public Land clone() {
                return new Land(this.island);
            }
        }

        class Island {
            private Flag flag;
            public void setFlag(final Flag flag) {
                this.flag = flag;
            }
            public Flag getFlag() {
                return this.flag;
            }

            public Island() {
                this.flag = new Flag();
            }
        }

        class Flag {

        }
    }
}
