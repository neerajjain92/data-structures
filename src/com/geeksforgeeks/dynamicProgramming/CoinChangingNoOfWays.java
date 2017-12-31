package com.geeksforgeeks.dynamicProgramming;

import com.geeksforgeeks.array.Rotate2DMatrix;

/**
 * https://www.youtube.com/watch?v=ZaVM057DuzE
 * <p>
 * Algorithm
 * <p>
 * No of ways coin can be formed
 * <p>
 * 1) (Excluding the new coin)
 * 2) (Including the new coin)
 * 3) ( Sum step (1) + (2)
 */
public class CoinChangingNoOfWays {

    public static void main(String[] args) {
//        System.out.println(getNoOfWaysSumCanBeAchieved(new int[]{1, 2, 3}, 6));
//        System.out.println(getNoOfWaysSumCanBeAchieved(new int[]{1, 2, 3}, 5));
//        System.out.println(getNoOfWaysSumCanBeAchieved(new int[]{2, 3, 5, 6}, 10));
//        System.out.println(getNoOfWaysSumCanBeAchieved(new int[]{3, 5}, 17));

        System.out.println(getNoOfWays(new int[]{1, 2, 3, 4, 5}, 5));
    }

    public static int getNoOfWaysSumCanBeAchieved(int[] coins, int sum) {
        int[][] ways = new int[coins.length][sum + 1];
        //Rotate2DMatrix.print2DArray(ways);
        for (int i = 0; i <= sum; i++)
            ways[0][i] = 1;
        for (int i = 0; i < coins.length; i++) // For Sum = 0, there is only one way to arrange coins(i.e. not include any coin at all)
            ways[i][0] = 1;

        for (int i = 1; i < coins.length; i++) {
            for (int j = 1; j < sum + 1; j++) {
                if (coins[i] > j) {
                    ways[i][j] = ways[i - 1][j];
                } else {
                    ways[i][j] = ways[i][j - coins[i]] + ways[i - 1][j]; // Here ways[i-1][j] is the minimum no of ways with excluding the new coin and ways[i][j - coins[i]] is no of ways including the new coin
                }
                // Rotate2DMatrix.print2DArray(ways);
            }
        }
        Rotate2DMatrix.print2DArray(ways);
        return ways[ways.length - 1][sum];
    }

    public static int getNoOfWays(int[] coins, int sum) {
        int[][] ways = new int[coins.length + 1][sum + 1];

        for (int i = 0; i <= coins.length; i++) {
            for (int j = 0; j <= sum; j++) {
                if (i == 0 && j == 0) {
                    ways[i][j] = 1;
                } else if (i == 0) {
                    ways[i][j] = 0;
                } else {
                    if (i > j) {
                        ways[i][j] = ways[i - 1][j];
                    } else {
                        ways[i][j] = ways[i][j - i] + ways[i - 1][j]; // Exclusion + Inclusion
                    }
                }
            }
        }
        Rotate2DMatrix.print2DArray(ways);
        return ways[coins.length][sum];
    }
}
