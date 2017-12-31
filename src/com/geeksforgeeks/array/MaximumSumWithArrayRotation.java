package com.geeksforgeeks.array;

public class MaximumSumWithArrayRotation {

    public static void main(String[] args) {
//        int []sample =  {8, 3, 1, 2};
        int[] sample = {1, 2, 3, 4};
        System.out.println(getMaxSumUsingArrayRotation(sample));
//
//        System.out.println(getNoOfRotationDoneToOriginalArray(sample));

//        System.out.println(getMinimumElementInRotatedArray(sample, 0, 3));
    }

    /**
     * Best Optimized example
     * https://www.geeksforgeeks.org/maximum-sum-iarri-among-rotations-given-array/
     * Input : arr[] = {8, 3, 1, 2}
     * Output : 29
     * Explanation : Let us see all rotations
     * {8, 3, 1, 2} = 8*0 + 3*1 + 1*2 + 2*3 = 11
     * {3, 1, 2, 8} = 3*0 + 1*1 + 2*2 + 8*3 = 29
     * {1, 2, 8, 3} = 1*0 + 2*1 + 8*2 + 3*3 = 27
     * {2, 8, 3, 1} = 2*0 + 8*1 + 3*2 + 1*1 = 17
     */
    public static int getMaxSumUsingArrayRotation(int[] arr) {
        int n = arr.length;
        int arrSum = 0, currentRotationSum = 0, maxSum = 0;

        for (int i = 0; i < n; i++) {
            arrSum += arr[i];
            currentRotationSum += i * arr[i];
        }
        System.out.println("Rotation 0 sum is " + currentRotationSum);
        maxSum = currentRotationSum;
        // Now for next rotation
        for (int i = 1; i < n; i++) {
            currentRotationSum = currentRotationSum - (arrSum - arr[i - 1]) + (arr[i - 1] * (n - 1));
            System.out.println("Rotation (" + i + ") sum is: " + currentRotationSum);
            if (currentRotationSum > maxSum) {
                maxSum = currentRotationSum;
            }
        }

        return maxSum;
    }

    public static int getMaxElementIndex(int[] array) {
        int maxElem = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[maxElem] < array[i]) {
                maxElem = i;
            }
        }
        return maxElem;
    }

    public static int getNoOfRotationDoneToOriginalArray(int[] rotatedArray) {
        int maxElementIndex = getMaxElementIndex(rotatedArray);

        if (maxElementIndex == rotatedArray.length - 1) {
            return 0;
        } else {
            return maxElementIndex + 1;
        }
    }


    public static int getMinimumElementInRotatedArray(int[] arr, int low, int high) {
        if (low > high)
            return arr[0];

        int mid = low + (high - low) / 2;

        if (mid > low && arr[mid] > arr[mid + 1])
            return arr[mid + 1];

        if (mid < high && arr[mid] < arr[mid - 1])
            return arr[mid];

        if (arr[mid] < arr[high])
            return getMinimumElementInRotatedArray(arr, low, mid - 1);
        else
            return getMinimumElementInRotatedArray(arr, mid + 1, high);
    }

}
