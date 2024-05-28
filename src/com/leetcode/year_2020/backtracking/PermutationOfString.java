package com.leetcode.year_2020.backtracking;

import com.util.LogUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by jaine03 on 23/07/17.
 */
public class PermutationOfString {

    public static void main(String[] args) {
        printAllPermutations("ABC", "");
        LogUtil.newLine();
        printAllPermutations("AABC", "");
    }

    private static void printAllPermutations(String input, String output) {
        // Base condition
        if (input.length() == 0) {
            System.out.println(output);
            return;
        }

        // To avoid duplicates, we should have a Map which will hold what all we explored
        final Set<Character> explored = new HashSet<>();

        // Choices and Decisions
        for (int i = 0; i < input.length(); i++) {
            if (!explored.contains(input.charAt(i))) {
                explored.add(input.charAt(i));
                String newInput = input.substring(0, i) + input.substring(i + 1); // Skipping the iTh character
                String newOutput = output + input.charAt(i);
                printAllPermutations(newInput, newOutput);
            }
        }
    }
}
