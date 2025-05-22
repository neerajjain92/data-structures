package com.datastructures.recursion;

/**
 * https://www.geeksforgeeks.org/print-subsequences-string/
 * <p>
 * Input : abc
 * Output : a, b, c, ab, bc, ac, abc
 * <p>
 * Input : aaa
 * Output : a, aa, aaa
 *
 * @author neeraj on 2019-05-04
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class PrintAllSubsequenceOfString {
    public static void main(String[] args) {
//        printAllSubsequenceNew("abc", "");
        printAllSubsequenceNew("aab", "");
        printAllSubsequenceNew("123", "");
        printAllSubsequenceNew("Geeks", "");
    }

    private static void printAllSubsequenceNew(String input, String output) {
        if (input.isEmpty()) {
            if (!output.isEmpty()) {
                System.out.println(output);
            }
            return;
        }
        // Not Choose
        printAllSubsequenceNew(input.substring(1), output);
        // Choose
        printAllSubsequenceNew(input.substring(1), output + input.charAt(0));
    }
}
