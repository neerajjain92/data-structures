package com.leetcode.year_2020.MayChallenge.week2;

/**
 * @author neeraj on 09/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class ValidPerfectSquare {

    public static void main(String[] args) {
        System.out.println(isPerfectSquare(16));
        System.out.println(isPerfectSquare(14));
        System.out.println(isPerfectSquare(143123123));
    }

    public static boolean isPerfectSquare(int num) {
        if (num < 1) return false;
        return isPerfectSquare(0, num, num);
    }

    /**
     * Finding isPerfect Square using Binary Search
     *
     * @return
     */
    public static boolean isPerfectSquare(long low, long high, int num) {
        if (low <= high) {
            long mid = low + (high - low) / 2;
            long square = (mid) * (mid);
            if (square == num) {
                return true;
            }
            if (square > num) {
                return isPerfectSquare(low, mid - 1, num);
            } else {
                return isPerfectSquare(mid + 1, high, num);
            }
        }
        return false;
    }
}
