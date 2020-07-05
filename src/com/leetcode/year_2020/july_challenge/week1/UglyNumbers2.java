package com.leetcode.year_2020.july_challenge.week1;

import com.util.LogUtil;

/**
 * https://leetcode.com/problems/ugly-number-ii/
 *
 * @author neeraj on 04/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class UglyNumbers2 {

    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            LogUtil.logIt("Ugly Number at index " + i + " is " + nthUglyNumber(i));
        }
    }

    public static int nthUglyNumber(int n) {
        int[] uglyNumbers = new int[n];

        /**
         * We know 1 is an ugly number
         */
        uglyNumbers[0] = 1;
        int t2 = 0, t3 = 0, t5 = 0; // These all are pointing to their respective sorted list
        /**
         * Sorted list of multiples of 2, 3 and 5.... Now it's more of a merge k sorted list problem.
         * t2 --Multiples of 2-->
         * t3 --Multiples of 3-->
         * t5 --Multiples of 5-->
         */

        for (int i = 1; i < n; i++) { // Why less than n, since we already took "1" as the 1st ugly number

            /**
             * So ith ugly numbers will be Min(2*uglyNumber[t2], Min(3*uglyNumber[t3],5*uglyNumber[t5]));
             */
            uglyNumbers[i] = Math.min(2 * uglyNumbers[t2], Math.min(3 * uglyNumbers[t3], 5 * uglyNumbers[t5]));

            /**
             * Now we will increment respective list which gave the min output
             * Note in case when the number is a multiple of both or multiple factors
             * we will increase counter in all those list
             *
             * Example : 6... both t2 and t3 will increase.
             *          15... both t3 and t5 will increase.
             */
            if (uglyNumbers[i] == uglyNumbers[t2] * 2) t2++;
            if (uglyNumbers[i] == uglyNumbers[t3] * 3) t3++;
            if (uglyNumbers[i] == uglyNumbers[t5] * 5) t5++;
        }
        return uglyNumbers[n - 1]; // Since index runs from 0.
    }
}
