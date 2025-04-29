package com.leetcode.year_2020.stack.codingWithMIK;

import java.util.Stack;

public class SumofSubarrayMinimums {
    int modulo = 1000000007;

    public static void main(String[] args) {
        SumofSubarrayMinimums obj = new SumofSubarrayMinimums();
        System.out.println(obj.sumSubarrayMinsUsingBruteForce(new int[]{3, 1, 2, 4}));
        System.out.println(obj.sumSubarrayMinsUsingBruteForce(new int[]{11, 81, 94, 43, 3}));
        System.out.println(obj.sumSubarrayMins(new int[]{3, 1, 2, 4}));
        System.out.println(obj.sumSubarrayMins(new int[]{11, 81, 94, 43, 3}));
        System.out.println(obj.sumSubarrayMins(new int[]{71, 55, 82, 55}));
        System.out.println(obj.sumSubarrayMinsUsingBruteForce(new int[]{71, 55, 82, 55}));
    }

    public int sumSubarrayMins(int[] arr) {
        int[] nearestElementToLeft = new int[arr.length];
        int[] nearestElementToRight = new int[arr.length];
        Stack<Integer> stack = new Stack<>();

        // nearest smallest element to the left
        for (int i = 0; i < arr.length; i++) {
            if (stack.isEmpty()) {
                nearestElementToLeft[i] = -1;
            } else {
                while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) { // To avoid duplicate we include the duplicate element in only either NSL or NSR
                    stack.pop();
                }
                nearestElementToLeft[i] = stack.isEmpty() ? -1 : stack.peek();
            }
            stack.push(i);
        }


        // nearest Smallest Element to the right
        stack.clear();
        for (int i = arr.length - 1; i >= 0; i--) {
            if (stack.isEmpty()) {
                nearestElementToRight[i] = arr.length;
            } else {
                while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                    stack.pop();
                }
                nearestElementToRight[i] = stack.isEmpty() ? arr.length : stack.peek();
            }
            stack.push(i);
        }

        long result = 0;
        for (int i = 0; i < arr.length; i++) {
            long totalElementsToTheLeft = i - nearestElementToLeft[i];
            long totalElementsToTheRight = nearestElementToRight[i] - i;

            long totalWays = totalElementsToTheLeft * totalElementsToTheRight;

            long totalSum = totalWays * arr[i];

            result = (result + totalSum) % modulo;
        }
        return (int) result;

    }

    public int sumSubarrayMinsUsingBruteForce(int[] arr) {
        int totalSum = 0;
        for (int i = 0; i < arr.length; i++) {
            int min = arr[i];
            totalSum += min;
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                }
                totalSum += min;
            }
        }
        return totalSum % modulo;
    }
}
