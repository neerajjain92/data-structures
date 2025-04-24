package com.leetcode.year_2020.heap.coding_with_mik;


import java.util.PriorityQueue;

public class FindMedianfromDataStream {

    public static void main(String[] args) {

    }

    /**
     * Always rememeber the maxHeap on the left should always carry
     * 1 item greater than right, hence we always start from left
     */
    class MedianFinder {

        PriorityQueue<Integer> minHeap;
        PriorityQueue<Integer> maxHeap;
        int totalSize = 0;

        public MedianFinder() {
            minHeap = new PriorityQueue<>();
            maxHeap = new PriorityQueue<>((a, b) -> b - a);
        }

        public void addNum(int num) {
            totalSize++;
            if (maxHeap.isEmpty() || num < maxHeap.peek()) {
                maxHeap.add(num);
            } else {
                minHeap.add(num);
            }

            rebalanceHeaps();
        }

        private void rebalanceHeaps() {
            // We always want left heap to have 1 element more than right
            if (maxHeap.size() - minHeap.size() > 1) {
                minHeap.add(maxHeap.poll());
            } else if (maxHeap.size() < minHeap.size()) {
                maxHeap.add(minHeap.poll());
            }
        }

        public double findMedian() {
            if (totalSize % 2 == 0) {
                return 0.5 * (maxHeap.peek() + minHeap.peek());
            } else {
                return maxHeap.peek();
            }
        }
    }
}
