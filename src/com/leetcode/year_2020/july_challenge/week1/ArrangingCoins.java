package com.leetcode.year_2020.july_challenge.week1;

/**
 * @author neeraj on 01/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class ArrangingCoins {

    public static void main(String[] args) {

    }

    public int arrangeCoins(int n) {
        /**
         **  So the problem boils down to find position in running sum of consecutive numbers
         * and we can basically do a  binary search on prefix sum of consecutive numbers upto n and
         * we know the sum of n numbers = n*(n+1)/2
         **/
        // Using long to avoid overflow...
        long low = 1;
        long nInLong = n;
        long high = nInLong;
        while (low <= high) {
            long mid = low + (high - low) / 2;

            // Here we need to check if upto mid
            // did we use all bricks to construct stair, and how do you check that
            // simple take sum upto mid
            if (mid * (mid + 1) <= nInLong * 2) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return (int) low - 1;
    }
}
