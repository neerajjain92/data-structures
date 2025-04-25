package com.leetcode.year_2020.heap.coding_with_mik;

import java.util.PriorityQueue;

public class TotalCosToHireKWorkers {

    public static void main(String[] args) {
        TotalCosToHireKWorkers obj = new TotalCosToHireKWorkers();
        System.out.println(obj.totalCost(new int[]{17, 12, 10, 2, 7, 2, 11, 20, 8}, 3, 4));
        System.out.println(obj.totalCost(new int[]{1, 2, 4, 1}, 3, 3));
        System.out.println(obj.totalCost(new int[]{35, 12, 7, 25, 30, 5, 20, 40, 9, 13, 6, 15, 22, 28, 11, 8, 10, 17}, 7, 4));
    }

    public long totalCost(int[] costs, int k, int candidates) {
        PriorityQueue<Integer> left_minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> right_minHeap = new PriorityQueue<>();
        int n = costs.length;
        int i = 0, j = n - 1;

        // fill left heap upto 'candidates' elements
        while (i < n && left_minHeap.size() < candidates) {
            left_minHeap.offer(costs[i++]);
        }

        // fill right heap upto 'candidates' elements
        while (j >= i && right_minHeap.size() < candidates) {
            right_minHeap.offer(costs[j--]);
        }

        long totalCost = 0;
        while (k-- > 0) {
            int left = left_minHeap.isEmpty() ? Integer.MAX_VALUE : left_minHeap.peek();
            int right = right_minHeap.isEmpty() ? Integer.MAX_VALUE : right_minHeap.peek();

            if (left <= right) {
                totalCost += left_minHeap.poll();
                if (i <= j) { // if we can still add something from costs array then add it
                    left_minHeap.offer(costs[i++]);
                }
            } else {
                totalCost += right_minHeap.poll();
                if (i <= j) {
                    right_minHeap.offer(costs[j--]);
                }
            }
        }
        return totalCost;

    }
}
