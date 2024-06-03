package com.leetcode.year_2020.graph.disjoint_set_union_find;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/number-of-islands-ii/#/description
 * https://tenderleo.gitbooks.io/leetcode-solutions-/GoogleMedium/305.html
 * <p>
 * Given m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]. Initially, the 2d grid grid is filled with water.
 * (Assume 0 represents water and 1 represents land).
 * 0 0 0
 * 0 0 0
 * 0 0 0
 *
 * @author neeraj on 29/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class NumberOfIslands_2 {

    public static void main(String[] args) {
        System.out.println(numIsland2Version2(3, 3, new int[][]{
                {0, 0},
                {0, 1},
                {1, 0},
                {1, 2},
                {2, 1}
        }));

        System.out.println(numIsland2Version2(4, 5, new int[][]{{1, 1}, {0, 1}, {3, 3}, {3, 4}}));
    }

    private static int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    /**
     * Implemented in June 2024
     * Awesome Explanation
     * https://www.youtube.com/watch?v=Rn6B-Q4SNyA&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=51
     * Let's solve this with Disjoint Set
     *
     * @param m         Total Number of rows
     * @param n         Total Number of cols
     * @param positions OnlineQueries (basically asking to get answer on each entry of positions
     */
    private static List<Integer> numIsland2Version2(int m, int n, int[][] positions) {
        /**
         * Most important things
         *  Given the structure as this m=4(totalRows) and n=3(TotalCols)
         *  ------------------------
         * | (0,0) | (0,1) | (0,2) |
         * | (1,0) | (1,1) | (1,2) |
         * | (2,0) | (2,1) | (2,2) |   ================> if i have to represent any item on this row in Disjoint set
         * | (3,0) | (3,1) | (3,2) |
         * -------------------------
         *
         * We need a way to represent each individual cell as a single entry in the disjoint set
         * So imagine standing on row presented above (comment on row 2)[0th based indexing]
         * Can i say before that in the previous row i have explored total 6 cells already
         * How ???? Any item on row 2 === [All Cells in previous rows] + any extra cell in this row(which is nothing but total cols explored)
         *
         * So if i have to represent (2,1) can i say [2 * totalCols + 1] = [2 * 3 + 1] == 7 (which is 0 based index)
         *
         *  ------------------------
         *  |  0   |   1   |   2   |
         *  |  3   |   4   |   5   |
         *  |  6   |   7   |   8   |
         *  |  9   |  10   |  11   |
         * -------------------------
         *
         * Now see how easy it became, now it's trivial to know from DisjointSet
         * which 2 components are connected
         */
        int[][] visited = new int[m][n];
        DisjointSetImplementation disjointSet = new DisjointSetImplementation(m * n);
        List<Integer> result = new ArrayList<>();
        int totalIsland = 0;
        for (int[] position : positions) {
            if (visited[position[0]][position[1]] == 1) continue;
            visited[position[0]][position[1]] = 1; // First visit
            totalIsland++; // Increment the island;

            // Check in All 4 directions
            for (int[] direction : directions) {
                int deltaX = position[0] + direction[0];
                int deltaY = position[1] + direction[1];
                if (isSafe(deltaX, deltaY, m, n, visited)) {
                    int uniqueId_U = getUniqueId(position[0], position[1], n);
                    int uniqueId_V = getUniqueId(deltaX, deltaY, n);
                    if (disjointSet.findUltimateParent(uniqueId_U) != disjointSet.findUltimateParent(uniqueId_V)) {
                        disjointSet.unionBySize(uniqueId_U, uniqueId_V);
                        totalIsland--;
                    }
                }
            }
            result.add(totalIsland);
        }
        return result;
    }

    private static boolean isSafe(int row, int col, int totalRows, int totalCols, int[][] visited) {
        return row >= 0 && col >= 0 && row < totalRows && col < totalCols && visited[row][col] == 1;
    }

    private static int getUniqueId(int row, int col, int totalCols) {
        return (row * totalCols + col);
    }

    public static List<Integer> numIslands2(int m, int n, int[][] positions) {
        /**
         * Initially grid is completely filled with water i.e 0
         * we need to add land as prescribed in positions and then just tell number of islands after
         * every land construction from positions.
         *
         * We can use DFS with every operation but the time complexity will become O(operation * (|V| + |E|))
         * So we will leverage Disjoint Set's Union by rank and find operations to do it in O(kmn)
         */

        // We can turn a 2d matrix in 1d matrix of size [m*n]
        // and we every position in 2d matrix will be denoted in 1d matrix by
        // (Col * Row_i + Col_j).
        // So 2d matrix like this
        // (0,0) , (0,1), (0,2)
        // (1,0) , (1,1), (1,2)
        // (2,0) , (2,1), (2,2)
        // Here m = 3 and n = 3
        // n*(0)+0, n*(0)+1 ,n*(0)+2,n*(1)+0,n*(1)+1,n*(1)+2,n*(2)+0,n*(2)+1,n*(2)+2,
        // 0,1,2,3,4,5,6,7,8
        // This is how you represent 2d matrix in 1d.
        int[] lands = new int[m * n];

        Arrays.fill(lands, -1); // Initially nothing is present in lands array.

        // Also we know land is surrounded by water in 4 directions(Top, Right, Bottom, Left>)
        // So we will try for each land in all 4 directions and see if this land can become part of some existing
        // island or will it make it's own island
        int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; //Top, Right, Bottom, Left

        int islandCount = 0;
        List<Integer> result = new ArrayList<>();

        for (int[] position : positions) {
            int land_position_x = position[0];
            int land_position_y = position[1];

            islandCount++;
            lands[n * land_position_x + land_position_y] = n * land_position_x + land_position_y;

            // Now lets check in all 4 directions
            for (int k = 0; k < directions.length; k++) {
                int new_land_position_x = land_position_x + directions[k][0];
                int new_land_position_y = land_position_y + directions[k][1];

                // if we have a safe check to explore kth direction
                if (new_land_position_x >= 0 && new_land_position_x < m &&
                        new_land_position_y >= 0 && new_land_position_y < n
                        && lands[n * new_land_position_x + new_land_position_y] != -1) { // Why != -1 because then only we know that the other element has already created it's island
                    // and new_land_position can club into it.

                    // this means we can club with some other existing island
                    islandCount--;
                    unionClubIsland(lands, lands[n * land_position_x + land_position_y], lands[n * new_land_position_x + new_land_position_y]);
                }
            }
            result.add(islandCount);
        }
        return result;
    }

    private static void unionClubIsland(int[] lands, int existingIsland, int newLandToBeClubbed) {
        for (int i = 0; i < lands.length; i++) {
            if (lands[i] == newLandToBeClubbed) {
                lands[i] = existingIsland;
            }
        }
    }

}
