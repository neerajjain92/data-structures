package com.leetcode.year_2020.binary_search;

import java.util.Arrays;

public class MinimizeTheMaximumDifferenceOfPairs {

    public static void main(String[] args) {
        MinimizeTheMaximumDifferenceOfPairs obj = new MinimizeTheMaximumDifferenceOfPairs();
        System.out.println(obj.minimizeMax(new int[]{10, 1, 2, 7, 1, 3}, 2));
        System.out.println(obj.minimizeMax(new int[]{4, 2, 1, 2}, 1));
    }

    public int minimizeMax(int[] nums, int p) {
        Arrays.sort(nums);
        int min_diff = 0; // if 2 digits are same
        int max_diff = Math.abs(nums[0] - nums[nums.length - 1]); // Difference between the largest and the smallest

        int answer = 0;
        while (min_diff <= max_diff) {
            int mid_diff = min_diff + (max_diff - min_diff) / 2;

            if (canWeCreate_P_Pairs_With_Diff_Less_Than_Mid_Diff(mid_diff, nums, p)) {
                answer = mid_diff;
                max_diff = mid_diff - 1; // Minimize the maximum difference
            } else {
                min_diff = mid_diff + 1;
            }
        }
        return answer;
    }

    private boolean canWeCreate_P_Pairs_With_Diff_Less_Than_Mid_Diff(int mid_diff, int[] nums, int p) {
        int totalPairs = 0;
        for (int i = 0; i < nums.length - 1;) {
            int diff = Math.abs(nums[i] - nums[i+1]);
            if (diff <= mid_diff) {
                totalPairs++;
                i += 2; // We found a pair, so jump to the next after the pair, because distinct pairs chahiye
            } else {
                i++;
            }
        }
        return totalPairs >= p;
    }
}
