package com.leetcode.year_2020.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/permutations/
 *
 * @author neeraj on 06/10/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class Permutations {

    public static void main(String[] args) {
        System.out.println(permute(new int[]{1, 2, 3}));
        System.out.println(permute(new int[]{3, 2, 5}));
        System.out.println(permute(new int[]{1, 2, 4}));
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        permuteUtil(nums, new ArrayList<>(), result);
        return result;
    }

    private static void permuteUtil(int[] nums, List<Integer> current, List<List<Integer>> all) {
        if (current.size() == nums.length) {
            all.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (current.contains(nums[i])) continue;
            current.add(nums[i]); // Choose
            permuteUtil(nums, current, all); // Explore
            current.remove(current.size() - 1); // Un-choose
        }
    }
}
