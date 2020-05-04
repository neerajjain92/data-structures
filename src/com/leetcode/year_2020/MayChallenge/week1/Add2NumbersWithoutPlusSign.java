package com.leetcode.year_2020.MayChallenge.week1;

/**
 * @author neeraj on 04/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class Add2NumbersWithoutPlusSign {

    public static void main(String[] args) {
        System.out.println(getSum(1, 3));
        System.out.println(getSum(1000, 32));
        System.out.println(getSum(999999, 1));
    }

    public static int getSum(int a, int b) {
        if (a == 0) return b;
        if (b == 0) return a;
        /**
         * https://www.youtube.com/watch?v=qq64FrA2UXQ
         * We will use AND(&) operator to get the carry,
         * We will use XOR(^) operator to do the actual addition.
         * We will use Left Shift(<<) to actually put the carry
         * at the right place.
         */
        int carry = 0;
        int addition = 0;
        while (b != 0) {
            carry = a & b; // Represent carry
            addition = a ^ b; // Represent addition
            a = addition;
            b = carry << 1; // Left shift to put carry at correct place.
        }
        return a;
    }
}
