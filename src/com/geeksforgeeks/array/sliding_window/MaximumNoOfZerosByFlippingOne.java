package com.geeksforgeeks.array.sliding_window;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Given a binary array, find the maximum number zeros in an array with one flip of a subarray allowed.
 * A flip operation switches all 0s to 1s and 1s to 0s.
 */
public class MaximumNoOfZerosByFlippingOne {

    public static void main(String[] args) {
        findMaximumNumOfZerosPossibleByFlippingOne(new Integer[]{0, 1, 0, 0, 1, 1, 0});
        findMaximumNumOfZerosPossibleByFlippingOne(new Integer[]{0, 0, 0, 1, 0, 1});
        findMaximumNumOfZerosPossibleByFlippingOne(new Integer[]{1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1});
    }

    public static void findMaximumNumOfZerosPossibleByFlippingOne(Integer[] arr) {
        // Get the frequency
        Map<Integer, Long> freqMap = Stream.of(arr)
                .collect(Collectors.groupingBy(o -> o, Collectors.counting()));

        getMaximumZeros(arr, freqMap);
    }

    public static long getMaximumZeros(Integer[] arr, Map<Integer, Long> freqMap) {
        long MAX_NUM_ZEROS = freqMap.get(0);
        int wL = 0, wR = 0;
        int NUM_ZEROS_IN_CURRENT_SUB_ARR = 0;
        int start = 0, end = 0;

        while (wR < arr.length) {
            if (arr[wR] == 0) {
                wR++;
                wL = wR;
            } else {
                NUM_ZEROS_IN_CURRENT_SUB_ARR = 0;
                while (wR < arr.length && arr[wR] != 0) {
                    NUM_ZEROS_IN_CURRENT_SUB_ARR++;
                    wR++;
                }
                if (MAX_NUM_ZEROS < freqMap.get(0) + NUM_ZEROS_IN_CURRENT_SUB_ARR) {
                    MAX_NUM_ZEROS = freqMap.get(0) + NUM_ZEROS_IN_CURRENT_SUB_ARR;
                    start = wL;
                    end = wR - 1;
                }
            }
        }
        System.out.println("Maximum Zeros By Flipping :: " + MAX_NUM_ZEROS + " which start and ends at [" + start + "," + end + "]");
        return MAX_NUM_ZEROS;
    }
}
