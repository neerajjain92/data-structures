package com.leetcode.year_2020;

/**
 * @author neeraj on 06/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 * Solution inspired by :
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/discuss/108870/Most-consistent-ways-of-dealing-with-the-series-of-stock-problems/111002
 */
public class BestTimeToBuyAndSellStockWithCoolDown {

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{1,2,3,0,2}));
    }

    /**
     * Input: [1,2,3,0,2]
     * Output: 3
     * Explanation: transactions = [buy, sell, cooldown, buy, sell]
     * <p>
     * You may not engage in multiple transactions at the same time (
     * ie, you must sell the stock before you buy again).
     * After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
     */
    public static int maxProfit(int[] prices) {
        // This is similar to having k(transactions) = +Infinity
        // T[i][k][0] ==> i: day, k: transactions, 0 ==> At the end of day we have 0 shares left(we sold)
        // T[i][k][1] ==> i: day, k: transactions, 1 ==> At the end of day we have 1 shares left(we purchased)

        int Ti_k0 = 0;
        int Ti_k1 = Integer.MIN_VALUE;
        int previousProfit = 0; // This is the profit before cooldown date.
        // i.e the profit remained after selling the stock before cooldown date.

        for (int price : prices) {
            // Rest on Previous day or Sold today.
            int Ti_k0_old = Ti_k0;
            Ti_k0 = Math.max(Ti_k0, Ti_k1 + price);
            Ti_k1 = Math.max(Ti_k1, previousProfit - price);
            previousProfit = Ti_k0_old;
        }
        return Ti_k0;
    }
}
