package com.leetcode.year_2020.MayChallenge.week3;

import com.leetcode.year_2020.MaximalSquare;

/**
 * https://leetcode.com/problems/count-square-submatrices-with-all-ones/
 *
 * @author neeraj on 21/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class CountSquareSubmatriceswithAllOnes {

    public static void main(String[] args) {
        System.out.println(countSquares(new int[][]{
                {0, 1, 1, 1},
                {1, 1, 1, 1},
                {0, 1, 1, 1}
        }));
        System.out.println(countSquares(new int[][]{
                {1, 0, 1},
                {1, 1, 0},
                {1, 1, 0}
        }));

        System.out.println(countSquares(new int[][]{
                {1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1}
        }));
    }

    public static int countSquares(int[][] matrix) {
        if (matrix.length == 0) return 0;
        /**
         * This problem is exactly similar to {@link MaximalSquare}. there we had to find
         * maximum square we can make here we need the count of all squares we can make
         * via just 1's
         *
         * Input: matrix =
         * [
         *   [0,1,1,1],
         *   [1,1,1,1],
         *   [0,1,1,1]
         * ]
         * Output: 15
         * Explanation:
         * There are 10 squares of side 1.
         * There are 4 squares of side 2.
         * There is  1 square of side 3.
         * Total number of squares = 10 + 4 + 1 = 15.
         *
         *
         * dp[i][j] : Represents maximumSize of square with Matrix[i][j] as the bottom-right corner of a square.
         * dp[i][j] : Also represents how many squares we can built with Matrix[i][j] as the bottom-right corner.
         *
         * So DP for our above problem is :
         *
         * 0  1	 1	1
         * 1  1	 2	2
         * 0  1	 2	3
         *
         * Let's focus on 2nd Row and 3rd Column: (1 Based Index)... you can clearly see it says 2.
         * what does that mean...that the max square which we can make at that index(as the bottom-right corner) will be of size 2.
         * Also it means there can be total 2 squares which can be built using this cell as the bottom-right corner.
         *
         * [ 1   1]
         * [ 1   2]  ----> Square of size 2 .
         * Also [1] [1]
         *      [1] [1] ----> you can notice same cell with Square of size 1.
         * but if a square of 2 is being built for sure we can make a square of currVal-1 keeping this index as the bottom
         *
         * Take an example of the cell with value 3.
         * we can make      or                                   or
         * [1] [1] [1]         |    [1]  [1]  [1]                  |   [ 1  1   1
         * [1] [1] [1]         |    [1]  [1    1 ]                 |     1   1  1
         * [1] [1] [1]  1*1    |    [1]  [1    1 ]  2*2 Matrix     |     1   1  1 ] ---> 3 * 3 Matrix
         *
         * Hence at the end we sum up all dp[i][j] to calculate all sub-squares.
         */
        int[][] dp = new int[matrix.length][matrix[0].length];
        int totalSquaresSubMatrices = 0;

        // Since we know if we have just 1d size square so either it will be 1 or 0.
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 1) {
                    if (i == 0 || j == 0) {
                        dp[i][j] = matrix[i][j];
                    } else {
                        // Now let's talk about rest of the rows.
                        // We know for a square to be made we need equal sides on all side.
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j],
                                dp[i][j - 1]), dp[i - 1][j - 1]) + 1; // Comparing all 3 sides left, top, left-diagonal and taking the min.
                    }
                    totalSquaresSubMatrices += dp[i][j];
                }

            }
        }
        return totalSquaresSubMatrices;
    }

}
