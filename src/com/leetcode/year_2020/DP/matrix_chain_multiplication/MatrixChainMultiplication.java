package com.leetcode.year_2020.DP.matrix_chain_multiplication;

import java.util.Arrays;

/**
 * @author neeraj on 09/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MatrixChainMultiplication {

    public static void main(String[] args) {
        System.out.println(findMinimumMultiplicationOperation(new int[]{40, 20, 30, 10, 30}));
        System.out.println(findMinimumMultiplicationOperation(new int[]{10, 20, 30}));
        System.out.println(findMinimumMultiplicationOperation(new int[]{10, 20, 30, 40, 30}));
    }

    static int t[][]; // Memorization

    public static int findMinimumMultiplicationOperation(int[] dimensions) {
        /**
         * Assume we have dimensions as [40, 20, 30, 10, 30]  with length as n
         * So we have n-1 matrix .
         * A = 40*30;
         * B = 20*30;
         * C = 30*10;
         * D = 10*30
         *
         * So how should we multiply them to achieve minimum multiplication operation.
         *
         * (AB)(CD), (A)(BCD), (ABC)(D).....
         *
         * The Optimal Answer for this problem is.
         * (A(BC))D --> 20*30*10 + 40*20*10 + 40*10*30
         *
         * [40  20  30  10  30]
         *  0   1   2   3   4
         *      i            j
         *  Now why i starts from 1 and not from 0.
         *  since the dimension is a pair (m * n).
         *  if we start i == 0, we won't be having m in m*n
         *
         *  Now k will run from i to j-1, why till j-1, since we k act as a cut to our matrix,
         *  and we put cut in  matrix to multiply, Now if i keep k==j... we can do solve(arr, i, k), but not solve(arr, k+1, j).
         */
        t = new int[dimensions.length + 1][dimensions.length + 1];
        for (int[] row : t) {
            Arrays.fill(row, -1);
        }
        return solve(dimensions, 1, dimensions.length - 1);
    }

    private static int solve(int[] dimensions, int i, int j) {
        if (i >= j) return 0; // This case represent when we have matrix of length 1, if that's the case
        // we can never multiply since we need at least 2 to do multiplication.

        if (t[i][j] > -1) return t[i][j];

        // K will run from i till j(exclusive), and try each section to multiply from i to k, and k+1 to j-1
        Integer MIN_OPERATIONS = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            int operationsInLeft = solve(dimensions, i, k);
            int operationsInRight = solve(dimensions, k + 1, j);
            int operationsAtThisPoint = dimensions[i - 1] * dimensions[k] * dimensions[j];

            int operationsIfWeChooseThisK = operationsInLeft + operationsAtThisPoint + operationsInRight;
            MIN_OPERATIONS = Math.min(MIN_OPERATIONS, operationsIfWeChooseThisK);
        }
        return t[i][j] = MIN_OPERATIONS;
    }
}
