package com.leetcode.year_2020.sliding_window.codestorywithmik;

import java.util.HashMap;
import java.util.Map;

public class NumberofSubmatricesThatSumToTarget {

    public static void main(String[] args) {
        NumberofSubmatricesThatSumToTarget obj = new NumberofSubmatricesThatSumToTarget();
        System.out.println(obj.numSubmatrixSumTarget(new int[][]{{0, 1, 0}, {1, 1, 1}, {0, 1, 0}}, 2));
        System.out.println(obj.numSubmatrixSumTarget(new int[][]{{0, 1, 0}, {1, 1, 1}, {0, 1, 0}}, 0));
    }

    /*
     * Intuition is simple
     * We will solve it similar to {@link SubArraySumEqualsK_UsingPrefixSum} but this is in 2D space
     * so what we can do is
     *
     * 0  1  0
     * 1  1  1
     * 0  1  0
     *
     * We know that to calculate sum of any rectangle/subArray in the input, the fastest way is for a column to give me the prefix
     * rowSum and the colSum that becomes the totalSum of that rectangle or subArray.
     *
     * So first calculate rowSum in each row
     *
     * 0  1  1
     * 1  2  3
     * 0  1  1
     *
     * Now what we will do is to keep starting of the rectangle from each column
     *
     *           Start
     *            Col
     *            ||
     *   Row--->  0   1   1
     *            1   2   3
     *            0   1   1
     *
     *  so we want to pick all SubArray starting from 0Index column, then from 1 index and so on
     *
     *               Start
     *                Col
     *                ||
     *   Row--->  0   1   1
     *            1   2   3
     *            0   1   1
     *
     *                    Start
     *                    Col
     *                    ||
     *   Row--->  0   1   1
     *            1   2   3
     *            0   1   1
     *
     *  And in each of this we keep moving our j from start_col to the end column and row from start to the end
     *
     */
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int totalSubMatrix = 0;
        populateRowPrefixSum(matrix);
        for (int start_col = 0; start_col < matrix[0].length; start_col++) {
            for (int col = start_col; col < matrix[0].length; col++) {
                Map<Integer, Integer> cumulativeSumFreq = new HashMap<>();
                cumulativeSumFreq.put(0, 1); // Sum always starts from 0
                int sum = 0;
                for (int row = 0; row < matrix.length; row++) {
                    sum += start_col > 0 ? matrix[row][col] - matrix[row][start_col - 1] : matrix[row][col];
                    int diff = sum - target;
                    if (cumulativeSumFreq.containsKey(diff)) {
                        totalSubMatrix += cumulativeSumFreq.get(diff);
                    }
                    cumulativeSumFreq.put(sum, cumulativeSumFreq.getOrDefault(sum, 0) + 1);
                }
            }
        }
        return totalSubMatrix;
    }

    private void populateRowPrefixSum(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                matrix[i][j] = matrix[i][j] + matrix[i][j - 1];
            }
        }
    }
}
