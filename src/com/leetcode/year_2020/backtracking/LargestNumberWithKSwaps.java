package com.leetcode.year_2020.backtracking;

import com.util.LogUtil;

/**
 * https://www.geeksforgeeks.org/find-maximum-number-possible-by-doing-at-most-k-swaps/
 * <p>
 * Given two positive integers M and K, find the maximum integer possible by doing at-most K swap operations on its digits.
 * <p>
 * Examples:
 * <p>
 * Input: M = 254, K = 1
 * Output: 524
 * Explanation: Swap 5 with 2 so number becomes 524
 * <p>
 * Input: M = 254, K = 2
 * Output: 542
 * Explanation: Swap 5 with 2 so number becomes 524, Swap 4 with 2 so number becomes 542
 * <p>
 * Input: M = 68543, K = 1
 * Output: 86543
 * Explanation: Swap 8 with 6 so number becomes 86543
 * <p>
 * Input: M = 7599, K = 2
 * Output: 9975
 * Explanation: Swap 9 with 5 so number becomes 7995, Swap 9 with 7 so number becomes 9975
 * <p>
 * Input: M = 76543, K = 1
 * Output: 76543
 * Explanation: No swap is required.
 * <p>
 * Input: M = 129814999, K = 4
 * Output: 999984211
 * Explanation:
 * Swap 9 with 1 so number becomes 929814991,
 * Swap 9 with 2 so number becomes 999814291,
 * Swap 9 with 8 so number becomes 999914281,
 * Swap 1 with 8 so number becomes 999984211
 */
public class LargestNumberWithKSwaps {

    public static void main(String[] args) {
        findMaximumNum("254".toCharArray(), 1);
        findMaximumNum("254".toCharArray(), 2);
        findMaximumNum("68543".toCharArray(), 1);
        findMaximumNum("7599".toCharArray(), 2);
        findMaximumNum("76543".toCharArray(), 1);
        findMaximumNum("76543".toCharArray(), 2);
        findMaximumNum("129814999".toCharArray(), 4);
    }

    private static Integer MAX_NUM;

    private static void findMaximumNum(char[] str, int k) {
        MAX_NUM = Integer.parseInt(new String(str));
        findMaximumNum(str, 0, k);
        LogUtil.logIt(String.format("Maximum Number which can be built from %s is %d", new String(str), MAX_NUM));
    }

    private static void findMaximumNum(char[] arr, int pointer, int swapsLeft) {
        MAX_NUM = Math.max(MAX_NUM, Integer.parseInt(new String(arr)));
        /**
         * Base condition
         * A. if no swaps left
         * B. pointer reached to the end before swaps could finish
         *  [This is possible when the input is already the maxNumber = 876543], you won't be using any swaps
         * but the loop has to end, hence this.
         *
         */
        if (swapsLeft == 0 || pointer == arr.length) {
            return;
        }

        // Let's see how many choices we have
        /**
         * Number is 7 3 4 4
         * <p>
         * Tell me this if I swap 7 with anyone on the right side will it ever make a bigger number
         * probably never also does it make sense to swap by itself, you will get the same number,
         * but you wasted one swap, So we need to be really careful when to really use a swap
         *
         * In the above example, if keep on skipping with whom 7 can swap, I go to end, so then I should
         * think about 7 is at the right place, what about others and hence your journey of incrementing
         * pointer starts.
         *
         *   7  3 4 4
         *  /\
         * / \
         *[set]
         *
         * For 3 yes I have 2 choices 3 with 1st 4 and 3 with another 4
         * which gives me 7 4 3 4 or 7 4 4 3 ===> 7 4 4 3 is obviously bigger and hence we need to try every bigger number
         * on the right side of pointer, and hence we move the choices loop
         *
         * Another interesting observation
         *
         *   3 4 8 2 9 9 1
         *  /\
         * / \
         * ||----------------------> we can swap 3 with anything, on the right
         *                          So tell me if  I should do with '8' or '9'
         *
         * 8 4 3 2 9 9 1
         *      OR
         * 9 4 8 2 3 9 1  =====> so replacing with '9' gave me a bigger number
         */

        // Find the maximum number on the right side with whom we want to replace it
        int maxElement = findMaxInArray(arr, pointer + 1);

        // Move to every maxElement we can replace with
        for (int i = pointer + 1; i < arr.length; i++) {
            if (arr[pointer] < arr[i] && arr[i] == maxElement) {
                swap(arr, pointer, i);
                findMaximumNum(arr, pointer + 1, swapsLeft - 1); // We used a swap
                swap(arr, i, pointer);
            }
        }
        // If swaps are left, then lets simply move the pointer
        findMaximumNum(arr, pointer + 1, swapsLeft);
    }

    private static int findMaxInArray(char[] arr, int fromIndex) {
        int max = Integer.MIN_VALUE;
        for (; fromIndex < arr.length; fromIndex++) {
            if (arr[fromIndex] >= max) {
                max = arr[fromIndex];
            }
        }
        return max;
    }

    private static void swap(char[] str, int start, int i) {
        char temp = str[start];
        str[start] = str[i];
        str[i] = temp;
    }
}
