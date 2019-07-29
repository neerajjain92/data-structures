package com.interviewbit.binary_search;

import static com.util.LogUtil.getArrayAsString;
import static com.util.LogUtil.logIt;

/**
 * https://www.interviewbit.com/problems/rotated-array/
 * <p>
 * Suppose a sorted array A is rotated at some pivot unknown to you beforehand.
 * <p>
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * <p>
 * Find the minimum element.
 * <p>
 * The array will not contain duplicates.
 *
 * @author neeraj on 2019-07-29
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class FindMinimumElementInRotatedArray {

    public static void main(String[] args) {
        int[] arr = new int[]{4, 5, 6, 7, 0, 1, 2};
        logIt("Minimum number in " + getArrayAsString(arr) + " is  " + findMinimumNumber(arr, 0, arr.length - 1));

        arr = new int[]{4, 5, 6, 7, 8, 1, 2};
        logIt("Minimum number in " + getArrayAsString(arr) + " is  " + findMinimumNumber(arr, 0, arr.length - 1));

        arr = new int[]{4, 5, 6, 7, 8, 9, 2};
        logIt("Minimum number in " + getArrayAsString(arr) + " is  " + findMinimumNumber(arr, 0, arr.length - 1));

        arr = new int[]{5, 1, 2, 3, 4};
        logIt("Minimum number in " + getArrayAsString(arr) + " is  " + findMinimumNumber(arr, 0, arr.length - 1));

        arr = new int[]{5137, 5525, 9511, 13269, 16255, 16700, 19870, 23034, 29247, 29934, 34583, 41585, 42598, 44113, 46035, 50147, 50737, 57084, 65916, 76905, 84098, 85912, 92081, 92257, 95449};
        logIt("Minimum number in " + getArrayAsString(arr) + " is  " + findMinimumNumber(arr, 0, arr.length - 1));
    }

    public static int findMinimumNumber(int[] arr, int low, int high) {
        if (low <= high) {
            if (arr[low] <= arr[high]) { // Case 1
                return arr[low];
            }
            int mid = low + (high - low) / 2;

            // We are taking modulo because the minimum element can be just on the last element itself.
            // So next of it will give stack overflow if not handled properly
            int nextOfMid = (mid + 1) % arr.length;
            int prevOfMid = (mid - 1) % arr.length;

            if (arr[mid] < arr[nextOfMid] && arr[mid] < arr[prevOfMid]) {
                return arr[mid];
            } else if (arr[mid] <= arr[high]) { // Element in middle is less than last element in right side
                // hence right side is perfectly sorted
                // So minimum element is in the left side
                return findMinimumNumber(arr, low, prevOfMid);
            } else if (arr[mid] >= arr[low]) { // Middle element is greater,
                // so if rotation has happened then this can only be possible
                // if the minimum element is in the right side of array
                return findMinimumNumber(arr, nextOfMid, high);
            }
        }
        return -1;
    }
}
