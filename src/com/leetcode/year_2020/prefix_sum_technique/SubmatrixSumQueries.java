package com.leetcode.year_2020.prefix_sum_technique;

import com.util.LogUtil;

/**
 * @author neeraj on 25/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class SubmatrixSumQueries {

    public static void main(String[] args) {

        int mat[][] = {{1, 2, 3, 4, 6},
                {5, 3, 8, 1, 2},
                {4, 6, 7, 5, 5},
                {2, 4, 8, 9, 4}};
        int M = mat.length, N = mat[0].length;
        int aux[][] = preProcess(mat);

        System.out.println(answerQuery(0, 0, 1, 1, aux));
        System.out.println(answerQuery(2, 2, 3, 4, aux));
        System.out.println(answerQuery(1, 2, 3, 3, aux));
    }

    public static int answerQuery(int tli, int tlj, int rbi, int rbj, int[][] aux) {
        // Now since we have all prefix sum of cols and then on top rows as well.

        /**
         * So let's Remove Unnecessary row  sum from previous rows
         * nd also remove Unnecessary col sum from previous cols.
         *
         * aux[rbi][rbj] - aux[tli-1][rbj](Extra Row Sum) - aux[tlj-1][rbi](extra colsum)
         * + aux[tli-1][tli-1] (Why this addition).
         *
         * Since this column is getting hit twice once in rows sum and another in cols sum
         * hence we have to discount him once.
         */
        int res = aux[rbi][rbj];

        if (tli > 0) {
            res -= aux[tli - 1][rbj];
        }
        if (tlj > 0) {
            res -= aux[tlj - 1][rbi];
        }
        if (tli > 0 && tlj > 0) {
            res += aux[tli - 1][tlj - 1];
        }
        return res;
    }

    public static int[][] preProcess(int mat[][]) {
        /**
         * So this problem is a little twist of prefix sum
         * since we have a 2d matrix so we prefix sum of both rows and cols
         */
        int[][] prefixSumMatrix = new int[mat.length][mat[0].length];

        int count = 0;
        // PrePopulate 1st Row
        for (int[] row : mat) {
            for (int i : row) {
                prefixSumMatrix[0][count++] = i;
            }
            break;
        }
        // Run through all cols
        for (int i = 1; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                prefixSumMatrix[i][j] = mat[i][j] + prefixSumMatrix[i - 1][j];
            }
        }
        LogUtil.logIt("Col Prefix Sum.....");
        LogUtil.printMultiDimensionArray(prefixSumMatrix);

        // Now Row Prefix Sum
        for (int i = 0; i < mat.length; i++) {
            for (int j = 1; j < mat[i].length; j++) {
                prefixSumMatrix[i][j] = prefixSumMatrix[i][j - 1] + prefixSumMatrix[i][j];
            }
        }

        LogUtil.logIt("Col Prefix Sum.....");
        LogUtil.printMultiDimensionArray(prefixSumMatrix);
        return prefixSumMatrix;
    }
}
