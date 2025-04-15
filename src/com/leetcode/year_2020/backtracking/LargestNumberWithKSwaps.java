package com.leetcode.year_2020.backtracking;

import com.util.LogUtil;

import java.util.Arrays;

/**
 * https://www.geeksforgeeks.org/find-maximum-number-possible-by-doing-at-most-k-swaps/
 * https://leetcode.com/problems/maximum-swap/description/
 * <p>
 * Leetcode 670. Maximum Swap
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
@SuppressWarnings("GrazieInspection")
public class LargestNumberWithKSwaps {

    private static Integer MAX_NUM;

    public static void main(String[] args) {
        findMaximumNum("254".toCharArray(), 1);
        findMaximumNum("254".toCharArray(), 2);
        findMaximumNum("68543".toCharArray(), 1);
        findMaximumNum("7599".toCharArray(), 2);
        findMaximumNum("76543".toCharArray(), 1);
        findMaximumNum("76543".toCharArray(), 2);
        findMaximumNum("129814999".toCharArray(), 4);

        findMaximumWithOneSwap(9973);
        findMaximumWithOneSwap(2736);
        findMaximumWithOneSwap(254);
        findMaximumWithOneSwap(68543);
        findMaximumWithOneSwap(7599);
        findMaximumWithOneSwap(76543);
        findMaximumWithOneSwap(76543);
        findMaximumWithOneSwap(129814999);

        LogUtil.printArray(findSecondLargestNumber(new int[]{2, 7, 3, 6}));
        LogUtil.printArray(findSecondLargestNumber(new int[]{1, 2, 1, 1, 1}));
        LogUtil.printArray(findSecondLargestNumber(new int[]{5, 9, 7, 6, 6, 3, 9, 6, 6}));
        LogUtil.printArray(findSecondLargestNumber(new int[]{1}));
        LogUtil.printArray(findSecondLargestNumber(new int[]{1,1,1,1,1}));
    }

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

    public static int findMaximumWithOneSwap(int num) {
        // We know that we can make a maximum number at any index by replacing it with the largest number on it's right side
        // and that too, the last index of that largest number on the right
        // Couple of examples
        // 7 3 4 4
        // We know that 7 shouldn't be swapped with anyone on the right because its the largest
        // so for 3 we have 2 choices, either with 1st 4 which gives 7 4 3 4 or with last 4 which gives
        // 7 4 4 3 (which is correct)
        // So how about if we can maintain all digits last index of their occurrence while traversing
        // and find that occurrence of max in the right and that sovles the problem
        // For Iterating we need O(N) time complexity but for every ith number we have to go from right
        // to left to find largest index but good thing is that information can be kept in just
        // 10 size array (as digits can just be 0-9) so that gives O(N) * O(10) ==> which is O(N) only drop the constant

        int[] itemIndexes = new int[10];
        Arrays.fill(itemIndexes, -1); // -1 to prove the number doesn't exist
        String numStr = String.valueOf(num);
        char[] charArray = numStr.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            itemIndexes[c - '0'] = i;
        }

        // Now for every i we find max in right
        for (int i = 0; i < charArray.length; i++) {
            int maxIndexInRight = findMaxInRight(itemIndexes, charArray[i] - '0', i);
            if (maxIndexInRight != -1) {
                swap(charArray, i, maxIndexInRight);
                break;
            }
        }
        System.out.println(Integer.parseInt(new String(charArray)));
        return Integer.parseInt(new String(charArray));
    }

    private static int findMaxInRight(int[] itemIndexes, int item, int itemIndex) {
        for (int j = itemIndexes.length - 1; j > item; j--) {
            if (itemIndexes[j] > itemIndex) {
                // Check if the itemIndex is indeed on the right side of item, else it's of no use
                // and will decrease the number instead of increasing it post swap
                return itemIndexes[j];
            }
        }
        return -1;
    }

    public static int[] findSecondLargestNumber(int[] nums) {
        int[] itemFreq = new int[10];
        for (int c : nums) {
            itemFreq[c] += 1;
        }
        int[] largestNumber = new int[nums.length];
        int counter = 0;
        for (int i = 9; i >= 0; i--) {
            while (itemFreq[i] > 0) {
                itemFreq[i] -= 1;
                largestNumber[counter++] = i;
            }
        }

        // Now we have the largest number, we will just keep iterating and replace the 2 least significant item
        // when they are different
        for (int i = largestNumber.length - 1; i > 0; i--) {
            // Why > 0 since we want to check the current number with previous one
            if (largestNumber[i] != largestNumber[i - 1]) {
                int temp = largestNumber[i];
                largestNumber[i] = largestNumber[i - 1];
                largestNumber[i - 1] = temp;
                break;
            }
        }
        return largestNumber;
    }
}
