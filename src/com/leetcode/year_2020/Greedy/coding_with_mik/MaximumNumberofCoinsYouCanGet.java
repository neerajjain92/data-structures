package com.leetcode.year_2020.Greedy.coding_with_mik;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/maximum-number-of-coins-you-can-get/description/
 * 1561. Maximum Number of Coins You Can Get
 */
public class MaximumNumberofCoinsYouCanGet {

    public static void main(String[] args) {
        MaximumNumberofCoinsYouCanGet obj = new MaximumNumberofCoinsYouCanGet();
        System.out.println(obj.maxCoins(new int[]{2, 4, 1, 2, 7, 8}));
        System.out.println(obj.maxCoins(new int[]{2, 4, 5}));
        System.out.println(obj.maxCoins(new int[]{9, 8, 7, 6, 5, 1, 2, 3, 4}));
    }

    public int maxCoins(int[] piles) {
        Arrays.sort(piles);

        int bob = 0;
        int me = piles.length - 2;
        int maxCoins = 0;
        while (bob < me) {
            maxCoins += piles[me];
            me -= 2;
            bob++;
        }
        return maxCoins;
    }
}
