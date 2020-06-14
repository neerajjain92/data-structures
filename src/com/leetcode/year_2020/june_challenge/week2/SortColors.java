package com.leetcode.year_2020.june_challenge.week2;

import com.util.LogUtil;

/**
 * Sort Zeros One and Two
 *
 * @author neeraj on 11/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class SortColors {

    public static void main(String[] args) {
        int [] color =new int[]{2, 0, 2, 1, 1, 0};
        sortColors(color);
        LogUtil.printArray(color);
    }

    public static void sortColors(int[] nums) {
        /**
         * We can follow the Generic Approach here.
         * We have to make a Sandwich here by keeping
         * pivot value (which is 1 in this case) in between
         * and the other smaller and greater value to left and right
         * respectively.
         */
        int pivotValue = 1;
        int pointer = 0;

        // First Pass move all small values to the left of pivot
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < pivotValue) {
                swap(nums, i, pointer);
                pointer++;
            }
        }

        // Second Pass move all greater value than pivot to the right
        pointer = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] > pivotValue) {
                swap(nums, i, pointer);
                pointer--;
            }
        }
    }

    private static void swap(int[] nums, int i, int pointer) {
        int temp = nums[i];
        nums[i] = nums[pointer];
        nums[pointer] = temp;
    }
}
