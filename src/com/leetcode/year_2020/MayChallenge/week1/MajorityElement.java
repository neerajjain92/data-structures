package com.leetcode.year_2020.MayChallenge.week1;

/**
 * @author neeraj on 06/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MajorityElement {

    public static void main(String[] args) {
        System.out.println(majorityElement(new int[]{3, 2, 3}));
        System.out.println(majorityElement(new int[]{2, 2, 1, 1, 1, 2, 2}));
    }

    /**
     * A linear time Majority Vote Algorithm
     * http://www.cs.utexas.edu/~moore/best-ideas/mjrty/index.html
     **/
    public static int majorityElement(int[] nums) {
        /**
         *  This algo is divided into 2 steps
         *
         * In Step 1 we identify the find the majority element by counting it's occurrence in 1 go
         * In Step 2 we confirm that the element found in step 1 is actually a Majority Element.
         */
        int count = 0; // Initially we don't know what is the majority element
        Integer majorityElement = null;
        for (int i : nums) {
            if (count == 0) {
                // Once we reach to a count 0 we need to reset the majorityElement
                majorityElement = i;
                count = 1;
            } else {
                if (i == majorityElement) count++;
                else count--;
            }
        }
        // Since problem statement says that majorityElement exist.
        // we can just return else we could do one more pass to actually verify that
        // element is really a MAJORITY ELEMENT
        // When can this probable situation happen
        /**
         * A A A C C B B C C C B C C
         *
         *  The majority element is C (if any element has a majority).
         *  Note that if you replaced the first C with an A, above, the algorithm would still end with C being chosen, but in fact C would not be the majority element: there is no majority in the modified list.
         *
         * In some situations you know, or assume, there is a majority element.
         *  But if you must confirm that the chosen element is indeed the majority element,
         *  you must take one more linear pass through the data to do it.
         */
        return majorityElement;
    }
}
