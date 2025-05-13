package com.leetcode.year_2020.sliding_window.codestorywithmik;

import java.util.HashMap;
import java.util.Map;

public class LengthofLongestSubarrayWithAtMostKFrequency {

    public static void main(String[] args) {
        LengthofLongestSubarrayWithAtMostKFrequency obj = new LengthofLongestSubarrayWithAtMostKFrequency();
        System.out.println(obj.maxSubarrayLength(new int[]{1, 2, 3, 1, 2, 3, 1, 2}, 2));
        System.out.println(obj.maxSubarrayLength(new int[]{1, 2, 1, 2, 1, 2, 1, 2}, 1));
        System.out.println(obj.maxSubarrayLength(new int[]{5, 5, 5, 5, 5, 5, 5}, 4));
    }

    /**
     * Standard Slinding window problem
     */
    public int maxSubarrayLength(int[] nums, int k) {
        Map<Integer, Integer> numFreq = new HashMap<>();
        int begin = 0, end = 0, maxLength = 0;
        int n = nums.length;
        while (end < n) {
            numFreq.put(nums[end], numFreq.getOrDefault(nums[end], 0) + 1);
            while (numFreq.get(nums[end]) > k) {
                numFreq.put(nums[begin], numFreq.get(nums[begin]) - 1);
                if (numFreq.get(nums[begin]) <= 0) {
                    numFreq.remove(nums[begin]);
                }
                begin++;
            }
            maxLength = Math.max(maxLength, end - begin + 1);
            end++;
        }
        return maxLength;
    }
}
