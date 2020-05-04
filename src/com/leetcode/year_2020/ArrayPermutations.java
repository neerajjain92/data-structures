package com.leetcode.year_2020;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Both
 * https://leetcode.com/problems/permutations/ (Non Duplicate)
 * https://leetcode.com/problems/permutations-ii/submissions/ (Duplicate check).
 *
 * @author neeraj on 10/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class ArrayPermutations {

    public static void main(String[] args) {
        System.out.println(permute(new int[]{1, 2, 3}));
        System.out.println(permute(new int[]{1, 2, 2}));
    }

    public static List<List<Integer>> permute(int[] nums) {
        // To Avoid duplicates
        Arrays.sort(nums);
        List<List<Integer>> permutations = new ArrayList<>();
        boolean[] used = new boolean[nums.length]; // keep track of which indexes have we already visited.
        permuteUtil(nums, new ArrayList<>(), permutations, used);
        return permutations;
    }

    private static void permuteUtil(int[] nums, ArrayList<Integer> currentPermutation, List<List<Integer>> permutations,
                                    boolean[] used) {
        if (currentPermutation.size() == nums.length) {
            permutations.add(new ArrayList<>(currentPermutation));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            /**
             * This is the most important check
             *  We are keeping track of 2 things here
             *  1st. Since loop always runs from 0 to length
             *  We need to make sure that in the currentIteration we don't re-iterate on the same i
             *  hence for that we are using "i"
             *  Now For Duplicate check we need to make sure, assuming we sorted the Array before
             *  that the index[i] != index[i-1] if that's the case we should not recurse
             *  But here is the tweak we should only do this "No Recursion thing for Duplicate"
             *  Only if we are in a recurring call, not when it's the first call of itself
             *  Hence if you see we have "!used[i-1]" check, this is helpful since when we backtrack
             *  and reset i-1 index to false, only then we need to make sure that we are not recursing
             *  on a duplicate element.
             */
            if (used[i] || (i > 0 && nums[i] == nums[i - 1]) && !used[i - 1]) continue;
            used[i] = true;
            currentPermutation.add(nums[i]);
            permuteUtil(nums, currentPermutation, permutations, used);
            used[i] = false;
            currentPermutation.remove(currentPermutation.size() - 1);
        }

    }
}
