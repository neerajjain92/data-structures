package com.leetcode.year_2020.DP.kadanes;

import com.geeksforgeeks.dynamicProgramming.MaximumSumRectangle;
import com.util.LogUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/max-sum-of-rectangle-no-larger-than-k/
 *
 * @author neeraj on 19/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MaxSumOfRectangleNoLargerThanK {

    public static void main(String[] args) {
        int[][] inputs = new int[][]{
                {1, 2, -1, -4, -20},
                {-8, -3, 4, 2, 1},
                {3, 8, 10, 1, 3},
                {-4, -1, 1, 7, -6}
        };
        maxSumSubmatrix(inputs, 2);

        inputs = new int[][]{
                {1, 0, 1},
                {0, -2, 3}
        };
        maxSumSubmatrix(inputs, 2);

        inputs = new int[][]{{5, -4, -3, 4},
                {-3, -4, 4, 5},
                {5, 1, 5, -4}};
        maxSumSubmatrix(inputs, 10);

        inputs = new int[][]{{5, -4, -3, 4},
                {-3, -4, 4, 5},
                {5, 1, 5, -4}};
        maxSumSubmatrix(inputs, 8);
    }

    enum Keywords {
        MAXIMUM_SUM, START, END
    }

    public static int maxSumSubmatrix(int[][] matrix, int k) {
        /**
         * This problem is similar to {@link MaximumSumRectangle}, with a twist of max sum not greater than k.
         * okay so i need to iterate through all the columns, by keeping a left and right limit and use kadane's
         * to find the sum of a sub-matrix or a rectangle.
         */
        int[] runningSumOfRectangle;
        Map<Keywords, Integer> kadaneOutput = new HashMap<>();
        int MAXIMUM_SUM_RECTANGLE = Integer.MIN_VALUE;
        int rectangleTop = 0, rectangleBottom = 0, rectangleLeft = 0, rectangleRight = 0;
        for (int L = 0; L < matrix[0].length; L++) {
            runningSumOfRectangle = new int[matrix.length]; // This sum goes from top row to the bottom row,
            // and within left and right bound.

            for (int R = L; R < matrix[0].length; R++) {

                // Now let's calculate the runningSumOfRectangle from left to right bound.
                for (int row = 0; row < matrix.length; row++) {
                    runningSumOfRectangle[row] += matrix[row][R];
                }

                // Now find the maximum sum within this rectangle
                kadaneOutput = kadanes(runningSumOfRectangle, k);

                if (MAXIMUM_SUM_RECTANGLE < kadaneOutput.get(Keywords.MAXIMUM_SUM) && kadaneOutput.get(Keywords.MAXIMUM_SUM) <= k) {
                    MAXIMUM_SUM_RECTANGLE = kadaneOutput.get(Keywords.MAXIMUM_SUM);
                    rectangleLeft = L;
                    rectangleRight = R;
                    rectangleTop = kadaneOutput.get(Keywords.START);
                    rectangleBottom = kadaneOutput.get(Keywords.END);
                }
            }
        }

        LogUtil.logIt("Maximum Rectangle SUM is " + MAXIMUM_SUM_RECTANGLE);
        for (int i = rectangleTop; i <= rectangleBottom; i++) {
            for (int j = rectangleLeft; j <= rectangleRight; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
        return MAXIMUM_SUM_RECTANGLE;
    }

    private static Map<Keywords, Integer> kadanes(int[] runningSumOfRectangle, int maxAllowedSum) {
        int start = 0, end = 0, tempStart = 0;
        int MAX_TILL_NOW = Integer.MIN_VALUE, MAX_ENDING_HERE = 0;
        Map<Keywords, Integer> kadaneOutput = new HashMap<>();
        for (int i = 0; i < runningSumOfRectangle.length; i++) {
            MAX_ENDING_HERE += runningSumOfRectangle[i];

            if (MAX_TILL_NOW < MAX_ENDING_HERE && MAX_ENDING_HERE <= maxAllowedSum) {
                MAX_TILL_NOW = MAX_ENDING_HERE;
                end = i;
                start = tempStart;
            }
            if (MAX_ENDING_HERE < 0) {
                MAX_ENDING_HERE = 0;
                tempStart = i + 1;
            }
        }
        kadaneOutput.put(Keywords.MAXIMUM_SUM, MAX_TILL_NOW);
        kadaneOutput.put(Keywords.START, start);
        kadaneOutput.put(Keywords.END, end);
        return kadaneOutput;
    }
}
