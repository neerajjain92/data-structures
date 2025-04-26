package com.leetcode.year_2020.heap.coding_with_mik;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/trapping-rain-water-ii/description/
 * <p>
 * 407. Trapping Rain Water II Hard
 * Couldn't have solved without this person's explanation
 * https://www.youtube.com/watch?v=TzsbIDtTlsQ&list=PLpIkg8OmuX-IkxvvfOeZp-Ot0UWHMGAT-&index=25&ab_channel=codestorywithMIK
 */
public class TrappingRainWater_2 {

    public static void main(String[] args) {
        TrappingRainWater_2 obj = new TrappingRainWater_2();
        System.out.println(obj.trapRainWater(new int[][]{
                {1, 4, 3, 1, 3, 2},
                {3, 2, 1, 3, 2, 4},
                {2, 3, 3, 2, 3, 1}
        }));

        System.out.println(obj.trapRainWater(new int[][]{
                {3, 3, 3, 3, 3},
                {3, 2, 2, 2, 3},
                {3, 2, 1, 2, 3},
                {3, 2, 2, 2, 3},
                {3, 3, 3, 3, 3}
        }));
    }


    public int trapRainWater(int[][] heightMap) {
        // Let's start with boundry
        boolean[][] visited = new boolean[heightMap.length][heightMap[0].length];
        PriorityQueue<TrappingRainWaterTuple> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.height));
        int totalRows = heightMap.length;
        int totalCols = heightMap[0].length;
        int totalWaterCollected = 0;

        // Traverse Boundry
        // 1st Row and last Row together, col will increase
        int lastRow = heightMap.length - 1;
        for (int j = 0; j < totalCols; j++) {
            minHeap.add(new TrappingRainWaterTuple(heightMap[0][j], 0, j));
            minHeap.add(new TrappingRainWaterTuple(heightMap[lastRow][j], lastRow, j));
            visited[0][j] = true;
            visited[lastRow][j] = true;
        }


        // 1st Column and last column together, so row will increase
        int lastColumn = totalCols - 1;
        for (int i = 1; i < totalRows - 1; i++) {
            minHeap.add(new TrappingRainWaterTuple(heightMap[i][0], i, 0));
            minHeap.add(new TrappingRainWaterTuple(heightMap[i][lastColumn], i, lastColumn));
            visited[i][0] = true;
            visited[i][lastColumn] = true;
        }


        // Now simply poll from minHeap
        while (!minHeap.isEmpty()) {
            TrappingRainWaterTuple trappingRainWaterTuple = minHeap.poll();

            // Check all 4 boundry
            int row = trappingRainWaterTuple.row, col = trappingRainWaterTuple.col;
            // Top
            if (row - 1 >= 0 && !visited[row - 1][col]) {
                visited[row - 1][col] = true;
                int waterToBeAdded = Math.max(0, trappingRainWaterTuple.height - heightMap[row - 1][col]);
                int newHeight = waterToBeAdded == 0 ? heightMap[row - 1][col] : heightMap[row - 1][col] + waterToBeAdded;
                totalWaterCollected += waterToBeAdded;
                minHeap.add(new TrappingRainWaterTuple(newHeight, row - 1, col));
            }

            // Right
            if (col + 1 < totalCols && !visited[row][col + 1]) {
                visited[row][col + 1] = true;
                int waterToBeAdded = Math.max(0, trappingRainWaterTuple.height - heightMap[row][col + 1]);
                int newHeight = waterToBeAdded == 0 ? heightMap[row][col + 1] : heightMap[row][col + 1] + waterToBeAdded;
                totalWaterCollected += waterToBeAdded;
                minHeap.add(new TrappingRainWaterTuple(newHeight, row, col + 1));
            }

            // Bottom
            if (row + 1 < totalRows && !visited[row + 1][col]) {
                visited[row + 1][col] = true;
                int waterToBeAdded = Math.max(0, trappingRainWaterTuple.height - heightMap[row + 1][col]);
                int newHeight = waterToBeAdded == 0 ? heightMap[row + 1][col] : heightMap[row + 1][col] + waterToBeAdded;
                totalWaterCollected += waterToBeAdded;
                minHeap.add(new TrappingRainWaterTuple(newHeight, row + 1, col));
            }

            // left
            if (col - 1 >= 0 && !visited[row][col - 1]) {
                visited[row][col - 1] = true;
                int waterToBeAdded = Math.max(0, trappingRainWaterTuple.height - heightMap[row][col - 1]);
                int newHeight = waterToBeAdded == 0 ? heightMap[row][col - 1] : heightMap[row][col - 1] + waterToBeAdded;
                totalWaterCollected += waterToBeAdded;
                minHeap.add(new TrappingRainWaterTuple(newHeight, row, col - 1));
            }
        }
        return totalWaterCollected;
    }

    static class TrappingRainWaterTuple {
        int height, row, col;

        public TrappingRainWaterTuple(int height, int row, int col) {
            this.height = height;
            this.row = row;
            this.col = col;
        }
    }


}
