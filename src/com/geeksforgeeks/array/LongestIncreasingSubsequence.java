package com.geeksforgeeks.array;

import java.util.Arrays;

public class LongestIncreasingSubsequence {

    public static void main(String[] args) {
        int arr[] = {50, 3, 10, 7, 40, 80};
        System.out.println(getLengthOfLis(arr));
    }

    public static int getLengthOfLis(int[] arr) {
        int[] LIS = new int[arr.length];
        int[] actualSequence = new int[arr.length];

        // Initially every index has LIS = 1
        for (int i = 0; i < arr.length; i++) {
            LIS[i] = 1;
            actualSequence[i] = i;
        }

        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {

                if (arr[i] > arr[j] && LIS[i] < LIS[j] + 1) {
                    LIS[i] = LIS[j] + 1;
                    actualSequence[i] = j;
                }
            }
        }

        // Find the index of maximum LIS
        int max = Integer.MIN_VALUE;
        int maxIndex = 0;
        for (int i = 0; i < LIS.length; i++) {
            if (max < LIS[i]) {
                max = LIS[i];
                maxIndex = i;
            }
        }

        // Now Let's Print Out the Actual Sequence which was involved in LIS
        int indexOne;
        int indexTwo = maxIndex;

        do {
            indexOne = indexTwo;
            System.out.print(arr[indexOne]+" ");
            indexTwo = actualSequence[indexOne];
        }while (indexOne != indexTwo);
        System.out.println();

        return max;
    }
}
