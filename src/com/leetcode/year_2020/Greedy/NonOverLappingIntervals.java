package com.leetcode.year_2020.Greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author neeraj on 16/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class NonOverLappingIntervals {

    public static void main(String[] args) {
        System.out.println(eraseOverlapIntervals(new int[][]{
                {1, 2}, {2, 3}, {3, 4}, {1, 3}
        }));

        System.out.println(eraseOverlapIntervals(new int[][]{
                {1, 2}, {1, 2}, {1, 2}
        }));
        System.out.println(eraseOverlapIntervals(new int[][]{
                {1, 2}, {2, 3}
        }));
        System.out.println(eraseOverlapIntervals(new int[][]{
                {1, 100}, {11, 22}, {1, 11}, {2, 12}
        }));
    }

    public static int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }

        Arrays.sort(intervals, Comparator.comparingInt(A -> A[0]));

        int endOfActiveInterval = intervals[0][1];
        int totalIntervalsInNonoverlappingSet = 1;

        for (int i = 1; i < intervals.length; i++) {
            int[] interval = intervals[i];

            int intervalStart = interval[0];
            int intervalEnd = interval[1];

            if (intervalStart >= endOfActiveInterval) {
                endOfActiveInterval = intervalEnd;
                totalIntervalsInNonoverlappingSet++;
            }
        }

        return intervals.length - totalIntervalsInNonoverlappingSet;
    }

    public static int eraseOverlapIntervals1(int[][] intervals) {
        if (intervals.length == 0) return 0;
        // Sorting Intervals based on their start time,
        // so that all nearby intervals comes together.
        Arrays.sort(intervals, Comparator.comparingInt(A -> A[0]));

        // This heap will maintain all intervals and the first finishing interval will be on top.
        // which will be helpful to figure out any overlaps
        PriorityQueue<Integer> minHeapOfEndTimeInInterval = new PriorityQueue<>((a, b) -> b - a);
        minHeapOfEndTimeInInterval.add(intervals[0][1]);

        for (int i = 1; i < intervals.length; i++) {
            int poppedIntervalEndTime = minHeapOfEndTimeInInterval.poll();
            int[] currentInterval = intervals[i];

            if (currentInterval[0] >= poppedIntervalEndTime) {
                // If currentInterval is starting only after previousInterval is finished
                // then we are good with this currentInterval.
                minHeapOfEndTimeInInterval.add(currentInterval[1]);
            } else {
                // If PreviousInterval is larger than this interval, then it's better
                // to reduce previousInterval's end time with currentEnd's time to accommodate new intervals.
                /**
                 * Example:
                 * [[1,100],[11,22],[1,11],[2,12]]
                 *
                 * After Sorting based on start time
                 * [[1,100],[1,11],[2,12],[11,22]]
                 *
                 * Now if we don't reduce the time from 100 to 11, [2,12] and [11,22] will never be accommodated.
                 * and based on Question we have to
                 *  find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
                 *
                 *  "Minimum Number of Intervals" is the key here.
                 */
                if (currentInterval[1] < poppedIntervalEndTime) {
                    poppedIntervalEndTime = currentInterval[1];
                }
            }
            minHeapOfEndTimeInInterval.add(poppedIntervalEndTime);
        }
        // Finally we will return all overlapping intervals
        // which will be only those from original intervals which didn't make it to minHeap.
        return intervals.length - minHeapOfEndTimeInInterval.size();
    }
}
