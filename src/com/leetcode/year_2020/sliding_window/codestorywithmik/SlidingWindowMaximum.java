package com.leetcode.year_2020.sliding_window.codestorywithmik;

import com.util.LogUtil;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * https://leetcode.com/problems/sliding-window-maximum/description/
 * 239. Sliding Window Maximum
 * Solved
 * Hard
 * Topics
 * Companies
 * Hint
 * You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 * <p>
 * Return the max sliding window.
 * Example 1:
 * <p>
 * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation:
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * Example 2:
 * <p>
 * Input: nums = [1], k = 1
 * Output: [1]
 */
public class SlidingWindowMaximum {

    public static void main(String[] args) {
        SlidingWindowMaximum obj = new SlidingWindowMaximum();
        LogUtil.printArray(obj.maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3));
        LogUtil.printArray(obj.maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3));
        LogUtil.printArray(obj.maxSlidingWindow(new int[]{1}, 1));
        LogUtil.printArray(obj.maxSlidingWindow(new int[]{1, 3, 1, 2, 0, 5}, 3));
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k <= 0) return new int[0];
        Deque<Integer> deque = new ArrayDeque<>();
        int n = nums.length;
        int[] res = new int[n - k + 1]; // Total n - k + 1 subarrays will be built

        for (int i = 0; i < n; i++) {

            // Remove elements out of the current sliding window
            // 1, 3, 1, 2, 0, 5
            //[0  1  2  3  4  5]  =================> Indices
            // i                   [             0] --> Deque storing indices
            //    i                [           1 0]
            //       i             [         2 1 0]
            //          i   =========> 3 - k(3) + 1 = 3 - 3 + 1, which is 1, so basically poll anything before index1
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }

            // Remove smaller elements in k range as they are useles
            // So poll from last
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }

            // Always insert at end.
            deque.offerLast(i);

            if (i >= k - 1) { // Found a window
                // How will you get to a start of window
                // windowEnd - sizeOfWindow + 1 ==> because 0 based indexing
                // So i is our windowEnd and k is the size of window, so [i - k + 1]
                res[i - k + 1] = nums[deque.peekFirst()];
            }
        }
        return res;
    }
}
