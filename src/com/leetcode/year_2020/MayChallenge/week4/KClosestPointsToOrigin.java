package com.leetcode.year_2020.MayChallenge.week4;

import com.util.LogUtil;

import java.util.PriorityQueue;

/**
 * @author neeraj on 30/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class KClosestPointsToOrigin {

    public static void main(String[] args) {
        LogUtil.printMultiDimensionArray(kClosest(new int[][]{
                {3, 3},
                {5, -1},
                {-2, 4}
        }, 2));
    }

    public static int[][] kClosest(int[][] points, int K) {
        if (points == null || K > points.length || points[0].length == 0) return new int[][]{};
        // Since we need k closest hence we choose MaxHeap so that when we have to kick-out some point
        // we kick farthest point first.
        PriorityQueue<int[]> maxHeapBasedOnDistance = new PriorityQueue<>((a, b) -> euclideanDistanceFromOrigin(b) -
                euclideanDistanceFromOrigin(a));

        for (int[] point : points) {
            maxHeapBasedOnDistance.add(point);
            if (maxHeapBasedOnDistance.size() > K) {
                maxHeapBasedOnDistance.poll();
            }
        }

        int[][] kClosest = new int[maxHeapBasedOnDistance.size()][2];
        int i = 0;
        while (!maxHeapBasedOnDistance.isEmpty()) {
            kClosest[i++] = maxHeapBasedOnDistance.poll();
        }
        return kClosest;
    }

    private static int euclideanDistanceFromOrigin(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }
}
