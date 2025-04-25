package com.leetcode.year_2020.heap.coding_with_mik;

import java.util.*;

public class Leetcode621_TaskScheduler_Leetcode621 {

    public static void main(String[] args) {
        Leetcode621_TaskScheduler_Leetcode621 solution = new Leetcode621_TaskScheduler_Leetcode621();
        System.out.println(solution.leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 2));
        System.out.println(solution.leastInterval(new char[]{'A', 'C', 'A', 'B', 'D', 'B'}, 1));
        System.out.println(solution.leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 3));
    }

    public int leastInterval(char[] tasks, int gap) {
        Map<Character, Integer> taskFreq = new HashMap<>();
        for (char c : tasks) {
            taskFreq.put(c, taskFreq.getOrDefault(c, 0) + 1);
        }

        // First pull highest frequency tasks and if the frequency is same then follow lexographic sorting
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        maxHeap.addAll(taskFreq.values());

        int count = 0;
        while (!maxHeap.isEmpty()) {
            /*
             * Intuition imagine you have tasks like this
             * A C A B D B A A A A
             *
             * Symbol - Freq
             *   A    -  6
             *   B    -  2
             *   C    -  1
             *   D    -  1
             *
             * We know one thing we want to finish the item with highest frequency
             * first so that in the end we don't have more CPU idle time
             *
             * Now notice one crucial point, you pick first A, now how much time you have to wait
             * before you can pick A again, well that would be 'n' so if n = 2
             * then if you picked A first you can pick A only after picking 2 more items after A
             * to let constraint meet.
             *
             * A B C A
             * 0 1 2 3
             *
             * So how about if we pop total of gap+1 items at once from Heap that way
             * we can cut-short lot of loops
             */
            int K = gap + 1;
            List<Integer> temporaryParking = new ArrayList<>();
            while (K > 0 && !maxHeap.isEmpty()) {
                int freq = maxHeap.poll();
                freq--;
                count++; // we executed this task
                K--;
                temporaryParking.add(freq);
            }

            // Checking if the K task we popped still have any frequency left
            for (Integer freqInTempParking : temporaryParking) {
                if (freqInTempParking > 0) {
                    maxHeap.add(freqInTempParking);
                }
            }

            // All task processed and nothing got added from tempStorage, so all frequencies gone
            // Assume you had A, B, C and gap was 2
            // so you popped 2+1 total 3 items
            // and nothing left
            if (maxHeap.isEmpty()) {
                break;
            }

            // if some K is left, means we found less task than the amount of gapTime
            // so we should wait for that much time
            count += K;
        }
        return count;
    }
}
