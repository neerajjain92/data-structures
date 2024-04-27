package com.datastructures.recursion;

/**
 * @author neeraj on 10/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class GenerateAllBinaryStringWithoutConsecutiveOnes {

    public static void main(String[] args) {
        permute(3, "");
        permute(4, "");
    }

    private static void permute(int n, String output) {
        if (n == 0) {
            System.out.println(output);
            return;
        }
        permute(n - 1, output + "0");
        if (output.length() == 0 || output.charAt(output.length() - 1) == '0') {
            permute(n - 1, output + "1");
        }
    }
}
