package com.leetcode.year_2020.july_challenge.week1;

import com.util.LogUtil;

import java.util.*;

/**
 * @author neeraj on 03/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class PrisonCellAfterNDays {

    public static void main(String[] args) {
        LogUtil.printArray(prisonAfterNDays(new int[]{0, 1, 0, 1, 1, 0, 0, 1}, 7));
        LogUtil.printArray(prisonAfterNDays(new int[]{0, 1, 0, 1, 1, 0, 0, 1}, 15));
        LogUtil.printArray(prisonAfterNDays(new int[]{0, 1, 0, 1, 1, 0, 0, 1}, 8));
        LogUtil.printArray(prisonAfterNDays(new int[]{0, 1, 0, 1, 1, 0, 0, 1}, 9));
        LogUtil.printArray(prisonAfterNDays(new int[]{0, 1, 0, 1, 1, 0, 0, 1}, 10));
        LogUtil.printArray(prisonAfterNDays(new int[]{0, 1, 0, 1, 1, 0, 0, 1}, 11));
        LogUtil.printArray(prisonAfterNDays(new int[]{0, 1, 0, 1, 1, 0, 0, 1}, 20));
        LogUtil.printArray(prisonAfterNDays(new int[]{0, 1, 0, 1, 1, 0, 0, 1}, 50));
        LogUtil.printArray(prisonAfterNDays(new int[]{0, 1, 0, 1, 1, 0, 0, 1}, 500));
        LogUtil.printArray(prisonAfterNDays(new int[]{1, 0, 0, 1, 0, 0, 1, 0}, 1000000000));
    }

    public static int[] prisonAfterNDays(int[] cells, int N) {
        if (cells == null || cells.length == 0 || N <= 0) return cells;
        boolean hasCycle = false;
        int cycleStartsAt = 0;
        Map<String, Boolean> seen = new HashMap<>();

        // If there is a cycle, then we will reach to the same day after those many number of days.
        // so wherever we see the cycle we can just mod the N % cycleStartsAt and then calculate
        // the nextDays only upto those extra days.
        for (int i = 0; i < N; i++) {
            int[] nextDay = getNextDay(cells);
            String key = Arrays.toString(nextDay);
            if (!seen.containsKey(key)) {
                cycleStartsAt++;
                seen.put(key, true);
            } else {
                hasCycle = true;
                break;
            }
            cells = nextDay;
        }
        if (hasCycle) {
            N = N % cycleStartsAt;
            for (int i = 0; i < N; i++) {
                cells = getNextDay(cells);
            }
        }
        return cells;
    }

    public static int[] getNextDay(int[] cells) {
        int[] nextDay = new int[cells.length];
        for (int i = 1; i < cells.length - 1; i++) {
            nextDay[i] = cells[i - 1] == cells[i + 1] ? 1 : 0;
        }
        return nextDay;
    }
}
