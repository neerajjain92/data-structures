package com.leetcode.year_2020.SeptemberChallenge.week1;

import java.util.TreeSet;

/**
 * https://leetcode.com/problems/contains-duplicate-iii/
 *
 * @author neeraj on 02/09/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class ContainsDuplicate_3 {

    public static void main(String[] args) {
        System.out.println(containsNearbyAlmostDuplicate(new int[]{1, 2, 3, 1}, 3, 0));
        System.out.println(containsNearbyAlmostDuplicate(new int[]{1, 0, 1, 1}, 1, 2));
        System.out.println(containsNearbyAlmostDuplicate(new int[]{1, 5, 9, 1, 5, 9}, 2, 3));
    }

    public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        /**
         * Well we know one thing for sure when we add a new number if we can find
         * smallest number just largest than the new number being input and the difference between them is <= k
         * or similarly largest number just smaller thant the new number and difference between them is <= k
         * we found our almost duplicate.
         *
         * For this the TreeSet is the best data structure we have.
         */
        TreeSet<Long> treeSet = new TreeSet<>();

        for (int i = 0; i < nums.length; i++) {
            Long floor = treeSet.floor((long) nums[i]);

            if (floor != null && nums[i] - floor <= t) {
                return true;
            }

            Long ceiling = treeSet.ceiling((long) nums[i]);
            if (ceiling != null && ceiling - nums[i] <= t) {
                return true;
            }

            treeSet.add((long) nums[i]);
            if (treeSet.size() > k) {
                treeSet.remove((long) nums[i - k]);
            }
        }
        return false;
    }
}
