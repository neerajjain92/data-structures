package com.company.amazon;

import com.geeksforgeeks.array.ArrayRotation;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Problem : 185 Amazon Interview Questions
 * <p>
 * Input: arr[]   = {2, 0, 2}
 * Output: 2
 * Structure is like below
 * | |
 * |_|
 * We can trap 2 units of water in the middle gap.
 * <p>
 * <p>
 * Input: arr[]   = {3, 0, 0, 2, 0, 4}
 * Output: 10
 * Structure is like below
 * |
 * |    |
 * |  | |
 * |__|_|
 * We can trap "3*2 units" of water between 3 an 2,
 * "1 unit" on top of bar 2 and "3 units" between 2
 * and 4.  See below diagram also.
 */
public class TrappingRainWaterProblem {

    public static void main(String[] args) {
        calculateTrappedRainWater(new Integer[]{2, 0, 2});
        calculateTrappedRainWater(new Integer[]{3, 0, 0, 2, 0, 4});
        calculateTrappedRainWater(new Integer[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1});
    }

    public static void calculateTrappedRainWater(Integer[] arr) {
        System.out.print("Total RainWaterTrapped in the below structure ");
        printArray(arr);
        printStructure(arr);
        System.out.println("\n is " + calculate(arr));
    }

    private static int calculate(Integer[] arr) {
        int leftMax = 0;
        int rightMax = 0;
        int left = 0;
        int right = arr.length - 1;
        int totalWaterTrapped = 0;

        while (left <= right) {

            if (arr[left] < arr[right]) { // Left wall is smaller than right wall
                if (arr[left] > leftMax) { // Update left Max
                    leftMax = arr[left];
                } else {
                    totalWaterTrapped += leftMax - arr[left++];
                }
            } else { // Right wall is smaller than left wall
                if (arr[right] > rightMax) { // Update left Max
                    rightMax = arr[left];
                } else {
                    totalWaterTrapped += rightMax - arr[right--];
                }
            }
        }
        return totalWaterTrapped;
    }

    private static void printStructure(Integer[] arr) {
        List<Integer> values = Arrays.asList(arr);
        int max = Collections.max(values);
        boolean[] shouldThisBeIncluded = new boolean[arr.length];

        int temp = max;

        while (temp > 0) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] == temp || shouldThisBeIncluded[j]) {
                    System.out.print("|");
                    if (!shouldThisBeIncluded[j])
                        shouldThisBeIncluded[j] = true;
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
            temp--;
        }

        for (int j = 0; j < arr.length; j++) {
            if (arr[j] == 0) {
                System.out.print("-");
            } else {
                System.out.print(" ");
            }
        }
    }

    public static void printArray(Integer[] input) {
        for (int i = 0; i < input.length; i++) {
            System.out.print(input[i] + ",");
        }
        System.out.println();
    }
}
