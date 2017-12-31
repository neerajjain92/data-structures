package com.geeksforgeeks.array;

public class MergeSort {

    public static void main(String[] args) {
        int[] sample = { -12, 11, -13, -5, 6, -7, 5, -3, -6 };
        mergeSort(sample, 0, 8);
        System.out.println("After sorting is ");
        ArrayRotation.printArray(sample);
    }


    public static void mergeSort(int[] arr, int low, int high) {

        if (low >= high)
            return;

        int mid = low + (high - low) / 2;

        mergeSort(arr, low, mid);
        mergeSort(arr, mid + 1, high);
        merge(arr, low, mid, high);
    }

    private static void merge(int[] arr, int low, int mid, int high) {
        int t1 = low;
        int t2 = mid + 1;
        int[] sortedArray = new int[arr.length];
        int counter = 0;

        while (t1 <= mid && t2 <= high) {
            if (arr[t1] < arr[t2]) {
                sortedArray[counter++] = arr[t1];
                t1++;
            } else if (arr[t2] < arr[t1]) {
                sortedArray[counter++] = arr[t2];
                t2++;
            }
        }

        while (t1 <= mid) {
            sortedArray[counter++] = arr[t1++];
        }

        while (t2 <= high) {
            sortedArray[counter++] = arr[t2++];
        }
        counter = 0;
        for (int i = low; i <= high; i++) {
            arr[i] = sortedArray[counter++];
        }
    }
}
