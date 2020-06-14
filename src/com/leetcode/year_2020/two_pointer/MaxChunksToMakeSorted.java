package com.leetcode.year_2020.two_pointer;

import java.util.Arrays;

/**
 * @author neeraj on 15/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MaxChunksToMakeSorted {

    public static void main(String[] args) {

    }

    public static int maxChunksToSorted(int[] arr) {
        /**
         * We need to find the max Value of any chunk
         * if the maxValue of the chunk falls exactly on the same index as it would have if i sort the original array
         * congo we found a chunk
         *
         * Why? Okay so whenever we make a cut to divide in chunk, we are saying that all the values before this cut
         * should be small and sorted.
         * Now when we take the max of a chunk.... it should come in the last of the chunk once that individual chunk is sorted.
         * So whenever that last Index is same as the original sorted array index. so that means this is a perfect chunk
         * and we found a chunk which when sorted will result in similar to if we have sorted the original array.
         *
         * Example :
         * I/p     :  0, 2, 1, 4, 3, 5, 7, 6
         * max     :  0, 2, 2, 4, 4, 5, 7, 7  [ We calculate maximum value in left at any index.]
         * Sorted  :  0, 1, 2, 3, 4, 5, 6, 7
         *
         * Now if we notice these chunks...... the max value of any valid chunk is falling exactly on the sorted index.
         * [0] [2 1] [4 3] [5] [7 6]
         *
         */
        int[] leftMax = new int[arr.length];
        leftMax[0] = arr[0];

        for (int i = 1; i < arr.length; i++) {
            leftMax[i] = Math.max(arr[i - 1], arr[i]); // maintaining left max;
        }

        Arrays.sort(arr);
        int chunkCount = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == leftMax[i]) chunkCount++;
        }
        return chunkCount;
    }
}
