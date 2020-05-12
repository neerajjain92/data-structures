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
        /**
         * Need some intuition.
         *
         * Assume we only have pairs, When we start placing all pairs.
         * (P11, P12) | (P21, P22) and so on.....
         *
         *  0      1      2     3      4      5   ====> Indexes(0 based index).
         * P11    P12    P21   P22    P31    P32
         * Even   Odd   Even   Odd    Even   Odd
         *
         * So second item of a pair always comes on odd position. Now let's try to insert some single item
         * and notice the behavior.
         *
         * Single Item on Left Side :
         *
         *  0      1      2     3      4      5      6  ====> Indexes(0 based index).
         * P11    P12    P21   P31    P32    P41   P42  >>>>> Single item on left side of mid.
         * Even   Odd   Even   Odd    Even   Odd   Even
         *
         * Notice how second item of pair disrupts due to introduction of P21 single item. we were assuming
         * second item of the pair will always comes on odd but P31 (first item of pair) came on Odd position
         * and this is contradictory to our original statement.
         *
         *
         *  0      1      2     3      4      5      6  ====> Indexes(0 based index).
         * P11    P12    P21   P22    P31    P41   P42  >>>>>> Single item on right side of mid.
         * Even   Odd   Even   Odd    Even   Odd   Even
         *
         * and ViceVersa
         */
        int low = 0;
        int high = nums.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (mid % 2 == 0) { // So this means, this is the first item of pair
                if (nums[mid] == nums[mid + 1]) {
                    low = low + 2; // perfect our assumption is correct so single item is on right side.
                    // why +2 since we are on 1st item of pair.
                } else {  // Our trust broke, item on mid(even) is not the first item of pair.
                    high = mid;
                }
            } else { // As per our assumption, odd position only contains 2nd item of pair.
                if (nums[mid] == nums[mid - 1]) {
                    low = mid + 1; // perfect our assumption is correct so single item is on right side.
                    // why +1 since we are on 2nd item of pair.
                } else { // Our trust broke, item on mid(odd) is not the second item of pair.
                    high = mid;
                }
            }
        }
        return nums[low];
    }
}
