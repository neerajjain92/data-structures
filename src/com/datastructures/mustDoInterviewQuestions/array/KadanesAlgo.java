package com.datastructures.mustDoInterviewQuestions.array;

/**
 * http://www.geeksforgeeks.org/must-do-coding-questions-for-companies-like-amazon-microsoft-adobe/#arrays
 * Created by jaine03 on 19/07/17.
 */
public class KadanesAlgo {

    public static void main(String[] args) {
        System.out.println(getLargestSubArraySum(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
        System.out.println(getLargestSubArraySumWithIndexes(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
        System.out.println(getLargestSubArraySumWithIndexes(new int[]{1, 2, 3, -2, 5}));
        System.out.println(getLargestSubArraySumWithIndexes(new int[]{4, -1, 2, 1}));
        System.out.println(getLargestSubArraySumWithIndexes(new int[]{-1, -2, -3, -4}));
        System.out.println(getLargestSubArraySumWithIndexes(new int[]{0, -3, 5, -1, -2}));
    }

    public static int getLargestSubArraySum(int[] arr) {
        int MAX_TILL_NOW = arr[0];
        int MAX_END_HERE = arr[0];

        for (int i = 1; i < arr.length; i++) { // This only runs till length/2 find out why ?
            MAX_END_HERE = Math.max(arr[i], MAX_END_HERE + arr[i]);
            MAX_TILL_NOW = Math.max(MAX_TILL_NOW, MAX_END_HERE);
        }
        return MAX_TILL_NOW;
    }

    public static int getLargestSubArraySumWithIndexes(int[] arr) {
        int MAX_TILL_NOW = arr[0];
        int MAX_END_HERE = arr[0];
        int startIndex = 0;
        int endIndex = 0;
        int tempStart = 0;

        for (int i = 1; i < arr.length; i++) { // This only runs till length/2 find out why ?
            if (MAX_END_HERE + arr[i] < arr[i]) {
                MAX_END_HERE = arr[i];
                tempStart = i;
            } else {
                MAX_END_HERE += arr[i];
            }
            if (MAX_TILL_NOW < MAX_END_HERE) {
                startIndex = tempStart;
                endIndex = i;
                MAX_TILL_NOW = MAX_END_HERE;
            }
        }
        System.out.println("Indexes ==>  " + startIndex + "::" + endIndex);
        return MAX_TILL_NOW;
    }
}
