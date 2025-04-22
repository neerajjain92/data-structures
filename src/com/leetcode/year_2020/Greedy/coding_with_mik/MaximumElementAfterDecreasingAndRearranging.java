package com.leetcode.year_2020.Greedy.coding_with_mik;

import java.util.Arrays;

/**
 * 1846. Maximum Element After Decreasing and Rearranging
 * https://leetcode.com/problems/maximum-element-after-decreasing-and-rearranging/description/
 */
public class MaximumElementAfterDecreasingAndRearranging {

    public static void main(String[] args) {
        MaximumElementAfterDecreasingAndRearranging obj = new MaximumElementAfterDecreasingAndRearranging();
        System.out.println(obj.maximumElementAfterDecrementingAndRearranging(new int[]{2, 2, 1, 2, 1}));
    }

    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        Arrays.sort(arr);
        int max = 1;
        for (int i = 1; i < arr.length; i++) {
            arr[i] = Math.min(arr[i - 1] + 1, arr[i]);
            max = arr[i];
        }
        return max;
    }
}
