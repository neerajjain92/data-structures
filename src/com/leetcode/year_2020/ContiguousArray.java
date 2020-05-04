package com.leetcode.year_2020;

import java.util.HashMap;
import java.util.Map;

/**
 * @author neeraj on 14/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class ContiguousArray {

    public static void main(String[] args) {
        System.out.println(findMaxLength(new int[]{0, 1}));
        System.out.println(findMaxLength(new int[]{0, 1, 0}));
        System.out.println(findMaxLength(new int[]{0, 0, 0, 0, 1, 1}));
        System.out.println(findMaxLength(new int[]{0, 0, 1, 0, 0, 0, 1, 1}));
        System.out.println(findMaxLength(new int[]{0, 1, 1, 0, 1, 1, 1, 0}));
    }

    /**
     * Solution Inspired by : https://www.youtube.com/watch?v=63ogoiDrd4g&feature=emb_title
     */
    public static int findMaxLength(int[] nums) {
        Map<Integer, Integer> sumToPositionCount = new HashMap<>();
        int maxLength = 0;

        // Initialize 0th position as -1.
        // Specifically for cases like this : [0,1]
        sumToPositionCount.put(0, -1);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            // Since we have to add 1 if nums[i] == 1;
            // and subtract -1 if nums[i] == 0;
//            if (nums[i] == 0) {
//                sum--;
//            } else {
//                sum++;
//            }

            // We can also use an equation instead
            // So if nums[i] =0 then 0*2 -1 = -1;
            // and if nums[i] = 1 then 1*2 -1 = 1;
            sum += nums[i] * 2 - 1;
            if (sumToPositionCount.containsKey(sum)) {
                maxLength = Math.max(maxLength, i - sumToPositionCount.get(sum));
            } else {
                sumToPositionCount.put(sum, i);
            }
        }
        return maxLength;
    }
}
