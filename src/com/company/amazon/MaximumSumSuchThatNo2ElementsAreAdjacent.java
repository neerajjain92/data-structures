package com.company.amazon;

public class MaximumSumSuchThatNo2ElementsAreAdjacent {
    public static void main(String[] args) throws java.lang.Exception {
//        findMaximumSum(new int[]{5, 5, 10, 100, 10, 5});
//        findMaximumSum(new int[]{1, 2, 3});
//        findMaximumSum(new int[]{1, 20, 3});
//        findMaximumSum(new int[]{5, 5, 10, 40, 50, 35});
//        findMaximumSum(new int[]{0, 24, 5, 6, 1, 43, 423, 54, 23, 54, 22, 6, 0, 1, 4, 2, 33, 2, 4, 2, 3});
//        findMaximumSum(new int[]{1, 4, 1, 1, 7});
//
//
//        System.out.println("Find Max Sum Optimized");
//        findMaxSumOptimized(new int[]{5, 5, 10, 100, 10, 5});
//        findMaxSumOptimized(new int[]{1, 2, 3});
//        findMaxSumOptimized(new int[]{1, 20, 3});
//        findMaxSumOptimized(new int[]{5, 5, 10, 40, 50, 35});
//        findMaxSumOptimized(new int[]{0, 24, 5, 6, 1, 43, 423, 54, 23, 54, 22, 6, 0, 1, 4, 2, 33, 2, 4, 2, 3});
//        findMaxSumOptimized(new int[]{1, 4, 1, 1, 7});

        System.out.println("Find Max Sum Using DP .........the only correct solution :::===>" + findMaxSumUsingDP(new int[]{1, 4, 1, 1, 7}, 5));

    }

    // Correct Solution
    public static int findMaxSumUsingDP(int[] arr, int n) {
        if (n == 1)
            return arr[0];
        if (n == 2)
            return Math.max(arr[0], arr[1]);

        int[] DP = new int[n];
        DP[0] = arr[0];
        DP[1] = arr[1];

        for (int i = 2; i < arr.length; i++) {
            DP[i] = Math.max((arr[i] + DP[i - 2]), DP[i - 1]);
        }
        return DP[n - 1];
    }

    public static void findMaxSumOptimized(int[] arr) {
        int evenSum = 0, oddSum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i % 2 == 0) {
                evenSum += arr[i];
            } else {
                oddSum += arr[i];
            }
        }
        System.out.println(evenSum > oddSum ? evenSum : oddSum);
    }

    public static void findMaximumSum(int[] arr) {
        int sum = 0, maxSum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum = 0;
            for (int j = i; j < arr.length; j += 2) {
                sum += arr[j];
            }
            if (maxSum < sum) {
                maxSum = sum;
            }
        }
        System.out.println(maxSum);
    }
}