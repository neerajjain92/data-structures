package com.leetcode.covid19;

/**
 * https://leetcode.com/problems/single-number/
 *
 * @author neeraj on 02/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class SingleNumber {

    public static void main(String[] args) {
        System.out.println(singleNumber(new int[]{2, 2, 1}));
        System.out.println(singleNumber(new int[]{4, 1, 2, 1, 2}));
    }

    public static int singleNumber(int[] nums) {
        int result = 0;
        for (int i : nums) {
            result ^= i;
        }
        return result;
    }
}
