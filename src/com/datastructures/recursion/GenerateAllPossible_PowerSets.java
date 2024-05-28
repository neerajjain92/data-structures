package com.datastructures.recursion;

import com.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * https://leetcode.com/problems/subsets/
 * <p>
 * Given a set of distinct integers, nums, return all possible subsets (the power set).
 * <p>
 * Note: The solution set must not contain duplicate subsets.
 * <p>
 * Example:
 * <p>
 * Input: nums = [1,2,3]
 * Output:
 * [
 * *  [3],
 * *  [1],
 * *  [2],
 * *  [1,2,3],
 * *  [1,3],
 * *  [2,3],
 * *  [1,2],
 * *  []
 * ]
 *
 * @author neeraj on 2019-05-14
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class
GenerateAllPossible_PowerSets {

    public static void main(String[] args) {
        generatePowerSets(new int[]{1, 2, 3});
    }

    public static void generatePowerSets(int[] nums) {
        List<List<Integer>> powerSet = new ArrayList<>();
        List<Integer> currentSubSet = new ArrayList<>();

        powerSet(nums, 0, powerSet, currentSubSet);
        LogUtil.logIt("PowerSet for ");
        LogUtil.printArray(nums);
        LogUtil.printList(powerSet);

    }

    private static void powerSet(int[] nums, int decisionPointer, List<List<Integer>> powerSet, List<Integer> currentSubSet) {
        // If we have reached to the
        if (decisionPointer == nums.length) {
            // Deep Copy
            powerSet.add(new ArrayList<>(currentSubSet));
            return;
        }
        /**
         *This is same as input and output method.
         * Choosing the item at decisionPointer, similar to {@link com.datastructures.recursion.PrintSubsetOfAlphabets} in recursion
         *
         * // Add in output
         *
         * // Don't add in output
         *               I/P("123") --- O/P("")
         *                 /            \
         *                /              \
         *  Choose       /                \ Not Choose
         *              /                  \
         *             "23","1" |          "23" ""
         *          /              \
         *         /                \
         *       "3" "12"|            "3" "1"
         *       /     \                /
         *      /       \              /
         *     /         \            /
         *   "" "123" |  "" "12"     "" "13"
         *
         *   And so on...........
         * */
        currentSubSet.add(nums[decisionPointer]);
        powerSet(nums, decisionPointer + 1, powerSet, currentSubSet);
        currentSubSet.remove(currentSubSet.size() - 1);

        // Here we are not chosing...... Simply a recursive problem not a backtracking
        powerSet(nums, decisionPointer + 1, powerSet, currentSubSet);
    }
}
