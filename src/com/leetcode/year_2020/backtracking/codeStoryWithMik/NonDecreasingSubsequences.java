package com.leetcode.year_2020.backtracking.codeStoryWithMik;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 491. Non-decreasing Subsequences
 * Medium
 * Topics
 * Companies
 * Given an integer array nums, return all the different possible non-decreasing subsequences
 * of the given array with at least two elements. You may return the answer in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [4,6,7,7]
 * Output: [[4,6],[4,6,7],[4,6,7,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]
 * Example 2:
 * <p>
 * Input: nums = [4,4,3,2,1]
 * Output: [[4,4]]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 15
 * -100 <= nums[i] <= 100
 */
public class NonDecreasingSubsequences {

    public static void main(String[] args) {
        NonDecreasingSubsequences obj = new NonDecreasingSubsequences();
        System.out.println(obj.findSubsequences(new int[]{4, 6, 7, 7}));
    }

    public List<List<Integer>> findSubsequences(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        backtrack(0, nums, new ArrayList<>(), result);
        return new ArrayList<>(result);
    }

    private void backtrack(int start, int[] nums, List<Integer> currentPath, Set<List<Integer>> result) {
        if (currentPath.size() >= 2) {
            result.add(new ArrayList<>(currentPath));
        }

        // Use a set to track used numbers at this level to avoid duplicate paths
        Set<Integer> used = new HashSet<>();

        for (int i = start; i < nums.length; i++) {
            if (used.contains(nums[i])) {
                continue;
            }
            if (currentPath.isEmpty() || currentPath.get(currentPath.size() - 1) <= nums[i]) {
                used.add(nums[i]);
                currentPath.add(nums[i]);
                backtrack(i + 1, nums, currentPath, result);
                currentPath.remove(currentPath.size() - 1);
            }
        }


    }
}
