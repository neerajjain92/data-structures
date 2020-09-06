package com.leetcode.year_2020.DP;

/**
 * @author neeraj on 25/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class OptimalStrategyForGame {

    public static void main(String[] args) {
        System.out.println(findMaximumPointGain(new int[]{8, 15, 3, 7}, 0, 3));
        System.out.println(findMaximumPointGain(new int[]{3, 9, 1, 2}, 0, 3));
    }

    private static int findMaximumPointGain(int[] points, int i, int j) {
        if (i == j) { // Only 1 point in the system
            return points[i];
        }
        if (i + 1 == j) { // Only 2 points
            return Math.max(points[i], points[j]);
        }

        /**
         * Now if i choose point[i] then obviously my opponent will choose such a number so that i loose
         * that's why instead of being greedy we need to check all possible combination.
         *
         * So if i choose points[i] then my opponent will choose max of left items
         * and we will be left with
         *
         * option1 = point[i] + Math.min(f(i+2, j), f(i+1, j-1))
         * option2 = point[j] + Math.min(f(i+1, j-1), f(i, j-2))
         *
         * and we need Max(options1, option2);
         */
        int option1 = points[i] + Math.min(findMaximumPointGain(points, i + 2, j), findMaximumPointGain(points, i + 1, j - 1));
        int option2 = points[j] + Math.min(findMaximumPointGain(points, i + 1, j - 1), findMaximumPointGain(points, i, j - 2));

        return Math.max(option1, option2);
    }
}
