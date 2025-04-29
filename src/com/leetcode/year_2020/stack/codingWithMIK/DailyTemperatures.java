package com.leetcode.year_2020.stack.codingWithMIK;

import com.util.LogUtil;

import java.util.Stack;

public class DailyTemperatures {

    public static void main(String[] args) {
        DailyTemperatures dt = new DailyTemperatures();
        LogUtil.printArray(dt.dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73}));
        LogUtil.printArray(dt.dailyTemperatures(new int[]{30, 40, 50, 60}));
        LogUtil.printArray(dt.dailyTemperatures(new int[]{30, 60, 90}));
        LogUtil.printArray(dt.dailyTemperatures(new int[]{30, 90, 60}));
        LogUtil.printArray(dt.dailyTemperatures(new int[]{10, 10, 10, 40}));
    }

    /**
     * Story
     * 1. Traverse from Right to Left, since we have to check in future(as per question, how many days in winter till we get summer]
     * 2. Pop All Colder Days or Same temperature days from Stack
     * 3: If Stack is empty
     * No Hot day in future
     * else:
     * Found Hot Day: Set (stack.peek().index - index)
     * <p>
     * Why this math?? (stack.peek().index - index)
     * "How many days from today until that hotter day?"
     * <p>
     * 👉 So you take:
     * hotter day's index - today's index
     */
    public int[] dailyTemperatures(int[] temperatures) {
        // 0th-Index is temperature
        // 1st-Index temperatureIndex
        Stack<int[]> stack = new Stack<>();
        int[] result = new int[temperatures.length];
        // We want to look into future, so start from right
        for (int index = temperatures.length - 1; index >= 0; index--) {
            int temperature = temperatures[index];
            // Pop Colder days or same temperature days
            while (!stack.isEmpty() && stack.peek()[0] <= temperature) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                result[index] = 0;
            } else {
                // Found Hot Day
                // How many days from currentDay
                result[index] = stack.peek()[1] - index;
            }
            stack.push(new int[]{temperature, index}); // This is the hotter day now, so push it's index
        }
        return result;
    }
}
