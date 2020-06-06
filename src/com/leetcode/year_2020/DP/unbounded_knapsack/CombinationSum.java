package com.leetcode.year_2020.DP.unbounded_knapsack;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/combination-sum/
 *
 * @author neeraj on 07/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class CombinationSum {

    public static void main(String[] args) {
        System.out.println(combinationSum(new int[]{2, 3, 6, 7}, 7));
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        // We will solve this problem using backtracking and then proceed with DP.
        List<List<Integer>> result = new ArrayList<>();
        combinationSum(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private static void combinationSum(int[] candidates, int target, int pointer, ArrayList<Integer> combination, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(combination));
        }

        for (int i = pointer; i < candidates.length; i++) {
            if (candidates[i] <= target) {
                combination.add(candidates[i]); // Choose
                // Explore, but don't increment pointer since same value can be re-used like Unbounded knapsack can be re-used.
                combinationSum(candidates, target - candidates[i], i, combination, result);
                combination.remove(combination.size() - 1); // Un-choose
            }
        }
    }
}
