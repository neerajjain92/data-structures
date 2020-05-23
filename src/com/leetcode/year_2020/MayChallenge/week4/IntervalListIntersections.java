package com.leetcode.year_2020.MayChallenge.week4;

import com.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author neeraj on 23/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class IntervalListIntersections {

    public static void main(String[] args) {
        LogUtil.printMultiDimensionArray(intervalIntersection(new int[][]{
                {0, 2}, {5, 10}, {13, 23}, {24, 25}
        }, new int[][]{
                {1, 5}, {8, 12}, {15, 24}, {25, 26}
        }));

        LogUtil.printMultiDimensionArray(intervalIntersection(new int[][]{
                {10, 12}, {18, 19}
        }, new int[][]{
                {1, 6}, {8, 11}, {13, 17}, {19, 20}
        }));
    }

    public static int[][] intervalIntersection(int[][] A, int[][] B) {
        /**
         * We will keep 2 pointers. t1, t2... for A and B respectively.
         *
         */
        List<int[]> result = new ArrayList<>();
        int t1 = 0, t2 = 0;
        // we will run upto the time when both are available
        // since overlapping is only possible then
        while (t1 < A.length && t2 < B.length) {
            int maxStart = Math.max(A[t1][0], B[t2][0]);
            int minEnd = Math.min(A[t1][1], B[t2][1]);

            if (maxStart <= minEnd) { // overlap exist
                result.add(new int[]{maxStart, minEnd});
            }

            if (A[t1][1] < B[t2][1]) { // A is finishing before B.
                ++t1;
            } else { // B is finishing before A.
                ++t2;
            }
        }
        return result.toArray(new int[result.size()][]);
    }
}
