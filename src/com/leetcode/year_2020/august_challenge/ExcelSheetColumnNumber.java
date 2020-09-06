package com.leetcode.year_2020.august_challenge;

/**
 * @author neeraj on 10/08/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class ExcelSheetColumnNumber {

    public static void main(String[] args) {
        System.out.println(titleToNumber("A"));
        System.out.println(titleToNumber("AB"));
        System.out.println(titleToNumber("AA"));
        System.out.println(titleToNumber("XY"));
        System.out.println(titleToNumber("ZY"));

    }

    public static int titleToNumber(String s) {
        int result = 0, counter = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            result += ((int) (Math.pow(26, counter++)) * (s.charAt(i) - 'A' + + 1));
        }
        return result;
    }
}
