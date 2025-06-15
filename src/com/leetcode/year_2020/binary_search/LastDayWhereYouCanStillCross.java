package com.leetcode.year_2020.binary_search;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class LastDayWhereYouCanStillCross {

    int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) {
        LastDayWhereYouCanStillCross cross = new LastDayWhereYouCanStillCross();
        System.out.println(cross.latestDayToCross(2, 2, new int[][]{{1, 1}, {1, 2}, {2, 1}, {2, 2}}));
        System.out.println(cross.latestDayToCross(3, 3, new int[][]{{1, 1}, {1, 2}, {2, 1}, {2, 2}}));
    }

    public int latestDayToCross(int row, int col, int[][] cells) {
        int low = 1; // First day
        int high = cells.length; // total days
        int answer = 0;
        int[][] grid = new int[row][col];
        for(int[] gridRow : grid) {
            Arrays.fill(gridRow, cells.length+10);
        }
        for (int i = 0; i < cells.length; i++) {
            int[] cell = cells[i];
            grid[cell[0] - 1][cell[1] - 1] = i + 1; // we are not keeping land or water in the grid but instead the day this becomes filled with water
        }
        while (low <= high) {
            int mid_day = low + (high - low) / 2;
            if (canTravelFromTopToBotton(mid_day, row, col, grid)) {
                answer = mid_day;
                low = mid_day + 1;
            } else {
                high = mid_day - 1;
            }
        }
        return answer;
    }

    private boolean canTravelFromTopToBotton(int mid_day, int row, int col, int[][] grid) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[row][col];
        for (int i = 0; i < grid[0].length; i++) {
            if (grid[0][i] > mid_day) {
                queue.add(new int[]{0, i});
                visited[0][i] = true;
            }
        }
        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            visited[pos[0]][pos[1]] = true;
            if (pos[0] == row - 1) {
                return true;
            }
            for (int[] dir : directions) {
                if (isSafe(pos[0] + dir[0], pos[1] + dir[1], grid, visited, mid_day)) {
                    int newRow = pos[0] + dir[0];
                    int newCol = pos[1] + dir[1];
                    queue.add(new int[]{newRow, newCol});
                }
            }
        }
        return false;
    }

    private boolean isSafe(int row, int col, int[][] grid, boolean[][] visited, int mid_day) {
        return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length && !visited[row][col] && grid[row][col] > mid_day;
    }
}
