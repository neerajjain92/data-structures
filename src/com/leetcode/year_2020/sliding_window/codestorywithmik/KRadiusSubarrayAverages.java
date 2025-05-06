package com.leetcode.year_2020.sliding_window.codestorywithmik;

import com.util.LogUtil;

import java.util.Arrays;

public class KRadiusSubarrayAverages {

    public static void main(String[] args) {
        KRadiusSubarrayAverages k = new KRadiusSubarrayAverages();
        LogUtil.printArray(k.getAverages(new int[]{7, 4, 3, 9, 1, 8, 5, 2, 6}, 3));
        LogUtil.printArray(k.getAverages(new int[]{100000}, 0));

        LogUtil.printArray(k.getAverages_UsingSlidingWindow(new int[]{7, 4, 3, 9, 1, 8, 5, 2, 6}, 3));
        LogUtil.printArray(k.getAverages_UsingSlidingWindow(new int[]{100000}, 0));
    }

    public int[] getAverages_UsingSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (k == 0) return nums;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        if (n < (2 * k + 1)) return res; // if our total items is less than the window we can expand in left and right

        int left = 0, right = 2 * k, i = k;
        int windowSize = 2 * k + 1;
        long windowSum = findSum(nums, left, right);
        long avg = windowSum / (windowSize);
        res[i] = (int) avg;
        // Increment i and right to calculate the next
        i++;
        right++;
        while (right < n) {
            // Since we are expanding in right and shrinking from left
            // basicalling rolling the sliding window
            int newItem = nums[right];
            int goneItem = nums[left];
            windowSum = windowSum + newItem - goneItem;
            res[i] = (int) (windowSum / windowSize);
            i++;
            left++;
            right++;
        }
        return res;
    }

    private long findSum(int[] nums, int left, int right) {
        long sum = 0;
        for (int i = left; i <= right; i++) {
            sum += nums[i];
        }
        return sum;
    }

    public int[] getAverages(int[] nums, int k) {
        int[] result = new int[nums.length];
        int n = nums.length;
        long[] prefixSum = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }

        for (int i = 0; i < nums.length; i++) {
            if (i - k >= 0 && i + k < n) {
                long sumAroundRadius = prefixSum[i + k + 1] - prefixSum[i - k];
                long average = sumAroundRadius / (2L * k + 1);
                result[i] = (int) average;
            } else {
                result[i] = -1;
            }
        }
        return result;
    }
}
