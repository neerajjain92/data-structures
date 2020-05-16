package com.leetcode.year_2020.MayChallenge.week3;

/**
 * @author neeraj on 15/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MaximumSumCircularSubarray {

    public static void main(String[] args) {
        System.out.println(maxSubarraySumCircular(new int[]{1,-2,3,-2}));
        System.out.println(maxSubarraySumCircular(new int[]{5, -3, 5}));

    }

    public static int maxSubarraySumCircular(int[] A) {
        /**
         *   We have 2 cases to handle,
         *   1) Max SubArray in between    [1,-2,3,-2]  : Simply solve using Kadane's Algorithm
         *.  2) Max Sub Array is split accorss
         *          in circular subarray.   [5, -3, 5]
         *   In this case : We will find the MinSubArray using Kadane's approach only. and then subtract from total sum
         * So this will be 5 + 5 + (-3) - (-3) ===> 10 which is the max Sum
         **/

        int MAX_TILL_NOW = Integer.MIN_VALUE;
        int MIN_TILL_NOW = Integer.MAX_VALUE;
        int MAX_ENDING_HERE = 0;
        int MIN_ENDING_HERE = 0;
        int total = 0;

        for(int i: A) {
            MAX_ENDING_HERE += i;
            // System.out.println("MAX_ENDING_HERE" + MAX_ENDING_HERE);

            MAX_TILL_NOW = Math.max(MAX_TILL_NOW, MAX_ENDING_HERE);
            if(MAX_ENDING_HERE < 0) MAX_ENDING_HERE = 0;

            MIN_ENDING_HERE += i;

            // System.out.println("MIN_ENDING_HERE" + MIN_ENDING_HERE);

            MIN_TILL_NOW = Math.min(MIN_TILL_NOW, MIN_ENDING_HERE);
            if(MIN_ENDING_HERE > 0) MIN_ENDING_HERE = 0;

            total += i;
        }

        return MAX_TILL_NOW < 0 ? MAX_TILL_NOW : Math.max(MAX_TILL_NOW, total - MIN_TILL_NOW);
    }
}
