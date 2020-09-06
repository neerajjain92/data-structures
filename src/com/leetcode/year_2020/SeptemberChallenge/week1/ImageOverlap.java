package com.leetcode.year_2020.SeptemberChallenge.week1;

/**
 * Best Explanation : https://www.youtube.com/watch?v=zfjBapE3Y6E
 * https://leetcode.com/problems/image-overlap/
 *
 * @author neeraj on 06/09/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class ImageOverlap {

    public static void main(String[] args) {
        System.out.println(largestOverlap(new int[][]{
                {1, 1, 0},
                {0, 1, 0},
                {0, 1, 0}
        }, new int[][]{
                {0, 0, 0},
                {0, 1, 1},
                {0, 0, 1}
        }));
    }

    public static int largestOverlap(int[][] A, int[][] B) {
        /**
         * So all that problem is saying that you are given 2 (2D) Arrays,
         * you need to keep 1 array static and move other array around such that on
         * whatever area they are overlapping you need to find total such cells where both array
         * has 1's in it. and we need the largest possible overlap?
         *
         * Important point we can move right, down, left, and up....
         * Moving Right(A) and Down(A) (is equivalent of) == Moving Left(B) and Up(B)
         *
         * So we will just switch the arrays for calculation.
         */
        return Math.max(shiftAndCount(A, B), shiftAndCount(B, A));
    }

    private static int shiftAndCount(int[][] a, int[][] b) {
        /**
         * Firstly we have to keep A static and Move B from full covering A and then sliding B to right
         * and bottom.
         */
        int count = Integer.MIN_VALUE;
        for (int row = 0; row < a.length; row++) {
            for (int col = 0; col < a.length; col++) {
                // These outer 2 loops is to just shift B around
                int temp = 0;
                for (int i = row; i < a.length; i++) {
                    for (int j = col; j < a.length; j++) {
                        if (a[i][j] == 1 && b[i - row][j - col] == 1) {
                            temp++;
                        }
                    }
                }
                count = Math.max(count, temp);
            }
        }
        return count;
    }
}
