package com.leetcode.year_2020.heap.coding_with_mik;

import com.util.LogUtil;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class SmallestRangeCoveringElementsfromKLists {

    public static void main(String[] args) {
        SmallestRangeCoveringElementsfromKLists obj = new SmallestRangeCoveringElementsfromKLists();
        LogUtil.printArray(obj.smallestRange(List.of(List.of(4, 10, 15, 24, 26), List.of(0, 9, 12, 20), List.of(5, 18, 22, 30))));
    }

    /**
     * One single image will solve this for you and it's not fucking hard then
     * before that remember sort k sorted list by MinHeap that's it that's what you have to do here
     * <p>
     * Every iteration find min which is easy bro because MinHeap
     * <p>
     * [4,10,15,24,26],[0,9,12,20],[5,18,22,30] ===>  0  5
     * [4,10,15,24,26],[9,12,20],[5,18,22,30]   ===>  4  9
     * [10,15,24,26],[9,12,20],[5, 18,22,30]    ===>  5 10
     * [10,15,24,26],[9,12,20],[18,22,30]       ===>  9 18
     * [10,15,24,26],[12,20],[18,22,30]         ===>  10 18
     * [15,24,26],[12,20],[18,22,30]            ===>  12 18
     * [15,24,26],[20],[18,22,30]               ===>  15 20
     * [24,26],[20],[18,22,30]                  ===>  18 24
     * [24,26],[20],[22,30]                     ===>  20 24
     * [24,26],[],[22,30]                       ===>  Stop
     * <p>
     * Min you can  easily find out how the fuck you find out about the max-bro
     * This is also easy bro, in the first time we get from 1st element from all
     * and then when you are pushing just check if your new is contributing to be the maximum
     */
    public int[] smallestRange(List<List<Integer>> nums) {
        PriorityQueue<SmallestRangeTuple> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.item));
        int rangeEnd = Integer.MIN_VALUE;
        for (int i = 0; i < nums.size(); i++) {
            List<Integer> list = nums.get(i);
            minHeap.add(new SmallestRangeTuple(list.get(0), 0, i));
            rangeEnd = Math.max(nums.get(i).get(0), rangeEnd);
        }

        // Now MinHeap has smallest in all
        int minDiff = Integer.MAX_VALUE;
        int bestStart = 0, bestEnd = 0;
        while (!minHeap.isEmpty()) {
            SmallestRangeTuple tuple = minHeap.poll();
            if (rangeEnd - tuple.item < minDiff) {
                minDiff = rangeEnd - tuple.item;
                bestStart = tuple.item;
                bestEnd = rangeEnd;
            }
            // Now push next, if next is not present that's your final range
            if (tuple.indexInList + 1 < nums.get(tuple.listIndex).size()) {
                // So we can push next item
                int nextItem = nums.get(tuple.listIndex).get(tuple.indexInList + 1);
                if (nextItem > rangeEnd) {
                    rangeEnd = nextItem;
                }
                minHeap.add(new SmallestRangeTuple(nextItem, tuple.indexInList + 1, tuple.listIndex));
            } else {
                break; // We can't continue
            }
        }
        return new int[]{bestStart, bestEnd};
    }

    static class SmallestRangeTuple {
        int item, indexInList, listIndex;

        public SmallestRangeTuple(int item, int indexInList, int listIndex) {
            this.item = item;
            this.indexInList = indexInList;
            this.listIndex = listIndex;
        }
    }
}
