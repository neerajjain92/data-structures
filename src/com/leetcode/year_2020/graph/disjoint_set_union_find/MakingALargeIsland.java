package com.leetcode.year_2020.graph.disjoint_set_union_find;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/making-a-large-island/description/
 * 827. Making A Large Island
 */
public class MakingALargeIsland {

    private static int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) {
        System.out.println(largestIsland(new int[][]{
                {1, 1, 0, 1, 1, 0},
                {1, 1, 0, 1, 1, 0},
                {1, 1, 0, 1, 1, 0},
                {0, 0, 1, 0, 0, 0},
                {0, 0, 1, 1, 1, 0},
                {0, 0, 1, 1, 1, 0},
        }));
    }

    public static int largestIsland(int[][] grid) {
        DisjointSetImplementation disjointSet = new DisjointSetImplementation(grid.length * grid[0].length);
        int totalRows = grid.length;
        int totalCols = grid[0].length;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    for (int[] direction : directions) {
                        int deltaX = i + direction[0];
                        int deltaY = j + direction[1];
                        if (isSafe(deltaX, deltaY, totalRows, totalCols, grid)) {
                            int uniqueId_U = getUniqueId(i, j, totalCols);
                            int uniqueId_V = getUniqueId(deltaX, deltaY, totalCols);
                            if (disjointSet.findUltimateParent(uniqueId_U) != disjointSet.findUltimateParent(uniqueId_V)) {
                                disjointSet.unionBySize(uniqueId_U, uniqueId_V);
                            }
                        }
                    }
                }
            }
        }
        int largestIsland = Integer.MIN_VALUE;
        for (int i = 0; i < totalRows; i++) {
            for (int j = 0; j < totalCols; j++) {
                if (grid[i][j] == 1) {
                    largestIsland = Math.max(largestIsland, disjointSet.getComponentSize(getUniqueId(i, j, totalCols)));
                } else if (grid[i][j] == 0) {
                    // Check from all 4 direction if any component is available
                    int size = 1; // If we flip this 0, hence taking size as 1
                    Set<Integer> visitedComponent = new HashSet<>();
                    for (int[] direction : directions) {
                        int deltaX = i + direction[0];
                        int deltaY = j + direction[1];
                        if (isSafe(deltaX, deltaY, totalRows, totalCols, grid)) {
                            int uniqueId_V = getUniqueId(deltaX, deltaY, totalCols);
                            int uniqueId_V_parent = disjointSet.findUltimateParent(uniqueId_V);
                            if (!visitedComponent.contains(uniqueId_V_parent)) {
                                visitedComponent.add(uniqueId_V_parent);
                                size += disjointSet.getComponentSize(uniqueId_V);
                            }
                        }
                    }
                    // After exploring all 4 directions
                    largestIsland = Math.max(largestIsland, size);
                }
            }
        }
        return largestIsland;
    }

    private static boolean isSafe(int row, int col, int totalRows, int totalCols, int[][] grid) {
        return row >= 0 && col >= 0 && row < totalRows && col < totalCols && grid[row][col] == 1;
    }

    private static int getUniqueId(int row, int col, int totalCols) {
        return (row * totalCols + col);
    }
}
