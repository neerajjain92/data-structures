package com.datastructures.recursion;

/**
 * https://www.geeksforgeeks.org/combinations-string-digits/
 *
 * @author neeraj on 10/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 * <p>
 * Exactly similar to {@link PermutationWithSpaces}
 */
public class CombinationInAStringOfDigits {

    public static void main(String[] args) {
        printCombination("123", "");
        printCombination("1234", "");
        printCombination("ABCD", "");
    }

    private static void printCombination(String input, String output) {
        if (input.equals("")) {
            System.out.println(output);
            return;
        }

        // Initially we can't add any whitespace
        printCombination(input.substring(1), output + input.charAt(0));
        if (!output.isEmpty()) {
            // Only when some alphabet is present in the output, then use Space,
            // because space is not allowed in the extreme left or right
            printCombination(input.substring(1), output + " " + input.charAt(0));
        }
    }
}
