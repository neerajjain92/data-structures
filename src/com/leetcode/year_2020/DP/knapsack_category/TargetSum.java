package com.leetcode.year_2020.DP.knapsack_category;

/**
 * https://leetcode.com/problems/target-sum/
 *
 * @author neeraj on 06/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class TargetSum {

    public static void main(String[] args) {
        System.out.println(findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3));
        System.out.println(findTargetSumWays(new int[]{1, 1, 2, 3}, 1));
    }


    public static int findTargetSumWays(int[] nums, int S) {
        /**
         * This problem is similar to {@link CountTheNumberOfSubsetWithGivenDifference}
         * just leet-code has phrased it differently.
         *
         * You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.
         *
         * Find out how many ways to assign symbols to make sum of integers equal to target S.
         *
         * Input: nums is [1, 1, 1, 1, 1], S is 3.
         * Output: 5
         * Explanation:
         *
         * -1+1+1+1+1 = 3
         * +1-1+1+1+1 = 3
         * +1+1-1+1+1 = 3
         * +1+1+1-1+1 = 3
         * +1+1+1+1-1 = 3
         *
         * There are 5 ways to assign symbols to make the sum of nums be target 3.
         *
         * Now if you Notice all it says is S1(Sum Of Set1) - S2(Sum of Set2) should be 3.
         *
         * Since S1 - S2 = difference or S
         *       S1 + S2 = Total_Sum
         *      ---------------------
         *      2S1 = diff(S) + Total_Sum
         *
         *      S1 = (diff(S) + Total_Sum)/2
         */
        return CountTheNumberOfSubsetWithGivenDifference
                .getCountOfNumberOfSubSetWithGivenDifference(nums, S);
    }
}
