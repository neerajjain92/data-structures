package com.leetcode.year_2020.june_challenge.week2;

/**
 * https://leetcode.com/problems/search-insert-position/
 *
 * @author neeraj on 10/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class SearchInsertPosition {

    public static void main(String[] args) {
        System.out.println(searchInsert(new int[]{1, 3, 5, 6}, 5));
        System.out.println(searchInsert(new int[]{1, 3, 5, 6}, 2));
        System.out.println(searchInsert(new int[]{1, 3, 5, 6}, 7));
        System.out.println(searchInsert(new int[]{1, 3, 5, 6}, 0));
        System.out.println(searchInsert(new int[]{1, 3}, 0));
    }

    public static int searchInsert(int[] nums, int target) {
        int low = 0, high = nums.length - 1, mid;

        while (low <= high) {
            mid = low + (high - low) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        /**
         * Why are we returning low
         * Take smallest example of 2 items
         *  I/p : [ 1  5 ]
         *
         *  Find "0".
         *
         *        1  5   ===> After loop ends, the pointers are at
         *   |    |
         *  high   low   (low > high) so loop breaks. where can we insert 0 not on -1 position right hence we have to put on low.
         *
         *
         *  Find "4"
         *
         *        1    5
         *        |    |
         *       high low   (So we have to insert 4 on "1"st index
         *
         *  Find "6"
         *
         *       1     5
         *             |     |
         *             high low  (we have to put 6 on 2nd index).
         */
        return low;
    }
}
