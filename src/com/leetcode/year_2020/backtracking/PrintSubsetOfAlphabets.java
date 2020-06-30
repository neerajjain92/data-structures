package com.leetcode.year_2020.backtracking;

import java.util.List;

/**
 * @author neeraj on 28/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class PrintSubsetOfAlphabets {

    public static void main(String[] args) {
        subset("AB", "");
        subset("ABC", "");
    }

    public static void subset(String input, String output) {
        if(input.length() == 0) {
            System.out.println(output);
            return;
        }

        subset(input.substring(1), output); // Not Choosing 1st Character
        subset(input.substring(1), output + input.charAt(0)); // Choosing the 1st Character

    }
}
