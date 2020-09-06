package com.leetcode.year_2020.SeptemberChallenge.week1;

import java.util.Arrays;

/**
 * @author neeraj on 01/09/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class LargestTimeForGivenDigits {

    public static void main(String[] args) {
        System.out.println(largestTimeFromDigits(new int[]{1, 2, 3, 4}));
        System.out.println(largestTimeFromDigits(new int[]{5, 5, 5, 5}));
    }

    static String[] largestTime;

    public static String largestTimeFromDigits(int[] A) {
        largestTime = new String[]{"", ""};
        boolean[] used = new boolean[A.length];
        Arrays.fill(used, false);
        backtrack(A, 0, new String[]{"", ""}, used);
        if (largestTime[0].length() == 0) {
            return "";
        } else {
            return largestTime[0] + ":" + largestTime[1];
        }
    }

    public static void backtrack(int[] A, int resultPointer, String[] result, boolean[] used) {
        if (resultPointer == 2) {
            updateLargestTime(result);
            return;
        }

        if (result[resultPointer].length() == 2) {
            backtrack(A, resultPointer + 1, result, used);
        }

        for (int i = 0; i < A.length; i++) {
            if (!used[i]) {
                String timePart = result[resultPointer] + A[i];
                if (canUseTime(timePart, resultPointer)) {
                    result[resultPointer] += A[i];
                    used[i] = true;
                    backtrack(A, resultPointer, result, used);
                    used[i] = false;
                    int len = result[resultPointer].length();
                    result[resultPointer] = result[resultPointer].substring(0, len - 1);
                }
            }
        }
    }

    private static boolean canUseTime(String timePart, int resultPointer) {
        int time = Integer.parseInt(timePart);
        if (resultPointer == 0) {
            return time >= 0 && time < 24;
        } else {
            // Minute Time
            return time >= 0 && time < 60;
        }
    }

    private static void updateLargestTime(String[] result) {
        String oldTime = largestTime[0] + largestTime[1];
        String newTime = result[0] + result[1];
        if (oldTime.length() == 0) {
            largestTime[0] = result[0];
            largestTime[1] = result[1];
        } else {
            int oldTimeInt = Integer.parseInt(oldTime);
            int newTimeInt = Integer.parseInt(newTime);
            if (newTimeInt > oldTimeInt) {
                largestTime[0] = result[0];
                largestTime[1] = result[1];
            }
        }
    }
}
