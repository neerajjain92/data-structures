package com.datastructures.graph;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by jaine03 on 12/08/17.
 */
public class NumberOfIsland {
    public static int[] rowNumber = {-1, -1, 1, 0, 0, 1, 1, 1};
    public static int[] colNumber = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) {
        // 0 represent water, 1 represent field
//        int fieldMap[][] = new int[][]{
//                {1, 1, 0, 0, 0},
//                {0, 1, 0, 0, 1},
//                {1, 0, 0, 1, 1},
//                {0, 0, 0, 0, 0},
//                {1, 0, 1, 0, 1}
//        };

        int fieldMap[][] = new int[][]{
                {1, 0, 0, 1, 1, 0},
                {0, 1, 1, 0, 0, 0},
                {0, 1, 0, 0, 1, 0},
                {0, 0, 1, 0, 1, 0},
                {1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0}
        };

        Set<String> visited = new HashSet<>();
        int totalIsland = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (fieldMap[i][j] == 1 && !visited.contains(i + "," + j)) {
                    markConnectedComponentAsVisited(fieldMap, i, j, visited);
                    ++totalIsland;
                }
            }
        }
        System.out.println("Total Number of Island " + totalIsland);


        System.out.println(numIslands(new char[][]{
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        }));
    }

    public static Boolean isSafeToTraverse(int[][] fieldMap, int row, int col, Set<String> visited) {
        return row >= 0 && col >= 0 && row < 5 && col < 5 && fieldMap[row][col] == 1 && !visited.contains(row + "," + col);
    }

    public static void markConnectedComponentAsVisited(int[][] fieldMap, int row, int col, Set<String> visited) {
        visited.add(row + "," + col);

        // Traverse All Connected Components
        for (int i = 0; i < 8; i++) {
            if (isSafeToTraverse(fieldMap, row + rowNumber[i], col + colNumber[i], visited))
                markConnectedComponentAsVisited(fieldMap, row + rowNumber[i], col + colNumber[i], visited);
        }
    }

    public static int numIslands(char[][] grid) {
        int totalIsland = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    System.out.println(i + ":" + j);
                    travel(grid, i, j);
                    totalIsland++;
                }
            }
        }
        return totalIsland;
    }

    private static void travel(char[][] grid, int row, int col) {

        if (row < 0 || row >= grid.length || col < 0 || col >= grid[row].length || grid[row][col] == '0') return;
        grid[row][col] = '0';

        travel(grid, row - 1, col);
        travel(grid, row, col + 1);
        travel(grid, row + 1, col);
        travel(grid, row, col - 1);
    }
}
