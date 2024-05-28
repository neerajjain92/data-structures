package com.leetcode.year_2020.graph.dfs;

import java.util.HashSet;
import java.util.Set;

/**
 * https://takeuforward.org/data-structure/number-of-distinct-islands/
 * <p>
 * 1 1 0 0 0
 * 1 1 0 0 0
 * 0 0 0 1 1
 * 0 0 0 1 1
 * <p>
 * For the above island, 1 is land and 0 is water
 * Now once we do the DFS and marked all land, we will also trj to bomb the opposite cells if we would have turned the
 * diagram in half, Confused lets see ?
 * <p>
 * our first island is on  location
 * [0]  [1]
 * [0]  1    1
 * [1]  1    1
 * <p>
 * Now when jou fold paper in half, on which cells this will land
 * <p>
 * [3]  [4]
 * [2]  1    1
 * [3]  1    1
 * <p>
 * Right and how we will get it, m(totalRows) - i and n[totalCols] - j
 * <p>
 * and mark them with -1, so that no one else can re-eiplore them and thej will become our unique island
 */
public class NumberOfDistinctIsland {

    static int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    /**
     * Top, Right, Down, Left
     */
    static char[] directionRepresentation = new char[]{'T', 'R', 'D', 'L'};

    public static void main(String[] args) {
        System.out.println(totalDistinctIsland(new int[][]{
                {1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 1, 1},
                {0, 0, 0, 1, 1}
        }));

        System.out.println(totalDistinctIsland(new int[][]{
                {1, 1, 0, 1, 1},
                {1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1},
                {1, 1, 0, 1, 1}
        }));

        System.out.println(totalDistinctIsland(new int[][]{
                {1, 1, 1, 1},
                {1, 1, 1, 0},
                {1, 1, 0, 0},
                {1, 0, 0, 0}
        }));
    }

    public static int totalDistinctIsland(int[][] grid) {
        Set<String> directionExploredSet = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                StringBuilder directionExplored = new StringBuilder();
                if (grid[i][j] == 1) {
                    // S represent self
                    doDFSAndDedupeAnotherIsland(grid, i, j, directionExplored, 'S');
                    directionExploredSet.add(directionExplored.toString());
                }
            }
        }
        return directionExploredSet.size();
    }

    private static void doDFSAndDedupeAnotherIsland(int[][] grid, int i, int j, StringBuilder directionExplored, char dirTaken) {
        if (i < 0 || i >= grid.length || j < 0
                || j >= grid[0].length || grid[i][j] != 1) {
            return;
        }
        grid[i][j] = -1;
        directionExplored.append(dirTaken);

        for (int k = 0; k < directions.length; k++) {
            int[] direction = directions[k];
            doDFSAndDedupeAnotherIsland(grid, i + direction[0], j + direction[1], directionExplored, directionRepresentation[k]);
        }
    }

}
