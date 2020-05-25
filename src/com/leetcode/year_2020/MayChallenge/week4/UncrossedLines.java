package com.leetcode.year_2020.MayChallenge.week4;

import java.util.Arrays;

/**
 * @author neeraj on 25/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class UncrossedLines {

    public static void main(String[] args) {
        System.out.println(maxUncrossedLines(
                new int[]{1, 4, 2},
                new int[]{1, 2, 4})
        );
        System.out.println(maxUncrossedLines(
                new int[]{2, 5, 1, 2, 5, 3},
                new int[]{10, 5, 2, 1, 5, 2})
        );
        System.out.println(maxUncrossedLines(
                new int[]{1, 3, 7, 1, 7, 5},
                new int[]{1, 9, 2, 5, 1})
        );
        System.out.println(maxUncrossedLines(
                new int[]{2},
                new int[]{2, 3, 2, 2})
        );
        System.out.println(maxUncrossedLines(
                new int[]{3, 2},
                new int[]{2, 2, 2, 3})
        );
        System.out.println(maxUncrossedLines(
                new int[]{1, 1, 2, 1, 2},
                new int[]{1, 3, 2, 3, 1})
        );
        System.out.println(maxUncrossedLines(
                new int[]{1, 2, 3, 4, 5},
                new int[]{4, 2, 3, 4, 1})
        );
    }

    static int [][]t; // Memorization
    public static int maxUncrossedLines(int[] A, int[] B) {
        if (A.length > B.length) return maxUncrossedLines(B, A);
        /**
         * Okay so if we observe carefully this is a Longest Common Subsequence problem.
         */
        t = new int[A.length + 1][B.length + 1];
        for(int [] row: t) {
            Arrays.fill(row, -1);
        }
        return maxUncrossedLines(A, B, A.length, B.length);
    }

    private static int maxUncrossedLines(int[] a, int[] b, int m, int n) {
        // Base Condition
        if (m == 0 || n == 0) return 0; // Either 1 of the string is empty hence nothing in common.

        if(t[m][n] != -1) return t[m][n];

        if (a[m-1] == b[n-1]) {
            return t[m][n] =  1 + maxUncrossedLines(a, b, m - 1, n - 1);
        } else {
            return t[m][n] =  Math.max(maxUncrossedLines(a, b, m, n - 1),
                    maxUncrossedLines(a, b, m - 1, n));
        }
    }
}
