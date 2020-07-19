package com.leetcode.year_2020.july_challenge.week1;

/**
 * @author neeraj on 19/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class AddBinary {

    public static void main(String[] args) {
        System.out.println(addBinary("11", "1"));
        System.out.println(addBinary("1010", "1011"));
    }

    public static String addBinary(String a, String b) {
        StringBuilder str = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0, sum = 0;
        while (i >= 0 || j >= 0) {
            sum = carry;
            if (i >= 0) {
                sum += a.charAt(i--) - '0';
            }
            if (j >= 0) {
                sum += b.charAt(j--) - '0';
            }
            // Now why sum % 2
            // if sum == 1, so 1 % 2 == 1;
            // if sum == 2, so 2 % 2 == 0; // In this case we will push carry by sum /2;
            // if sum == 0, so 0 % 2 == 0;
            str.insert(0, sum % 2);
            carry = sum / 2;
        }
        if (carry > 0) {
            str.insert(0, carry);
        }
        return str.toString();
    }
}
