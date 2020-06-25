package com.leetcode.year_2020.june_challenge.week4;

/**
 * @author neeraj on 25/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class FindTheDuplicateNumber {

    public static void main(String[] args) {
        System.out.println(findDuplicate(new int[]{1, 2, 2, 3, 4, 5, 6, 7, 8, 9}));
        System.out.println(findDuplicate(new int[]{1, 2, 3, 4, 5, 6, 7, 7, 9, 10}));
        System.out.println(findDuplicate(new int[]{2, 5, 9, 6, 9, 3, 8, 9, 7, 1}));
    }

    public static int findDuplicate(int[] nums) {
        /**
         * We can use Finding start of loop in linked list approach.
         * Here since we know numbers are between 1 to n we can easily jump between those numbers
         */
        int slow = nums[0];
        int fast = nums[nums[0]];
        if (nums.length > 1) {
            while (slow != fast) {
                slow = nums[slow];
                fast = nums[nums[fast]];
            }


            // Now since there is a duplicate they must meet each other due to lyod's Algo.
            fast = 0;
            while (fast != slow) {
                fast = nums[fast];
                slow = nums[slow];
            }
            return slow;
        }
        return -1;
    }
}
