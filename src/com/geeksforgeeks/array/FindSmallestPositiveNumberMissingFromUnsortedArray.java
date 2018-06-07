package com.geeksforgeeks.array;

import java.util.Arrays;

import static java.lang.Math.abs;

public class FindSmallestPositiveNumberMissingFromUnsortedArray {

    public static void main(String[] args) {
        findSmallestPositiveUtil(new int[]{2, 3, 7, 6, 8, -1, -10, 15});
    }

    public static void findSmallestPositiveUtil(int[] arr) {
//        int positiveNumStart = SegregatePositiveAndNegativeNumbers.segregatePositiveAndNegativeNumbers(arr);
        int positiveNumStart = segregate(arr, arr.length);
        int[] onlyPositiveArr = Arrays.copyOfRange(arr, positiveNumStart, arr.length);

        int smallestPositiveNum = findSmallestPositiveUtil(onlyPositiveArr, onlyPositiveArr.length);
        System.out.println(smallestPositiveNum);
    }

    static int segregate(int arr[], int size) {
        int j = 0, i;
        for (i = 0; i < size; i++) {
            if (arr[i] <= 0) {
                int temp;
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                // increment count of non-positive
                // integers
                j++;
            }
        }

        return j;
    }

    // Method local to util
    private static int findSmallestPositiveUtil(int[] onlyPositiveArr, int length) {
        int smallestNum = length + 1;

        for (int i = 0; i < length; i++) {

            // If the index value is actually under the size of array
            // and the Value at the found index is positive then mark it negative
            // We are subtracting 1 from the calculation because array index start from 0
            // and we are assuming positive numbers start from 1 not from 0;
            if (abs(onlyPositiveArr[i]) - 1 < length && onlyPositiveArr[onlyPositiveArr[abs(i)] - 1] > 0) {
                onlyPositiveArr[onlyPositiveArr[abs(i)] - 1] = -onlyPositiveArr[onlyPositiveArr[abs(i)] - 1];
            }
        }

        // Now Simply traverse the array and check for 1st positive number and return the index+1
        // Why this logic works is because
        // We are checking for value in the array and if 1 is missing so no one will be able to mark 0th index as -ve
        for (int i = 0; i < length; i++) {
            if (onlyPositiveArr[abs(i)] > 0) {
                smallestNum = i + 1;
                break;
            }
        }
        return smallestNum;
    }
}
