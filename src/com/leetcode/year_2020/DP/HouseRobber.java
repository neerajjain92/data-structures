package com.leetcode.year_2020.DP;

/**
 * https://leetcode.com/problems/house-robber/
 * <p>
 * This problem can also solve :
 * https://www.geeksforgeeks.org/maximum-sum-such-that-no-two-elements-are-adjacent/
 *
 * @author neeraj on 05/09/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class HouseRobber {

    public static void main(String[] args) {
        System.out.println(rob(new int[]{1, 2, 3, 1}));
        System.out.println(rob(new int[]{2, 7, 9, 3, 1}));

        /**
         * For Maximum sum such that no two elements are adjacent test cases
         */
        System.out.println(rob(new int[]{1, 2, 3}));
        System.out.println(rob(new int[]{5, 5, 10, 100, 10, 5}));
        System.out.println(rob(new int[]{1, 20, 3}));
        System.out.println(rob(new int[]{2, 1, 1, 2}));


    }

    public static int rob(int[] nums) {
        /**
         * This problem is exactly similar to {@link MaximumSumSuchThatNoTwoElementsAreAdjacent}
         */
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max((nums[i] + dp[i - 2]), dp[i - 1]);
        }

        return dp[nums.length - 1];
    }
}
