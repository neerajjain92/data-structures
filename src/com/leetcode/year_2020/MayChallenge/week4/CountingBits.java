package com.leetcode.year_2020.MayChallenge.week4;

import com.util.LogUtil;

/**
 * https://leetcode.com/problems/counting-bits/
 *
 * @author neeraj on 28/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class CountingBits {

    public static void main(String[] args) {
        System.out.println(LogUtil.getArrayAsString(countBits(10)));
        System.out.println(LogUtil.getArrayAsString(countBits(0)));
        System.out.println(LogUtil.getArrayAsString(countBits(50)));
        System.out.println(LogUtil.getArrayAsString(countBits(12)));
    }

    public static int[] countBits(int num) {
        if (num == 0) return new int[]{0};
        int[] bits = new int[num + 1];
        // Some base bits which we already know
        bits[0] = 0;
        bits[1] = 1;


        if (num > 1) {
            bits[2] = 1;
        }

        // Now for other sequences we can easily calculate with the previous calculated bits.

        for (int i = 3; i <= num; i++) {
            if (i % 2 == 0) {
                bits[i] = bits[i / 2];
            } else {
                bits[i] = bits[i / 2] + bits[i % 2];
                // System.out.println(i/2);
            }
        }
        return bits;
    }
}
