package com.leetcode.year_2020.DP.minimum_maximum_path_to_reach_to_target;

import java.util.Arrays;

/**
 * @author neeraj on 06/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class PerfectSquares {

    static int T[];

    public static void main(String[] args) {
        System.out.println(numSquares(2));

    }

    /**
     * Some Insights.....
     * We know that numSquares of when we need to achieve number -> 1
     * is 1.
     * <p>
     * So what we can do at every number is to give try of all possible numSquares below that.
     */
    public static int numSquares(int n) {
        T = new int[n + 1];
        Arrays.fill(T, -1);
        return solveNumSquares(n);
    }

    private static int solveNumSquares(int n) {
        // Base Condition
        if (n == 0) return 0;
        if (n == 1) return 1;


        if (T[n] != -1) return T[n];

        int value = Integer.MAX_VALUE;
        for (int i = 1; Math.pow(i, 2) <= n; i++) { // Trying all possible Perfect Squares
            /**
             * So if we are trying to find numSquares for 5
             * We have just 2 choices of perfect square 1 and 4, we will try both
             *
             * 1 + numSquare(5 - 1) : Taking 1 as perfect square
             * OR
             * 1 + numSquare(5 - 4) : Taking 4 as perfect square.
             */
            value = Math.min(value, 1 + solveNumSquares((int) (n - Math.pow(i, 2))));
        }
        return T[n] = value;
    }
}
