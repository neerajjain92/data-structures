package com.leetcode.year_2020.Greedy.coding_with_mik;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/find-polygon-with-the-largest-perimeter/
 * Leetcode 2971. Find Polygon With the Largest Perimeter
 */
public class FindPolygonWithTheLargestPerimeter {

    public static void main(String[] args) {
        FindPolygonWithTheLargestPerimeter obj = new FindPolygonWithTheLargestPerimeter();
//        System.out.println(obj.largestPerimeter(new int[]{1, 2, 3}));
//        System.out.println(obj.largestPerimeter(new int[]{5, 5, 5}));
        System.out.println(obj.largestPerimeter(new int[]{1, 12, 1, 2, 5, 50, 3}));
        System.out.println(obj.largestPerimeter(new int[]{5, 5, 50}));
    }

    /**
     * Intuition is simple, the largest side should be less than sum of remaining sides
     * so if we sort the input then we always know post 2 sides (since for a polygon 3 sides is mandatory)
     * so post 2 sides the 3rd side which we will get is always greater since input is sorted
     * and with the help of prefix sum (we keep track of sum of remaining sides)
     * <p>
     * Simply assume a input of [2 4, 5]
     * when we choose 5[thrid side] for a triangle[Triangle is also a polygon]
     * so sum of remaining [2+4] is greater than 5 so running sum and sorting makes our life easier
     * <p>
     * [1, 1, 2, 3, 5, 12, 50]
     * <p>
     * if you notice when we reached 2 : 2 is not < (1+1) but we didn't stop because the input is sorted there might be a side
     * which could contribute to the answer
     */
    public long largestPerimeter(int[] nums) {
        Arrays.sort(nums);

        long answer = 0;
        long runningSum = nums[0] + nums[1];
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] < runningSum) { // We are only concerned about the sides which contribute to the polygon and
                // Always follow the number < sum(of remaining sides)
                answer = runningSum + nums[i];
            }
            runningSum += nums[i];
        }
        return answer == 0 ? -1 : answer;
    }
}
