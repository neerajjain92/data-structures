package com.leetcode.year_2020.backtracking.codeStoryWithMik;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/unique-paths-iii/description/
 * <p>
 * 980. Unique Paths III
 * Solved
 * Hard
 * Topics
 * Companies
 * You are given an m x n integer array grid where grid[i][j] could be:
 * <p>
 * 1 representing the starting square. There is exactly one starting square.
 * 2 representing the ending square. There is exactly one ending square.
 * 0 representing empty squares we can walk over.
 * -1 representing obstacles that we cannot walk over.
 * Return the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle square exactly once.
 */
public class UniquePaths3 {

    static int total = 0;

    public static void main(String[] args) {
        UniquePaths3 uniquePaths3 = new UniquePaths3();
        System.out.println(uniquePaths3.uniquePathsIII(new int[][]{
                {1, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 2, -1}
        }));
    }

    public int uniquePathsIII(int[][] grid) {
        total = 0;
        // Find Obstacles, and starting point in first shot
        int[] startPoint = new int[2];
        int totalObstacles = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    startPoint = new int[]{i, j};
                } else if (grid[i][j] == -1) {
                    totalObstacles++;
                }
            }
        }
        int totalBlocksToExplore = grid.length * grid[0].length - totalObstacles; // -1 for the ending point

        // we will use -2 value to mark grid visited
        // Why (-1) in totalBlocksToExplore because we are starting from
        List<int[]> paths = new ArrayList<>();
        uniquePaths(startPoint[0], startPoint[1], grid, totalBlocksToExplore - 1, paths);
        return total;
    }

    private void uniquePaths(int i, int j, int[][] grid, int blocksToExplore, List<int[]> paths) {
        // Skip out of bounds or either visited or obstacle
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == -2 || grid[i][j] == -1) {
            return;
        }

        if (grid[i][j] == 2) {
            if (blocksToExplore == 0) {
                total++;
            }
            return;
        }
        paths.add(new int[]{i, j});
        int prev = grid[i][j];
        grid[i][j] = -2;
        // Top, right, bottom, left
        uniquePaths(i - 1, j, grid, blocksToExplore - 1, paths);
        uniquePaths(i, j + 1, grid, blocksToExplore - 1, paths);
        uniquePaths(i + 1, j, grid, blocksToExplore - 1, paths);
        uniquePaths(i, j - 1, grid, blocksToExplore - 1, paths);
        grid[i][j] = prev;
        paths.remove(paths.size() - 1);
    }

}
