package com.leetcode.year_2020.DP.matrix_chain_multiplication;

import com.util.LogUtil;

import java.util.Arrays;

/**
 * @author neeraj on 09/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MatrixChainMultiplication {

    public static void main(String[] args) {
        System.out.println(findMinimumMultiplicationOperation(new int[]{1, 2, 3, 4}));
        System.out.println(findMinimumMultiplicationOperation(new int[]{40, 20, 30, 10, 30}));
        System.out.println(findMinimumMultiplicationOperation(new int[]{10, 20, 30}));
        System.out.println(findMinimumMultiplicationOperation(new int[]{10, 20, 30, 40, 30}));
    }

    static int t[][]; // Memorization
    static int brackets[][]; // TO put the optimal Brackets.

    public static void printBrackets(int[][] brackets, int i, int j) {
        if (i == j) {
            System.out.print("A" + i);
        } else {
            System.out.print("(");
            printBrackets(brackets, i, brackets[i][j]);
            printBrackets(brackets, brackets[i][j] + 1, j);
            System.out.print(")");
        }
    }

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
        t = new int[dimensions.length][dimensions.length];
        brackets = new int[t.length][t[0].length];
        for (int[] row : t) {
            Arrays.fill(row, -1);
        }
        int minOperation = solveMatrixChain(dimensions, 1, dimensions.length - 1);
        printBrackets(brackets, 1, brackets.length - 1);
        LogUtil.newLine();
        return minOperation;
    }

    private static int solveMatrixChain(int[] dimensions, int i, int j) {
        if (i >= j) {
            t[i][j] = 0;
            return 0; // This case represent when we have matrix of length 1, if that's the case
        }
        // we can never multiply since we need at least 2 to do multiplication.

        if (t[i][j] > -1) return t[i][j];

        // K will run from i till j(exclusive), and try each section to multiply from i to k, and k+1 to j-1
        Integer MIN_OPERATIONS = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            int operationsInLeft = solveMatrixChain(dimensions, i, k);
            int operationsInRight = solveMatrixChain(dimensions, k + 1, j);
            int operationsAtThisPoint = dimensions[i - 1] * dimensions[k] * dimensions[j];

            int operationsIfWeChooseThisK = operationsInLeft + operationsAtThisPoint + operationsInRight;
            if (operationsAtThisPoint < MIN_OPERATIONS) {
                MIN_OPERATIONS = operationsIfWeChooseThisK;

                // Update the brackets wherever we have put optimal cuts.
                brackets[i][j] = k;
            }
        }
        return t[i][j] = MIN_OPERATIONS;
    }
}
