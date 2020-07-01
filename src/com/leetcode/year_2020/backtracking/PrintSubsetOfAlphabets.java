package com.leetcode.year_2020.backtracking;

/**
 * @author neeraj on 28/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class PrintSubsetOfAlphabets {

    public static void main(String[] args) {
        subset("AB", "");
        subset("ABC", "");
        subset("AAB", "");
    }

    public static void subset(String input, String output) {
        if (input.length() == 0) {
            System.out.println(output);
            return;
        }

        // To Avoid Duplicates...
        if (output.contains(input.charAt(0) + "")) return;

        subset(input.substring(1), output); // Not Choosing 1st Character
        subset(input.substring(1), output + input.charAt(0)); // Choosing the 1st Character
    }
}
