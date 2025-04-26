package com.leetcode.year_2020.heap.coding_with_mik;

import java.util.PriorityQueue;

public class RangeSumOfSortedSubArraySum {

    public static void main(String[] args) {
        RangeSumOfSortedSubArraySum rs = new RangeSumOfSortedSubArraySum();

        System.out.println(rs.rangeSum(new int[]{1, 2, 3, 4}, 4, 1, 5));
    }

    /*
     * The best intuition, Using Heap you can get sum of all subarray of Array
     * if Minheap it's ascending sum, and MaxHeap it's descending
     *
     * Now let's see how [1, 2, 3, 4]
     * We know individual elements are always subarrays
     * so push them in the MinHeap, and also we store a tuple with the sum and the index at which we found sum
     *
     * I/P --> [1  2  3  4]
     *          0  1  2  3
     *
     * | [1, 0] |  =======> Min Sum on Top of MinHeap
     * | [2, 1] |
     * | [3, 2] |
     * | [4, 3] |
     * |--------|
     *
     * Pop the minSum and try to expand it's index with just next item, that way now you have
     * sum of subArray [1,2]
     *         Index    0 1
     * So we push [1 + 2, 1] ==> [3, 1] 3 is sum of subArray[1,2] and ending at index 1
     *
     * | [2, 1] | ===> Min Sum on Top of MinHeap
     * | [3, 1] |
     * | [3, 2] |
     * | [4, 3] |
     * |--------|
     * See how it's just O(N) to get subArray sum of all, Now keep repeating till your entire minHeap is empty
     */
    public int rangeSum(int[] nums, int n, int left, int right) {
        final int MOD = 1000000007;  // Prime number to avoid overflow
        PriorityQueue<RangeSumTuple> minHeap = new PriorityQueue<>((a, b) -> {
            if (Double.compare(a.sum, b.sum) == 0) {
                return a.indexEndingAt - b.indexEndingAt;
            } else {
                return Double.compare(a.sum, b.sum);
            }
        });
        for (int i = 0; i < n; i++) {
            minHeap.add(new RangeSumTuple(nums[i], i));
        }

        long totalSum = 0;
        for (int count = 1; count <= right; count++) {
            RangeSumTuple tuple = minHeap.poll();
            if (tuple.indexEndingAt + 1 < n) {
                int nextIndex = tuple.indexEndingAt + 1;
                minHeap.add(new RangeSumTuple(tuple.sum + nums[nextIndex], nextIndex));
            }
            if (count >= left) {
                totalSum = (totalSum + tuple.sum) % MOD;
            }
        }
        return (int) totalSum;
    }

    static class RangeSumTuple {
        long sum;
        int indexEndingAt;

        public RangeSumTuple(long sum, int indexEndingAt) {
            this.sum = sum;
            this.indexEndingAt = indexEndingAt;
        }
    }

}
