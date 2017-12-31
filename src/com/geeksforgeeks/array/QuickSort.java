package com.geeksforgeeks.array;

public class QuickSort {

    public static void main(String[] args) {
        int arr[] = {10, 80, 30, 90, 40, 50, 70};
        quickSort(arr, 0, 6);
        ArrayRotation.printArray(arr);
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {

            int p = partition(arr, low, high);
            quickSort(arr, low, p - 1);
            quickSort(arr, p + 1, high);
        }
    }

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];

        int j = low - 1;
        for (int i = low; i < high; i++) {
            if (arr[i] < pivot) {
                j++;
                swap(arr, j, i);
            }
        }
        swap(arr, j + 1, high);
        return j + 1;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}