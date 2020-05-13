package com.leetcode.year_2020.stack;

import java.util.Stack;

/**
 * @author neeraj on 14/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MaximumAreaOfHistogram {

    public static void main(String[] args) {
        System.out.println(findMaxArea(new int[]{2, 1, 5, 6, 2, 3}));
        System.out.println(findMaxArea(new int[]{1}));
        System.out.println(findMaxArea(new int[]{}));
        System.out.println(findMaxArea(new int[]{3, 6, 5, 7, 4, 8, 1, 0}));
        System.out.println(findMaxArea(new int[]{2, 1, 2}));
    }

    public static int findMaxArea(int[] height) {
        /**
         * Input: [2,1,5,6,2,3]
         * Output: 10
         *
         * Now the area can grow in both directions left and right, assume we start at index[1] where value is also 1.
         * So the area can be    1<- 1 ------>5 (Total 6) Area of Rectangle = height * width.
         *
         * So we have to essentially look out in both the directions and maintain the area.
         * Let's start with going in right direction first.
         * At any i how far in right we can only..... only till we find nearest smallest element in the right isn't it ?
         * Yes. so we have to first solve Nearest Smallest Element in the Right problem while maintaining Area in O(N) space.
         */
        Stack<Integer> stack = new Stack<>();
        int[] area = new int[height.length]; // this will store the area.
        int MAX_AREA = Integer.MIN_VALUE;

        // Since we have to find next smallest element in right for each element so we start with right most element.
        for (int i = height.length - 1; i >= 0; i--) {
            if (stack.isEmpty()) {
                area[i] = (height.length - i) * height[i];
            } else {
                while (!stack.isEmpty() && height[stack.peek()] >= height[i]) {
                    stack.pop();
                }

                if (stack.isEmpty()) {
                    area[i] = (height.length - i) * height[i];
                } else {
                    area[i] = (stack.peek() - i) * height[i];
                }
            }
            MAX_AREA = Math.max(MAX_AREA, area[i]);
            stack.push(i); // Pushing index instead of value which will help us calculate distance between 2 items.
        }
        // Now since we have all the right area. lets see which all elements now can contribute or expand their area
        // in the left side, which means finding Next Smallest item in the left side.
        stack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            if (!stack.isEmpty()) {
                // If Stack is empty then this item will not grow at all in left.
                // and it has already contributed it's area in the right side.
                while (!stack.isEmpty() && height[stack.peek()] >= height[i]) {
                    stack.pop();
                }
                if (!stack.isEmpty()) {
                    area[i] += (i - (stack.peek() + 1)) * height[i];
                } else {
                    area[i] += (i - 0) * height[i];
                }
                MAX_AREA = Math.max(MAX_AREA, area[i]);
            }
            stack.push(i);
        }
        return MAX_AREA == Integer.MIN_VALUE ? 0 : MAX_AREA;
    }
}
