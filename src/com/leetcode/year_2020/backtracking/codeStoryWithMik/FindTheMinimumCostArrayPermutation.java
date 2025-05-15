package com.leetcode.year_2020.backtracking.codeStoryWithMik;

import com.util.LogUtil;

/**
 * 3149. Find the Minimum Cost Array Permutation
 * Hard
 * Topics
 * Companies
 * Hint
 * You are given an array nums which is a permutation of [0, 1, 2, ..., n - 1]. The score of any permutation of [0, 1, 2, ..., n - 1] named perm is defined as:
 * <p>
 * score(perm) = |perm[0] - nums[perm[1]]| + |perm[1] - nums[perm[2]]| + ... + |perm[n - 1] - nums[perm[0]]|
 * <p>
 * Return the permutation perm which has the minimum possible score. If multiple permutations exist with this score, return the one that is lexicographically smallest among them.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,0,2]
 * <p>
 * Output: [0,1,2]
 * <p>
 * Explanation:
 * <p>
 * <p>
 * <p>
 * The lexicographically smallest permutation with minimum cost is [0,1,2]. The cost of this permutation is |0 - 0| + |1 - 2| + |2 - 1| = 2.
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [0,2,1]
 * <p>
 * Output: [0,2,1]
 * <p>
 * Explanation:
 * <p>
 * <p>
 * <p>
 * The lexicographically smallest permutation with minimum cost is [0,2,1]. The cost of this permutation is |0 - 1| + |2 - 2| + |1 - 0| = 2.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 2 <= n == nums.length <= 14
 * nums is a permutation of [0, 1, 2, ..., n - 1].
 */
public class FindTheMinimumCostArrayPermutation {

    int minDiff = Integer.MAX_VALUE;
    int[] answer;

    public static void main(String[] args) {
        FindTheMinimumCostArrayPermutation obj = new FindTheMinimumCostArrayPermutation();
        LogUtil.printArray(obj.findPermutation(new int[]{1, 0, 2}));
        LogUtil.printArray(obj.findPermutation(new int[]{0, 2, 1}));
    }

    public int[] findPermutation(int[] nums) {
        answer = new int[nums.length];
        int swapPointer = 0;
        solve(swapPointer, nums, nums.clone());
        return answer;
    }

    private void solve(int swapPointer, int[] nums, int[] permutation) {
        if (swapPointer == nums.length) {
            int diff = 0, n = nums.length;
            for (int i = 0; i < n; i++) {
                diff += Math.abs(permutation[i] - nums[(i + 1) % n]);
            }
            if (minDiff > diff) {
                minDiff = diff;
                answer = permutation.clone();
            }
            return;
        }

        for (int i = swapPointer; i < nums.length; i++) {
            swap(permutation, swapPointer, i);
            solve(swapPointer + 1, nums, permutation);
            swap(permutation, swapPointer, i);
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
