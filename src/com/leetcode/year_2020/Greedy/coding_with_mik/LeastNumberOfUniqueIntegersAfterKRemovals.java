package com.leetcode.year_2020.Greedy.coding_with_mik;

import java.util.*;

public class LeastNumberOfUniqueIntegersAfterKRemovals {

    public static void main(String[] args) {
        LeastNumberOfUniqueIntegersAfterKRemovals obj = new LeastNumberOfUniqueIntegersAfterKRemovals();
        System.out.println(obj.findLeastNumOfUniqueInts(new int[]{5, 5, 4}, 1));
        System.out.println(obj.findLeastNumOfUniqueInts(new int[]{4, 3, 1, 1, 3, 3, 2}, 3));
        System.out.println(obj.findLeastNumOfUniqueInts(new int[]{1, 1, 2, 2, 3, 3}, 3));
        System.out.println(obj.findLeastNumOfUniqueInts(new int[]{1, 1, 1, 4}, 3));

        System.out.println("------------ Using O(N) time complexity -------------------------");
        System.out.println(obj.findLeastNumOfUniqueIntsInO_N(new int[]{5, 5, 4}, 1));
        System.out.println(obj.findLeastNumOfUniqueIntsInO_N(new int[]{4, 3, 1, 1, 3, 3, 2}, 3));
        System.out.println(obj.findLeastNumOfUniqueIntsInO_N(new int[]{1, 1, 2, 2, 3, 3}, 3));
        System.out.println(obj.findLeastNumOfUniqueIntsInO_N(new int[]{1, 1, 1, 4}, 3));

    }

    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int j : arr) {
            freq.put(j, freq.getOrDefault(j, 0) + 1);
        }
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            minHeap.offer(entry.getValue());
        }

        while (!minHeap.isEmpty()) {
            k -= minHeap.peek(); // First just checking how much we can reduce, if it made k < 0 we simply exit without popping it
            if (k < 0) {
                return minHeap.size();
            }
            minHeap.poll();
        }
        return 0;
    }

    public int findLeastNumOfUniqueIntsInO_N(int[] arr, int k) {
        Map<Integer, Integer> frequencies = new HashMap<>();
        for (int j : arr) {
            frequencies.put(j, frequencies.getOrDefault(j, 0) + 1);
        }
        int[] freqToCount = new int[arr.length+1]; // If all elements of arr is same than that's the max freq anyone can have
        for (int freq : frequencies.values()) {
            freqToCount[freq] = freqToCount[freq] + 1;
        }
        int totalUniqueItems = frequencies.size(); // These many unique elements we have in total before deletion

        for (int freq = 0; freq < freqToCount.length; freq++) {
            if (freqToCount[freq] == 0) continue;
            // So assume a frequency is 4 and k is 12 how many unique elements of freq 4 can be deleted
            // that would be 12/4 == 3 so 3 elements whose freq is 4 can be deleted
            // similarly if there are less unique elements whose freq is the current freq, then we only delete that much
            int uniqueElementWhichCanBeRemoved = Math.min(k / freq, freqToCount[freq]);

            // So how much is left inside k now

            // Why multiply freq because if a freq is 4 and we found 3 unique elements can be removed but in actual there are 12 entries
            // to be deleted
            k = k - (uniqueElementWhichCanBeRemoved * freq);

            totalUniqueItems -= uniqueElementWhichCanBeRemoved; // these many from total unique got deleted
            if (k <= 0) break;
        }
        return totalUniqueItems;
    }
}
