package com.leetcode.year_2020.DP.unbounded_knapsack;

import java.util.Arrays;

/**
 * @author neeraj on 06/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class CoinChange_MinCoins {

    static int t[][]; // Memorization Cache

    public static void main(String[] args) {
        System.out.println(coinChange(new int[]{1, 2, 3}, 5));
        System.out.println(coinChange(new int[]{1, 2, 3}, 4));
        System.out.println(coinChange(new int[]{1, 2, 5}, 11));
        System.out.println(coinChange(new int[]{1, 5, 6, 9}, 11));
        System.out.println(coinChange(new int[]{2}, 3));
        System.out.println(coinChange(new int[]{470, 18, 66, 301, 403, 112, 360}, 8235));
    }

    public static int coinChange(int[] coins, int amount) {
        t = new int[coins.length + 1][amount + 1];
        for (int[] row : t) {
            Arrays.fill(row, -1);
        }
//        int minCoins = coinChangeMinimumNumbersOfCoinsRecursively(coins, coins.length - 1, amount);
        int minCoins = cointChangeMinCoins(coins, amount);
        return minCoins != Integer.MAX_VALUE ? minCoins : -1;
    }

    public static int coinChangeMinimumNumbersOfCoinsRecursively(int[] coins, int n, int amount) {
        /**
         * Here we want to get minimumNumber of coins, so our base condition will differ from
         * {@link CoinChange_NumberOfWays} problem.
         */
        if (amount == 0) { // When we have to achieve amount = 0.
            return 0;
        }
        if (n < 0) { // When we have no items then for sure we can't achieve any sum, so minimum coins tends to INFINITY.
            return Integer.MAX_VALUE;
        }

        if (t[n][amount] > -1) return t[n][amount];

        if (coins[n] <= amount) { // We can include this coin to achieve the amount.
            int picked = coinChangeMinimumNumbersOfCoinsRecursively(coins, n, amount - coins[n]);
            picked = picked == Integer.MAX_VALUE ? Integer.MAX_VALUE : 1 + picked;
            int notPicked = coinChangeMinimumNumbersOfCoinsRecursively(coins, n - 1, amount);
            return t[n][amount] = Math.min(picked, notPicked);
        } else {
            return t[n][amount] = coinChangeMinimumNumbersOfCoinsRecursively(coins, n - 1, amount);
        }
    }

    private static int cointChangeMinCoins(int[] coins, int amount) {
        int[][] t = new int[coins.length + 1][amount + 1];

        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t[0].length; j++) {
                if (i == 0) {
                    t[i][j] = Integer.MAX_VALUE; // with no coins you need hypothetically infinite coins
                }
                if (j == 0) {
                    t[i][j] = 0; // Sum required is 0 so no coins needed
                }
            }
        }

        for (int i = 1; i < t.length; i++) {
            for (int j = 1; j < t[0].length; j++) {
                if (coins[i-1] <= j) {
                    // Choose or not
                    int picked = t[i][j - coins[i-1]];
                    int notPicked = t[i - 1][j];
                    if (picked != Integer.MAX_VALUE) {
                        picked += 1; // Since the coin is used
                    }
                    t[i][j] = Math.min(picked, notPicked);
                } else {
                    // don't choose
                    t[i][j] = t[i - 1][j];
                }
            }
        }
        return t[coins.length][amount];
    }
}
