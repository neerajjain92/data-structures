package com.leetcode.problems.easy;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author neeraj on 01/09/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class ClimbStairs_70 {


    private static Map<Integer, Integer> memoize = new HashMap<>();

    public static void main(String[] args) {
        System.out.println(climbStairs(2));
        System.out.println(climbStairs(3));
        System.out.println(climbStairs2(2));
        System.out.println(climbStairs2(3));
    }

    public static int climbStairs(int n) {
        int totalDistinctWays = 0;

        int[] ways = new int[n + 1];
        ways[0] = 1;
        for (int i = 1; i < ways.length; i++) {
            for (int j = 0; j < i; j++) {
                if (i - j <= 2) {
                    ways[i] += ways[j];
                }
            }
        }
        return ways[ways.length - 1];
    }

    public static int climbStairs2(int n) {
        memoize = new HashMap<>();
        return dfs(0, n);
    }

    public static int dfs(int index, int n) {
        if (index > n) return 0;
        if (index == n) return 1;

        if (memoize.containsKey(index)) return memoize.get(index);
        int result = dfs(index + 1, n) + dfs(index + 2, n);
        memoize.put(index, result);
        return result;
    }
}
