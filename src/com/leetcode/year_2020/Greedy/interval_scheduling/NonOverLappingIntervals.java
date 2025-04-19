package com.leetcode.year_2020.Greedy.interval_scheduling;

import java.util.Arrays;
import java.util.Comparator;

/**
 * https://leetcode.com/problems/non-overlapping-intervals/
 * https://www.youtube.com/watch?v=HDHQ8lAWakY&list=PLgUwDviBIf0rF1w2Koyh78zafB0cz7tea&index=8
 * <p>
 * Given a collection of intervals, find the minimum number of intervals you need to remove
 * to make the rest of the intervals non-overlapping.
 *
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
        System.out.println(eraseOverlapIntervals(new int[][]{
                {10, 16}, {2, 8}, {1, 6}, {7, 12}
        }));

        System.out.println("Now new varian ----------------------->");
        // New variant with Max meetings in single room analogy

        System.out.println(eraseOverlappingIntervals(new int[][]{
                {1, 2}, {2, 3}, {3, 4}, {1, 3}
        }));

        System.out.println(eraseOverlappingIntervals(new int[][]{
                {1, 2}, {1, 2}, {1, 2}
        }));
        System.out.println(eraseOverlappingIntervals(new int[][]{
                {1, 2}, {2, 3}
        }));
        System.out.println(eraseOverlappingIntervals(new int[][]{
                {1, 100}, {11, 22}, {1, 11}, {2, 12}
        }));

        System.out.println(eraseOverlapIntervals(new int[][]{
                {10, 16}, {2, 8}, {1, 6}, {7, 12}
        }));
    }

    public static int eraseOverlappingIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        /*
         * This is similar to the problem of finishing as many meetings as possible by using only a single meeting room
         * in that also we first try to find out which meeting will finish faster and then keep the latest endTime of a meeting
         * in check as soon as we find some meeting clashing with that we couldn't let that meeting happen and once we get
         * some meeting whose start > currentEnd time that's when we used to give that to that person
         * here also do  the same once you have all the meetings count which overlapped, total - count will
         * give you non over lapping interval
         */
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1])); // Sorting by finish time first
        int maxEnd = intervals[0][1];
        int conflicts = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < maxEnd) {
                conflicts++;
            } else {
                maxEnd = Math.max(maxEnd, intervals[i][1]);
            }
        }
        return conflicts;
    }

    public static int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) return 0;
        /*
         * This is similar to the problem of finishing as many meetings as possible by using only a single meeting room
         * in that also we first try to find out which meeting will finish faster and then keep the latest endTime of a meeting
         * in check as soon as we find some meeting clashing with that we couldn't let that meeting happen and once we get
         * some meeting whose start > currentEnd time that's when we used to give that to that person
         * here also do  the same once you have all the meetings count which overlapped, total - count will
         * give you non over lapping interval
         *
         * This problem is the variation of {@link MergeIntervals}
         * In this problem we are asked to remove minimum number of interval
         * to make the whole intervals non-overlapping.
         *
         * {1, 2}, {2, 3}, {3, 4}, {1, 3}
         *
         * We have to just remove 1 interval which is {1,3}. to make it non-overlapping.
         *
         * 1. We have to sort the intervals based on their Finish Time in Ascending Order....
         *    Reason: Intervals with earliest endTime gives us more space to accommodate extra intervals, v/s if we choose the interval
         *    with End Time as largest we will have no space to accommodate more intervals.
         * 2. Here also we will take baseInterval and do the comparision with other intervals. Now in this variation we have to reject
         *    the overlapping interval.
         *    So if(currInterval[startTime] < baseInterval[endTime]) currentInterval has to be rejected....
         *    we will only consider those interval where currInterval[start] > baseInterval.
         */
        Arrays.sort(intervals, Comparator.comparingInt(A -> A[1]));
        int[] baseInterval = intervals[0];
        int totalNonOverlappingInterval = 1;

        for (int i = 1; i < intervals.length; i++) {
            int[] currentInterval = intervals[i];

            // If currentInterval start > baseInterval[end])
            if (currentInterval[0] >= baseInterval[1]) {
                // Here we are extending the baseInterval.
                baseInterval[1] = Math.max(currentInterval[1], baseInterval[1]);
                totalNonOverlappingInterval++;
            }
        }
        // intervals.length - totalNonOverlappingInterval; == Total Overlapping Intervals.
        return intervals.length - totalNonOverlappingInterval;
    }
}
