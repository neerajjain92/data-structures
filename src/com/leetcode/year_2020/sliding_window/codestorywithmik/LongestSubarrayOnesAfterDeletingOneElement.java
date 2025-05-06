package com.leetcode.year_2020.sliding_window.codestorywithmik;

/**
 * https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/
 * 1493. Longest Subarray of 1's After Deleting One Element
 * <p>
 * Given a binary array nums, you should delete one element from it.
 * <p>
 * Return the size of the longest non-empty subarray containing only 1's in the resulting array. Return 0 if there is no such subarray.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,1,0,1]
 * Output: 3
 * Explanation: After deleting the number in position 2, [1,1,1] contains 3 numbers with value of 1's.
 * Example 2:
 * <p>
 * Input: nums = [0,1,1,1,0,1,1,0,1]
 * Output: 5
 * Explanation: After deleting the number in position 4, [0,1,1,1,1,1,0,1] longest subarray with value of 1's is [1,1,1,1,1].
 * Example 3:
 * <p>
 * Input: nums = [1,1,1]
 * Output: 2
 * Explanation: You must delete one element.
 */
public class LongestSubarrayOnesAfterDeletingOneElement {

    public static void main(String[] args) {
        LongestSubarrayOnesAfterDeletingOneElement obj = new LongestSubarrayOnesAfterDeletingOneElement();
        System.out.println(obj.longestSubarray(new int[]{1, 1, 0, 1}));
        System.out.println(obj.longestSubarray(new int[]{0, 1, 1, 1, 0, 1, 1, 0, 1}));
        System.out.println(obj.longestSubarray(new int[]{1, 1, 1}));
        System.out.println(obj.longestSubarray(new int[]{0, 0, 0}));

        System.out.println(obj.longestSubarrayImprovedSlidingWindow(new int[]{1, 1, 0, 1}));
        System.out.println(obj.longestSubarrayImprovedSlidingWindow(new int[]{0, 1, 1, 1, 0, 1, 1, 0, 1}));
        System.out.println(obj.longestSubarrayImprovedSlidingWindow(new int[]{1, 1, 1}));
        System.out.println(obj.longestSubarrayImprovedSlidingWindow(new int[]{0, 0, 0}));
    }

    /**
     * Intuition is pretty simple
     * 1. keep sliding window [being and end]
     * 2. At every end++; calculate Max[end - begin -1] -1 for the deleted zero
     * 3. Keep a zero counter which will keep the count of zero in the sliding window
     * as soon as the count of zero counter crosses > 1, shrink the window
     */
    public int longestSubarray(int[] nums) {
        int begin = 0, end = 0, n = nums.length, zeroCounter = 0;
        int longestSubarray = Integer.MIN_VALUE;
        while (end < n) {
            if (nums[end] == 0) {
                zeroCounter++;
            }
            end++;
            while (zeroCounter > 1) {
                if (nums[begin] == 0) {
                    zeroCounter--;
                }
                begin++;
            }
            longestSubarray = Math.max(longestSubarray, end - begin);
        }
        return longestSubarray - 1;
    }

    /**
     * https://www.youtube.com/watch?v=SQ8tY9nxeZU&list=PLpIkg8OmuX-J2Ivo9YdY7bRDstPPTVGvN&index=12&ab_channel=codestorywithMIK
     * Intuition:
     * <p>
     * Go over the array and instead of shriking the window with a loop, simply keep track
     * of the lastSeenIndex_OfZero and move i after that, why does it work
     * simply think until i remove the zero at lastSeenIndex_OfZero(where j or end) saw the zero
     * there will be 2 zeros in the subArray hence we have to remove.
     * <p>
     *         i
     * I/P ==> 1  1  0  0  1  1  1  0  1
     *         j                             -----> j - i ==> 0
     *            j                           -----> j - i ==> 1
     *               j                         ----------------> [PreZeroIndex = -1], So i should come to 0, it's already on that so distance j - i => 2 - 0 => 2 [1 1 0] we'll delete 1 zero so size is 2
     *                  j                     ----> [PrevZeroIdx => 2, so i should come to 3] then j - i = > 3 - 3 = 0
     *                    j
     *                       j
     *                          j
     *                             j          -----> PrevIndex=3, so i should come to 4, so 7 - 4 => 3
     *                                 j
     *    So max length is 4
     *
     */
    public int longestSubarrayImprovedSlidingWindow(int[] nums) {
        int prevZeroIndex = -1;
        int result = Integer.MIN_VALUE, i = 0, j = 0;
        while (j < nums.length) {
            if (nums[j] == 0) {
                i = prevZeroIndex + 1;
                prevZeroIndex = j;
            }
            result = Math.max(result, j - i);
            j++;
        }
        return result;
    }
}
