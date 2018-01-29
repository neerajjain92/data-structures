package com.geeksforgeeks.array;

public class MaxSumContiguousSubArray {
    public static void main(String[] args) {
        getMaximumSumContiguousSubArray(new int[]{-2, -3, 4, -1, -2, 1, 5, -3});
        getMaximumSumContiguousSubArray(new int[]{-4, -1, -10, -5});
    }

    public static int getMaximumSumContiguousSubArray(int[] arr) {

        int MAX_ENDING_HERE = 0;
        int MAX_TILL_NOW = arr[0];
        int start = 0, end = 0, s = 0;

        for (int i = 0; i < arr.length; i++) {
            MAX_ENDING_HERE += arr[i];

            if (MAX_TILL_NOW < MAX_ENDING_HERE) {
                MAX_TILL_NOW = MAX_ENDING_HERE;
                start = s;
                end = i;
            }

            if (MAX_ENDING_HERE < 0) {
                MAX_ENDING_HERE = 0;
                s = i + 1; // Since at i MAX_ENDING_HERE becomes 0 so i definitely can not be the start of maximum sum subarray
            }
        }
        System.out.println("=========================================================================");
        ArrayRotation.printArray(arr);
        System.out.println("Maximum contiguous sum is " + MAX_TILL_NOW);
        System.out.println(" Starts at " + (start + 1) + " and ends at " + (end + 1));
        return MAX_TILL_NOW;
    }
}
