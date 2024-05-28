package com.leetcode.year_2020.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.leetcode.problems.easy.RemoveElement_27.swap;

/**
 * https://leetcode.com/problems/permutations/
 *
 * @author neeraj on 06/10/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class Permutations {

    public static void main(String[] args) {
        permute(new int[]{1, 2, 3});
        permute(new int[]{1, 2, 3, 4});
        permute(new int[]{3, 2, 5});
        permute(new int[]{1, 2, 4});
        permute(new int[]{1, 2});
        permute(new int[]{1, 1, 2});
        permute(new int[]{1, 2, 2});
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        invoked = 0;
        permuteUtil(nums, new ArrayList<>(), result);
//        System.out.println("Invoked upto " + invoked);
        System.out.println("<<<<<<<<<<<<<<<<<<Permute Without Swapping via using list?>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(result);

        result = new ArrayList<>();
        permuteUtilWithJustSwapping(nums, 0, result);
        System.out.println("<<<<<<<<<<<<<<<<<<Permute With Swapping without using list?>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(result);


        return result;
    }

    private static void permuteUtilWithJustSwapping(int[] nums, int start, List<List<Integer>> all) {
        // Base condition
        if (start == nums.length) {
            List<Integer> res = new ArrayList<>();
            for (int i : nums) {
                res.add(i);
            }
            all.add(res);
            return;
        }

        // Choices
        Set<Integer> explored = new HashSet<>();
        for (int i = start; i < nums.length; i++) {
            if (!explored.contains(nums[i])) {
                explored.add(nums[i]);

                // Swap i and start
                swap(nums, i, start);
                permuteUtilWithJustSwapping(nums, start + 1, all);
                // Undo the swap
                swap(nums, start, i);
            }
        }

    }

    static int invoked = 0;

    private static void permuteUtil(int[] nums, List<Integer> current, List<List<Integer>> all) {
        if (current.size() == nums.length) {
            all.add(new ArrayList<>(current));
            return;
        }
        invoked++;

        for (int i = 0; i < nums.length; i++) {
            if (current.contains(nums[i])) continue;
            current.add(nums[i]); // Choose
            permuteUtil(nums, current, all); // Explore
            current.remove(current.size() - 1); // Un-choose
        }
    }


    /**
     * https://www.geeksforgeeks.org/print-all-possible-combinations-of-r-elements-in-a-given-array-of-size-n/
     */
    private static void permuteUtilUptoR(int[] nums, List<Integer> current, List<List<Integer>> all, int R) {
        if (current.size() >= R) {
            all.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (current.contains(nums[i])) continue;
            current.add(nums[i]);
            permuteUtilUptoR(nums, current, all, R);
            current.remove(current.size() - 1);
        }
    }
}
