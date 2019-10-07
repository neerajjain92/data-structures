package com.leetcode.problems.medium;

/**
 * @author neeraj on 15/09/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MinimumPathSum_64 {

    public static void main(String[] args) {
        minPathSum(new int[][]{
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        });
        minPathSum(new int[][]{
                {1, 2, 3},
                {4, 5, 6}
        });

        minPathSum(new int[][]{
                {5, 0, 1, 1, 2, 1, 0, 1, 3, 6, 3, 0, 7, 3, 3, 3, 1},
                {1, 4, 1, 8, 5, 5, 5, 6, 8, 7, 0, 4, 3, 9, 9, 6, 0},
                {2, 8, 3, 3, 1, 6, 1, 4, 9, 0, 9, 2, 3, 3, 3, 8, 4},
                {3, 5, 1, 9, 3, 0, 8, 3, 4, 3, 4, 6, 9, 6, 8, 9, 9},
                {3, 0, 7, 4, 6, 6, 4, 6, 8, 8, 9, 3, 8, 3, 9, 3, 4},
                {8, 8, 6, 8, 3, 3, 1, 7, 9, 3, 3, 9, 2, 4, 3, 5, 1},
                {7, 1, 0, 4, 7, 8, 4, 6, 4, 2, 1, 3, 7, 8, 3, 5, 4},
                {3, 0, 9, 6, 7, 8, 9, 2, 0, 4, 6, 3, 9, 7, 2, 0, 7},
                {8, 0, 8, 2, 6, 4, 4, 0, 9, 3, 8, 4, 0, 4, 7, 0, 4},
                {3, 7, 4, 5, 9, 4, 9, 7, 9, 8, 7, 4, 0, 4, 2, 0, 4},
                {5, 9, 0, 1, 9, 1, 5, 9, 5, 5, 3, 4, 6, 9, 8, 5, 6},
                {5, 7, 2, 4, 4, 4, 2, 1, 8, 4, 8, 0, 5, 4, 7, 4, 7},
                {9, 5, 8, 6, 4, 4, 3, 9, 8, 1, 1, 8, 7, 7, 3, 6, 9},
                {7, 2, 3, 1, 6, 3, 6, 6, 6, 3, 2, 3, 9, 9, 4, 4, 8}
        });

        minPathSumUsingDP(new int[][]{
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        });
        minPathSumUsingDP(new int[][]{
                {1, 2, 3},
                {4, 5, 6}
        });

        minPathSumUsingDP(new int[][]{
                {5, 0, 1, 1, 2, 1, 0, 1, 3, 6, 3, 0, 7, 3, 3, 3, 1},
                {1, 4, 1, 8, 5, 5, 5, 6, 8, 7, 0, 4, 3, 9, 9, 6, 0},
                {2, 8, 3, 3, 1, 6, 1, 4, 9, 0, 9, 2, 3, 3, 3, 8, 4},
                {3, 5, 1, 9, 3, 0, 8, 3, 4, 3, 4, 6, 9, 6, 8, 9, 9},
                {3, 0, 7, 4, 6, 6, 4, 6, 8, 8, 9, 3, 8, 3, 9, 3, 4},
                {8, 8, 6, 8, 3, 3, 1, 7, 9, 3, 3, 9, 2, 4, 3, 5, 1},
                {7, 1, 0, 4, 7, 8, 4, 6, 4, 2, 1, 3, 7, 8, 3, 5, 4},
                {3, 0, 9, 6, 7, 8, 9, 2, 0, 4, 6, 3, 9, 7, 2, 0, 7},
                {8, 0, 8, 2, 6, 4, 4, 0, 9, 3, 8, 4, 0, 4, 7, 0, 4},
                {3, 7, 4, 5, 9, 4, 9, 7, 9, 8, 7, 4, 0, 4, 2, 0, 4},
                {5, 9, 0, 1, 9, 1, 5, 9, 5, 5, 3, 4, 6, 9, 8, 5, 6},
                {5, 7, 2, 4, 4, 4, 2, 1, 8, 4, 8, 0, 5, 4, 7, 4, 7},
                {9, 5, 8, 6, 4, 4, 3, 9, 8, 1, 1, 8, 7, 7, 3, 6, 9},
                {7, 2, 3, 1, 6, 3, 6, 6, 6, 3, 2, 3, 9, 9, 4, 4, 8}
        });
    }

    private static int minPathSumUsingDP(int[][] grid) {
        int[][] minPathSum = new int[grid.length][grid[0].length];

        // Since we can only move in either right or down
        // Let's pre-fill the 1st row and 1st column, as there is no other way apart from moving right and down
        // to cover up the 1st row and column
        minPathSum[0][0] = grid[0][0];

        for(int j=1;j<minPathSum[0].length;j++) {
            minPathSum[0][j] = minPathSum[0][j-1] + grid[0][j];
        }


        for(int i=1;i<minPathSum.length;i++) {
            minPathSum[i][0] = minPathSum[i-1][0] + grid[i][0];
        }

        for (int i = 1; i < minPathSum.length; i++) {
            for (int j = 1; j < minPathSum[i].length; j++) {
                minPathSum[i][j] = grid[i][j] + Math.min(minPathSum[i - 1][j], minPathSum[i][j - 1]);
            }
        }
        System.out.println(minPathSum[grid.length - 1][grid[0].length - 1]);
        return minPathSum[grid.length - 1][grid[0].length - 1];
    }

    static int minimumDistance = Integer.MAX_VALUE;

    public static int minPathSum(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        minimumDistance = Integer.MAX_VALUE;
        doDFS(grid, 0, 0, grid[0][0], visited);
        System.out.println(minimumDistance);
        return minimumDistance;
    }

    private static void doDFS(int[][] grid, int row, int col, int distanceFromOrigin, boolean[][] visited) {
        visited[row][col] = true;
        if (row == grid.length - 1 && col == grid[0].length - 1) {
            minimumDistance = Math.min(distanceFromOrigin, minimumDistance);
        }

        if (isSafe(grid, row, col + 1, visited)) {
            doDFS(grid, row, col + 1, distanceFromOrigin + grid[row][col + 1], visited);
        }
        if (isSafe(grid, row + 1, col, visited)) {
            doDFS(grid, row + 1, col, distanceFromOrigin + grid[row + 1][col], visited);
        }

        visited[row][col] = false;
    }

    private static boolean isSafe(int[][] grid, int row, int col, boolean[][] visited) {
        return row >= 0 && col >= 0 && row < grid.length && col < grid[0].length && visited[row][col] == false;
    }

}
