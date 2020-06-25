package com.leetcode.year_2020.prefix_sum_technique;

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
        int startOfRow = tli, endOfRow = rbi, endCol = rbj, startCol = tli;

        int rangeSum = 0;
        for (int i = startOfRow; i <= endOfRow; i++) {
            rangeSum += aux[i][endCol] - ((startCol - 1 < 0) ? 0 : aux[i][startCol - 1]);
        }
        return rangeSum;
    }

    public static int[][] preProcess(int mat[][]) {
        int[][] prefixSumMatrix = new int[mat.length][mat[0].length];

        int count = 0;
        for (int[] row : mat) {
            int[] prefixSumRow = new int[row.length];
            prefixSumRow[0] = row[0];
            for (int i = 1; i < row.length; i++) {
                prefixSumRow[i] = row[i] + prefixSumRow[i - 1];
            }
            prefixSumMatrix[count++] = prefixSumRow;
        }
        return prefixSumMatrix;
    }
}
