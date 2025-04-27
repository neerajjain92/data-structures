package com.leetcode.year_2020.heap.coding_with_mik;

import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/minimum-operations-to-exceed-threshold-value-ii/description/
 * 3066. Minimum Operations to Exceed Threshold Value II
 */
public class MinimumOperationsToExceedThresholdValueII {

    public static void main(String[] args) {
        MinimumOperationsToExceedThresholdValueII obj = new MinimumOperationsToExceedThresholdValueII();
        System.out.println(obj.minOperations(new int[]{2, 11, 10, 1, 3}, 10));
        System.out.println(obj.minOperations(new int[]{1, 1, 2, 4, 9}, 20));
    }

    public int minOperations(int[] nums, int k) {
        PriorityQueue<Long> minHeap = new PriorityQueue<>();
        for (int num : nums) {
            minHeap.add((long) num);
        }

        int operations = 0;
        while (minHeap.size() >= 2) {
            if (minHeap.peek() >= k) {
                // if the smallest element is greater than K so all else will anyways be greater
                break;
            }
            long firstMin = minHeap.poll();
            long secondMin = minHeap.poll();
            operations++;
            long newValue = (Math.min(firstMin, secondMin) * 2) + Math.max(firstMin, secondMin);
            minHeap.add(newValue);
        }
        return operations;
    }
}
