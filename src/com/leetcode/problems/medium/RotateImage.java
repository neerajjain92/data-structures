package com.leetcode.problems.medium;

import com.geeksforgeeks.array.Rotate2DMatrix;

/**
 * @author neeraj on 08/10/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class RotateImage {

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        Rotate2DMatrix.print2DArray(matrix);
        rotate(matrix);
        Rotate2DMatrix.print2DArray(matrix);

    }

    /**
     * Simple Transpose the matrix
     * and just flip it
     * 1 2 3
     * 4 5 6
     * 7 8 9
     * <p>
     * So transpose of it
     * 1 4 7
     * 2 5 8
     * 3 6 9
     * <p>
     * // Now look at what we want to achieve is just the flip of above transpose output
     * <p>
     * 7 4 1
     * 8 5 2
     * 9 6 3
     *
     * @param matrix
     */
    public static void rotate(int[][] matrix) {
        transposeMatrix(matrix);

        // Now just flip it
        for (int i = 0; i < matrix.length; i++) {
            swap1DMatrix(matrix[i]);
        }
    }

    private static void swap1DMatrix(int[] matrix) {
        int left = 0;
        int right = matrix.length - 1;
        while (left < right) {
            int temp = matrix[left];
            matrix[left] = matrix[right];
            matrix[right] = temp;
            left++;
            right--;
        }
    }

    private static void transposeMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix[i].length; j++) {
                swap(matrix, i, j, j, i);
            }
        }
    }

    private static void swap(int[][] matrix, int i, int j, int k, int l) {
        int temp = matrix[i][j];
        matrix[i][j] = matrix[k][l];
        matrix[k][l] = temp;
    }
}
