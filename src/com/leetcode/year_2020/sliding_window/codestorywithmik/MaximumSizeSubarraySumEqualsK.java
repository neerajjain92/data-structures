package com.leetcode.year_2020.sliding_window.codestorywithmik;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.ca/all/325.html
 * <p>
 * 325. Maximum Size Subarray Sum Equals k
 * Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.
 * <p>
 * Note:
 * The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1, -1, 5, -2, 3], k = 3
 * Output: 4
 * Explanation: The subarray [1, -1, 5, -2] sums to 3 and is the longest.
 * Example 2:
 * <p>
 * Input: nums = [-2, -1, 2, 1], k = 1
 * Output: 2
 * Explanation: The subarray [-1, 2] sums to 1 and is the longest.
 * Follow Up:
 * Can you do it in O(n) time?
 */
public class MaximumSizeSubarraySumEqualsK {

    public static void main(String[] args) {
        MaximumSizeSubarraySumEqualsK obj = new MaximumSizeSubarraySumEqualsK();
        System.out.println(obj.maxSubArrayLen(new int[]{1, -1, 5, -2, 3}, 3));
        System.out.println(obj.maxSubArrayLen(new int[]{-2, -1, 2, 1}, 1));
    }

    public int maxSubArrayLen(int[] nums, int k) {
        Map<Integer, Integer> cumulativeSumIndexMap = new HashMap<>();
        cumulativeSumIndexMap.put(0, -1); // Even before input start out cumulativeSum is 0
        int sum = 0;
        int maxLength = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            cumulativeSumIndexMap.putIfAbsent(sum, i);
            maxLength = Math.max(maxLength, i - cumulativeSumIndexMap.getOrDefault(sum - k, i));
        }
        return maxLength;
    }
}
