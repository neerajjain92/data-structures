package com.leetcode.year_2020.MayChallenge.week2;

/**
 * @author neeraj on 12/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class SingleElementInSortedArray {

    public static void main(String[] args) {
        System.out.println(singleNonDuplicate(new int[]{1}));
        System.out.println(singleNonDuplicate(new int[]{1, 1, 2, 3, 3, 4, 4, 8, 8}));
        System.out.println(singleNonDuplicate(new int[]{3, 3, 7, 7, 10, 11, 11}));
    }

    public static int singleNonDuplicate(int[] nums) {
        if (nums.length == 1) return nums[0];
        return search(nums, 0, nums.length - 1);
    }

    public static int search(int[] nums, int low, int high) {
        if (low <= high) {
            int mid = low + (high - low) / 2;

            if (mid == 0) { // check if 1st item
                // then we have to just compare with the next item
                if (nums[mid] != nums[mid + 1]) return nums[mid];
            } else if (mid == nums.length - 1) { // check if last item
                // then we have to just compare with the previous item
                if (nums[mid - 1] != nums[mid]) return nums[mid];
            } else {// Item in middle
                // we have to compare with both previous and next.
                if (nums[mid] != nums[mid - 1] &&
                        nums[mid] != nums[mid + 1]) {
                    return nums[mid];
                }

                // If item is not in middle, then we have to test in both the side
                // as we can't eliminate either side because question didn't have us any
                // other information.
                int leftResult = search(nums, low, mid - 1);
                if (leftResult != -1) { // If found in left
                    return leftResult;
                }
                return search(nums, mid + 1, high);
            }
        }
        return -1;
    }
}
