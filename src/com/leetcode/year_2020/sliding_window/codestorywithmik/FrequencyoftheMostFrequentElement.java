package com.leetcode.year_2020.sliding_window.codestorywithmik;

import java.util.Arrays;

public class FrequencyoftheMostFrequentElement {

    public static void main(String[] args) {
        FrequencyoftheMostFrequentElement f = new FrequencyoftheMostFrequentElement();
        System.out.println(f.maxFrequency(new int[]{1, 4, 8, 10, 13}, 5));
        System.out.println(f.maxFrequency(new int[]{1, 2, 4}, 5));

        System.out.println(f.maxFrequencyUsingSlidingWindow(new int[]{1, 4, 8, 10, 13}, 5));
        System.out.println(f.maxFrequencyUsingSlidingWindow(new int[]{1, 2, 4}, 5));
    }

    /**
     * Intuition is simple:
     * <p>
     * 1. We should sort the input, why???
     * because you can only ask the smaller ones to become like you as only addition is allowed
     * so if the input is unsorted, how will you know which all numbers to ask that within k operations can you become like me
     * [ 8 1 4 13 10] ===> In this if you consider how many candidates can become 13 ??? from naked eyes it's easier
     * but for a program to handle this it's not that easiy without a proper format
     * <p>
     * Once we sort [1 4 8 10 13] ==> Now tell me is this possible ? yes 13 can always ask all the left ones to become like it
     * <p>
     * Hence prooved we will sort it.
     * <p>
     * 2. Now once you have sorted it, can we take the number and do a binary search on the left to check how many candidates can become
     * the targetItem in 'k' operations.
     * <p>
     * 3. One awesome trick which god knows how i will get to know in a interview but here you go
     * <p>
     * targetIndex
     * [1    4    8     10     13]
     * low                     high
     * mid
     * <p>
     * <p>
     * From Mid to targetIndex [2, 4] if i have to make all elements 13, how much is the total sum of that window = 13 * 3 = 39
     * and what is the current sum i have of the window [8 + 10 + 13] which is 31
     * So you need total 39 - 31 => 8 operations
     * <p>
     * Is that possible with k = 5 , no right, so we need to shift our low to mid+1
     * <p>
     * targetIndex
     * [1    4    8     10     13]
     * low    high
     * <p>
     * TotalSum = Window Size = [targetIndex - mid + 1] [4 - 3 + 1] ==> 2
     * So totalSum = windowSize * target  = 2 * 13 => 26
     * and current windowSum => 23
     * <p>
     * Diff = 26 - 23 = 3 < 5 yeah so we can definitely have 2 freq of 13
     * <p>
     * Now you must have noticed we need currentWindow sum every time we do calculation
     * So what better than prefixSum for this
     * <p>
     * Story time
     * 1. Sort the input
     * 2. Calculate prefixSum
     * 3. Pick every element from last to be the targetIndex for max freq
     * 4. Do binarySearch
     * 5. From MidIndex to targetIndex calculate expectedSum - windowSum
     * 6. if diff < k, consider this freq and put high = mid-1
     * 7 else low = mid+1 to shrink the window
     */
    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int[] prefixSum = new int[nums.length];
        prefixSum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }
        int maxFreq = Integer.MIN_VALUE;
        for (int targetIndex = nums.length - 1; targetIndex >= 0; targetIndex--) {
            int low = 0, high = targetIndex;
            int achievedFreqOfTargetIndex = 0;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                int windowWidth = targetIndex - mid + 1;
                int expectedSumOfWindow = windowWidth * nums[targetIndex];
                int currentSumOfWindow = prefixSum[targetIndex] - ((mid == 0) ? 0 : prefixSum[mid - 1]);
                int diff = expectedSumOfWindow - currentSumOfWindow;
                if (diff <= k) {
                    achievedFreqOfTargetIndex = windowWidth;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            maxFreq = Math.max(maxFreq, achievedFreqOfTargetIndex);
        }
        return maxFreq;
    }

    /**
     * Let's solve this via sliding window, because at the end of day we are sliding
     * the window only to check, the max frequency of the max element
     * <p>
     * As we know we expand and shrink the sliding window, so we will keep begin
     * and end, and always take the item at the [end] to be the element in window whose frequency
     * we are trying to match from all other elements
     * <p>
     * 1,   4,   8,   10,   13
     * b|e
     * <p>
     * CurrentWindowSum = 1
     * and expectedWindowSum = [size*target] == [0 - 0 + 1] * 1 = 1
     * diff = 1-1 == 0 definitely less than k[5] so the maxfrequency in this window is 1
     * <p>
     * 1,   4,   8,   10,   13
     * b    e
     * <p>
     * CurrentSum: 5
     * WindowSum: [1 - 0 + 1] * 4 => 8
     * Diff = 8 - 5 => 3 which is < 5 so we can make both 0th and 1st index 4 with just 3 operations
     * <p>
     * Expand e
     * 1,   4,   8,   10,   13
     * b         e
     * <p>
     * WindowSum = [2 - 0 + 1] * 8 => 24
     * CurrentSum => [1+4+8] = 13
     * diff = 24 - 13 > k so not possible to make all chars 8
     * <p>
     * So shrink the window
     * <p>
     * 1,   4,   8,   10,   13
     * b    e
     * WinSum = 16
     * CurrSum = 12
     * diff = 4 Yes possible so expand
     * <p>
     * 1,   4,   8,   10,   13
     * b         e
     * WinSum = 30
     * CurrSum = 22
     * diff = 8 Not possible so shrink
     * <p>
     * 1,   4,   8,   10,   13
     * b    e
     * WinSum : 20
     * CurrSum: 18
     * Diff: 2, Expand
     * <p>
     * 1,   4,   8,   10,   13
     * b           e
     * <p>
     * WinSum: 39
     * CurrSum: 31
     * Diff =8 not possible shrink
     * <p>
     * 1,   4,   8,   10,   13
     * b    e
     * WinSum: 26
     * CurrSum: 23
     * Diff = 3 Yes possible so expand
     * and done.
     * <p>
     * So max Freq = 2
     */
    public int maxFrequencyUsingSlidingWindow(int[] nums, int k) {
        Arrays.sort(nums);  // crucial step
        int begin = 0, maxFreq = 1;
        long currSum = 0;

        for (int end = 0; end < nums.length; end++) {
            currSum += nums[end];

            // If we need to add more than k, then shrink the window
            // FYI we can be smart about it as well, since we know that if we shrink the window we
            // are reducing the windowSize and that won't give us a better answer
            // so we need not keep shrinking, just shrink once and let the end increase to find a
            // different window, so replacing while with if
//            while ((long) (end - begin + 1) * nums[end] - currSum > k) {
            if ((long) (end - begin + 1) * nums[end] - currSum > k) {
                currSum -= nums[begin];
                begin++;
            }
            maxFreq = Math.max(maxFreq, end - begin + 1);
        }
        return maxFreq;
    }
}
