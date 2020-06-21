package com.leetcode.year_2020.june_challenge.week3;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/dungeon-game/
 * -2 	-3	3
 * -5	-10	1
 * 10	30	-5
 *
 * @author neeraj on 21/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class DungeonGame {

    public static void main(String[] args) {
        System.out.println(calculateMinimumHP(new int[][]{
                {-2, -3, 3},
                {-5, -10, 1},
                {10, 30, -5}
        }));

        System.out.println(calculateMinimumHP(new int[][]{
                {0, 0}
        }));

        System.out.println(calculateMinimumHP(new int[][]{
                {-1, 1}
        }));
    }

    public static int calculateMinimumHP(int[][] dungeon) {
        /**
         * So starting from top-left is not possible in this problem
         * reason we don't know how much extra fuel will be needed in the rest of
         * dungeon as we don't know how many daemons(-ve) or angels(+ve) are sitting
         *
         * Also at each cell only the right and the bottom cell can tell us
         * what is the minimum cost required.
         *
         *                  |
         *      King        |   SOME HEALTH POINT
         *    -------------------------------
         *                  |
         *    SOME COST     |  Princess.
         *    HEALTH POINT  |
         *
         *    because for a simple matrix we know that when we reach to princess our
         *    minimum cost should be just 1.
         *
         *    So
         *    [-10   1
         *     30    -5]
         *
         *    If we have such board.
         *    we know princess is at [1,1] position and when we reach there
         *    we will lost -5 health, so we need at-least 6 health point
         *    when we reach to princess so that we will be alive.
         *
         *    Similarly when we are 1 [0,1], we know that to reach to princess
         *    we need at-least 6, but [0,1] position already had 1 health, so the health
         *    left to be achieved is just 5... or you can say (6 - (1 initial health)) = 5.
         *
         *    Same when we are on 30[1,0] we know we need at-least 6 when we reach
         *    to princess [6 - 30] = -24... but can you have a negative health
         *    no right, so you just take the minimum valid health. i.e (1)
         *
         *    Now the interesting part at [0,0] 10.....here i have 2 choices
         *    i can go down or right. but we need just minimum health.
         *    so i will choose 30[1,0] path since there i need just 1 health point
         *
         *    1 - (-10) .... so i need minimum health point (11) at[0,0]
         *    to safely reach princess..
         *
         *    What is the recurrence
         *
         *    minHealth[i][j] = Math.min(minHealth[i+1][j], minHealth[i][j+1]) - dungeon[i][j];
         **/
        int minHealth[][] = new int[dungeon.length + 1][dungeon[0].length + 1]; // Why +1 ?
        // Since we know that when we are on last row and last column column if we don't put
        // extra row and column we need to do if-else check and to avoid that let's have extra space.

        for (int[] row : minHealth) {
            Arrays.fill(row, Integer.MAX_VALUE); // Initially we need the max health until we know the real health.
        }

        //We will start from princess and our answer will be on minHealth[0][0]
        // Since we are starting from princess[m][n] we know 1 thing for sure that at this health point should be 1

        minHealth[minHealth.length - 2][minHealth[0].length - 1] = 1;
        minHealth[minHealth.length - 1][minHealth[0].length - 2] = 1;

        for (int i = dungeon.length - 1; i >= 0; i--) {
            for (int j = dungeon[0].length - 1; j >= 0; j--) {
                minHealth[i][j] = Math.min(minHealth[i + 1][j], minHealth[i][j + 1]) - dungeon[i][j];
                if (minHealth[i][j] <= 0) minHealth[i][j] = 1; // Since can't have negative health.
            }
        }
        return minHealth[0][0];
    }
}
