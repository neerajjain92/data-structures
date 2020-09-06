package com.leetcode.year_2020.august_challenge;

import com.util.LogUtil;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * @author neeraj on 27/08/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class FindRightInterval {

    public static void main(String[] args) {
        LogUtil.printArray(findRightInterval(new int[][]{
                {3, 4},
                {2, 3},
                {1, 2}
        }));

        LogUtil.printArray(findRightInterval(new int[][]{
                {1, 2}
        }));
    }

    public static int[] findRightInterval(int[][] intervals) {
        Map<Integer, Integer> positionMap = new HashMap<>();
        int counter = 0;
        for (int[] item : intervals) {
            positionMap.put(item[0], counter++);
        }

        // Sort on start time
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        int[] result = new int[intervals.length];
        Arrays.fill(result, -1);

        for (int[] interval : intervals) {
            int position = binarySearch(intervals, interval, positionMap);
            result[positionMap.get(interval[0])] = position;
        }
        return result;
    }

    public static int binarySearch(int[][] intervals,
                                   int[] entry,
                                   Map<Integer, Integer> positionMap) {
        int index = -1;
        int low = 0;
        int high = intervals.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (intervals[mid][0] >= entry[1]) {
                index = positionMap.get(intervals[mid][0]);
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return index;
    }
}
