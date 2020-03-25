package com.leetcode.year_2020;

/**
 * @author neeraj on 15/03/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class SubarraySumEqualsK {

    public static void main(String[] args) {
        System.out.println(subarraySum(new int[]{1, 1, 1}, 2));
        System.out.println(subarraySum(new int[]{28, }, 100));
    }

    public static int subarraySum(int[] nums, int k) {
        int result = 0;
        int sum = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) {
                    result += 1;
                    break;
                } else if (sum > k) {
                    break;
                }
            }
        }
        return result;
    }
}
