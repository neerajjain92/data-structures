package com.leetcode.year_2020.MayChallenge.week2;

/**
 * @author neeraj on 08/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class CheckIfItIsAStraightLine {

    public static void main(String[] args) {
        System.out.println(checkStraightLine(new int[][]{
                {1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}, {6, 7}
        }));
        System.out.println(checkStraightLine(new int[][]{
                {1, 1}, {2, 2}, {3, 4}, {4, 5}, {5, 6}, {7, 7}
        }));


    }

    public static boolean checkStraightLine(int[][] coordinates) {

        /**
         * We know 2 points in the plain always make up a Straight line
         *                          .(4,5)
         *
         *
         *       .(1, 2)
         * We know if we draw a line between these 2 points it's always straight(visually we can see this)
         * Now when 3rd point comes in that's when things become interesting.
         *
         * How can we say if all 3 points fall in same line, basically both
         * Slope of P1 and P2 == Slope of P1 and P3
         *
         * Slope is the steepness
         *
         * Slope => (y2 - y1)/(x2 - x1) == (y3-y1)/(x3-x1)
         *
         */

        if (coordinates.length == 2) return true; // Only 2 coordinates hence straight line.
        for (int i = 2; i < coordinates.length; i++) { // we are starting from i=2(3rd coordinate),
            int x1 = coordinates[i - 2][0];
            int y1 = coordinates[i - 2][1];
            int x2 = coordinates[i - 1][0];
            int y2 = coordinates[i - 1][1];
            int x3 = coordinates[i][0];
            int y3 = coordinates[i][1];
            if ((y2 - y1) * (x3 - x1) != (y3 - y1) * (x2 - x1)) {
                return false;
            }
        }
        return true;
    }

    public static boolean is3PointsCoLinear(int x1, int y1, int x2, int y2, int x3, int y3) {
        /**
         * Why are we checking for x1 == x2 || x1 == x3, simply because.
         * if 2 points are same and is being checked
         */
        return x1 == x2 || x1 == x3 || (y2 - y1) / (x2 - x1) == (y3 - y1) / (x3 - x1);
    }
}
