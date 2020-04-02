package com.leetcode.year_2020;

import java.util.Arrays;

/**
 * @author neeraj on 02/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class PartitionEqualSubsetSum {

    public static void main(String[] args) {
        System.out.println(canPartition(new int[]{1, 5, 11, 5}));
        System.out.println(canPartition(new int[]{1, 9, 8, 10}));
        System.out.println(canPartition(new int[]{1, 2, 3, 5}));
        System.out.println(canPartition(new int[]{1, 2, 3, 4, 5, 6, 7}));
    }

    // 1, 5, 11, 5
    // 1, 9, 8, 10;
    public static boolean canPartition(int[] nums) {
        Arrays.sort(nums);
        int firstPointer = 0;
        int lastPointer = nums.length - 1;
        int leftSum = 0;
        int rightSum = 0;

        while (firstPointer < lastPointer) {
            if (nums[firstPointer] + leftSum < nums[lastPointer] + rightSum) {
                leftSum += nums[firstPointer];
                firstPointer++;
            } else if (nums[firstPointer] + leftSum > nums[lastPointer] + rightSum) {
                rightSum += nums[lastPointer];
                lastPointer--;
            } else if (nums[firstPointer] + leftSum == nums[lastPointer] + rightSum) {
                if (lastPointer - firstPointer > 1) {
                    continue;
                } else {
                    return true;
                }
            }
        }
        return false;
    }
}
