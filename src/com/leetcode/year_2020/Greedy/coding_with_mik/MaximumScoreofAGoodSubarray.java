package com.leetcode.year_2020.Greedy.coding_with_mik;

/**
 * 1793. Maximum Score of a Good Subarray
 * https://leetcode.com/problems/maximum-score-of-a-good-subarray/description/
 */
public class MaximumScoreofAGoodSubarray {

    public static void main(String[] args) {
        MaximumScoreofAGoodSubarray obj = new MaximumScoreofAGoodSubarray();
        System.out.println(obj.maximumScore(new int[]{1, 4, 3, 7, 4, 5}, 3));
        System.out.println(obj.maximumScore(new int[]{5, 5, 4, 5, 4, 1, 1, 1}, 0));
    }

    /*
     *
     * Intuition:
     * Constraint K = 3 and i<=k<=j [Meaning K index should always be a part of subarray]
     * To get maximum score pick minimum from the subArray and multiply with length of subarray [j - i + 1] or [right - left + 1]
     *
     * Example
     *
     * Items :  1  4  3  7  4  5
     * Index :  0  1  2  3  4  5
     *                  i-j      -------> Initially both points to kth index since that has to be included
     *                                  Min = 7, length (j - i + 1) = 3 - 3 + 1
     *                                 So maxSum = 7
     *
     * Now the job is simply to expand this window and keep track of maximum.
     * A basic question and that will solve the problem is where to move in which direction whether to increment right or left pointer
     * that's indeed simple, if you increment where you find a smaller value and since you haave to pick the minimum in a subArray
     * the larger value will never get a chance to multiply with the length which is not fair
     *
     * and eventually smaller items will come into the picture because we are keep on incrementing i and j to the borders
     * so to give fair chance lets increment on the larger item side
     *
     * Items :  1  4  3  7  4  5
     * Index :  0  1  2  3  4  5
     *                  i   j      -------> Min = 4, length = 2
     *                                      MaxSum = 8
     * Items :  1  4  3  7  4  5
     * Index :  0  1  2  3  4  5
     *                  i      j   -------> Min = 4(stilll 4), length = 3
     *                                      MaxSum = 12
     * Now j can't be increased so keep decrementing i
     *
     * Items :  1  4  3  7  4  5
     * Index :  0  1  2  3  4  5
     *                i        j   -------> Min = 3, length = 4
     *                                      MaxSum = 12
     * Items :  1  4  3  7  4  5
     * Index :  0  1  2  3  4  5
     *             i           j   -------> Min = 3, length = 5
     *                                      MaxSum = 15
     *
     * Items :  1  4  3  7  4  5
     * Index :  0  1  2  3  4  5
     *          i              j   -------> Min = 1, length = 6
     *                                      MaxSum = 15 (but 6 is not > 15)
     *
     * So max sum is always 15
     */
    public int maximumScore(int[] nums, int k) {
        int left = k, right = k;
        int maxSum = nums[k], min = nums[k]; // Initially they all belong to just k

        while (left > 0 || right < nums.length - 1) {
            int right_of_right = right == nums.length - 1 ? Integer.MIN_VALUE : nums[right + 1];
            int left_of_left = left == 0 ? Integer.MIN_VALUE : nums[left - 1];
            if (right_of_right > left_of_left) {
                min = Math.min(min, right_of_right);
                right++;
            } else {
                min = Math.min(min, left_of_left);
                left--;
            }
            int length = right - left + 1;
            maxSum = Math.max(maxSum, min * length);
        }
        return maxSum;
    }
}
