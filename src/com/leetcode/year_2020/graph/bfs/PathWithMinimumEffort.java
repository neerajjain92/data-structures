package com.leetcode.year_2020.graph.bfs;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/path-with-minimum-effort/description/
 */
public class PathWithMinimumEffort {

    public static void main(String[] args) {
        System.out.println(minimumEffortPath(new int[][]{
                {1, 2, 2},
                {3, 8, 2},
                {5, 3, 5}
        }));

        System.out.println(minimumEffortPath(new int[][]{
                {1, 2, 1, 1, 1},
                {1, 2, 1, 2, 1},
                {1, 2, 1, 2, 1},
                {1, 2, 1, 2, 1},
                {1, 1, 1, 2, 1}
        }));
    }

    static int[][] distance = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    static class Pair {
        int x;
        int y;
        int maxAbsoluteDifference;


        public Pair(int x, int y, int maxAbsoluteDifference) {
            this.x = x;
            this.y = y;
            this.maxAbsoluteDifference = maxAbsoluteDifference;
        }
    }

    public static int minimumEffortPath(int[][] heights) {
        PriorityQueue<Pair> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.maxAbsoluteDifference));
        minHeap.add(new Pair(0, 0, 0));

        int[][] minimumAbsoluteDifference = new int[heights.length][heights[0].length];
        for (int[] minDifferenceRow : minimumAbsoluteDifference) {
            Arrays.fill(minDifferenceRow, Integer.MAX_VALUE);
        }
        minimumAbsoluteDifference[0][0] = 0;
        int maxAbsoluteDifference = Integer.MAX_VALUE;
        while (!minHeap.isEmpty()) {
            Pair polled = minHeap.poll();
            if (polled.x == heights.length - 1 && polled.y == heights[0].length - 1) {
                return polled.maxAbsoluteDifference;
            }

            // 4 directions
            for (int[] delta : distance) {
                int newX = polled.x + delta[0];
                int newY = polled.y + delta[1];
                if (isSafe(newX, newY, heights)) {
                    int absoluteDifference = Math.abs(heights[polled.x][polled.y] - heights[newX][newY]);
                    if (minimumAbsoluteDifference[newX][newY] >
                            absoluteDifference) {
                        minimumAbsoluteDifference[newX][newY] = absoluteDifference;
                        minHeap.add(new Pair(newX, newY, Math.max(polled.maxAbsoluteDifference, absoluteDifference)));
                    }
                }
            }
        }
        return maxAbsoluteDifference == Integer.MAX_VALUE ? 0 : maxAbsoluteDifference;
    }

    private static boolean isSafe(int x, int y, int[][] grid) {
        return x >= 0 && y >= 0 && x < grid.length && y < grid[0].length;
    }
}
