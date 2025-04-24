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

    public int maxCoinsApproach2(int[] piles) {
        Arrays.sort(piles);

        // We know that piles are to be picked in 3
        // So and we have 3n coins
        // So n/3 is something which bob will anyways take since we are giving him smalles
        // [1    2     3      4     5    6     7    8    9]
        //  B    B     B      M     A    M     A    M    A
        // So why not start our counter of Myself from n/3
        int self = piles.length / 3;
        int count = 0;
        while (self < piles.length - 1) {
            count += piles[self];
            self+=2;
        }
        return count;
    }
}
