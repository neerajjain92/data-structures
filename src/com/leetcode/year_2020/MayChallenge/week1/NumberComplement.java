package com.leetcode.year_2020.MayChallenge.week1;

/**
 * @author neeraj on 04/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class NumberComplement {

    public static void main(String[] args) {
        System.out.println(findComplement(5));
        System.out.println(findComplement(7));
    }

    public static int findComplement(int num) {
        /**
         *  So Number Complement of 5 is 2
         * 5 ==> 1 0 1
         *  ===> Complement of 1 is 0 and 0 is 1
         *
         * We can XOR with 1 at each entry
         * So 1 0 1
         *      ^ 1
         *    -----
         *    1 0 0
         *    ^ 1   <<1 (Left Shift by 1)
         *    -----
         *    1 1 0
         *   ^1     <<1 (Left Shift again by 1).
         *    -----
         *    0 1 0   ==> 2.
         *    -----
         *
         *    This is all good but where and when we should stop.
         *    because 1 0 1 ===can be represented as===> 0 0 0 0 1 0
         *    and if we keep on taking the XOR we will end up with ===> 1 1 1 0 1 0.
         *
         *    So we have to put a limit and here we can take advantage of Right Shift
         *    We will rightShift original number
         *
         *              1 0 1
         *     1>>      0 1 0 1
         *     1>>      0 0 1 0 1
         *     1>>     0 0 0 1 0 1
         */
        int temp = num;
        int bit = 1;
        while (temp != 0) {
            num = num ^ bit;
            bit = bit << 1;
            temp = temp >> 1;
        }
        return num;
    }
}
