package com.geeksforgeeks.array;

import java.util.Arrays;

public class LongestIncreasingSubsequence {

    public static void main(String[] args) {
        int arr[] = {50,3,10,7,40,80};
        System.out.println(getLengthOfLis(arr));
    }

    public static int getLengthOfLis(int[] arr) {
        int[] LIS = new int[arr.length];

        // Initially every index has LIS = 1
        for (int i = 0; i < arr.length; i++) {
            LIS[i] = 1;
        }

        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {

                if (arr[i] > arr[j] && LIS[i] < LIS[j] + 1) {
                    LIS[i] = LIS[j] + 1;
                }
            }
        }

        System.out.println(LIS);
        Arrays.sort(LIS);
        return LIS[LIS.length - 1];
    }
}
